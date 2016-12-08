package es.um.nosql.schemainference.gen.schema.xtend

import es.um.nosql.schemainference.util.emf.ResourceManager
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage
import java.io.File
import java.io.PrintStream
import es.um.nosql.schemainference.NoSQLSchema.Property
import es.um.nosql.schemainference.NoSQLSchema.Association
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.Type
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Entity
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion
import java.util.ArrayList
import java.util.List
import java.util.Map
import java.util.HashMap
import java.util.Iterator
import java.util.Map.Entry
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import java.util.stream.Stream

class DiffToGlobalSchemaWithOptionalFields
{
	var modelName = "";
	var whiteLine="\n"
	var tab="\t"
	var List<Attribute>commonAttrs=new ArrayList
    var List<Aggregate>commonAggrs=new ArrayList
    var List<Reference>commonRefs=new ArrayList
    var List<Attribute> notCommonAttrs=new ArrayList
    var List<Aggregate> notCommonAggrs=new ArrayList
    var List<Reference> notCommonRefs=new ArrayList
//    var Map<String, Aggregate>finalCommonAggrs=new HashMap
	var Map<String, Aggregate> finalNotCommonAggrs=new HashMap
    var List<EntityDiffSpec> eDiffRoots=new ArrayList;
    var List<EntityDiffSpec> eDiffs=new ArrayList;
		    
	def static void main(String[] args) 
    {
		if (args.length < 1)
		{
			System.out.println("Usage: DiffToJS model [outdir]")
			System.exit(-1)
		}

        val inputModel = new File(args.head)
        val ResourceManager rm = new ResourceManager(EntitydifferentiationPackage.eINSTANCE,
        	NoSQLSchemaPackage.eINSTANCE)
        rm.loadResourcesAsStrings(inputModel.getPath())
        val EntityDifferentiation td = rm.resources.head.contents.head as EntityDifferentiation
        
		val outputDir = new File(if (args.length > 1) args.get(1) else ".")
								.toPath().resolve(td.name).toFile()
		// Create destination directory if it does not exist
		outputDir.mkdirs()
        System.out.println("Generating Javascript for " 
        					+ inputModel.getPath() 
        					+ " in "
        					+ outputDir.getPath())

//        val NoSQLDifferences dataModelObject = M2M.getInstance().transform(modelObject)
//

		val diff_to_js = new DiffToGlobalSchemaWithOptionalFields()
		val outFile = outputDir.toPath().resolve(td.name + ".js").toFile()
		val outFileWriter = new PrintStream(outFile)

        outFileWriter.println(diff_to_js.generate(td))
        outFileWriter.close()
        
        System.exit(0)
    }

	def generate(EntityDifferentiation diff)
	{
		modelName = diff.name;
		var tf=diff
		eDiffRoots.clear
		eDiffs.clear
		for(eD: tf.entityDiffSpecs){
	      if(eD.entity.entityversions.get(0).root==true)
	      	eDiffRoots.add(eD)
	      else
	      eDiffs.add(eD)
	}
  '''
  «FOR entL: eDiffRoots»
	«analyzeEnt(entL)»
  «ENDFOR»
  '''
}	
	
def analyzeEnt(EntityDiffSpec ent){
  var List<String> prims=new ArrayList
  var List<String> tuples=new ArrayList
  var List<Reference> refs=new ArrayList
  var List<Aggregate> ags=new ArrayList
	
  commonAttrs.clear
  commonAggrs.clear
  commonRefs.clear
  notCommonAttrs.clear
  notCommonAggrs.clear
  notCommonRefs.clear
  getProps(ent)

'''
  «whiteLine»
  «var contVer2=0»
  «FOR entVer: ent.entityVersionProps»	
  //File «ent.entity.name.toFirstUpper»«contVer2+=1»
  var mongoose = require('mongoose');
  var assert=require('assert');
  var url='mongodb://localhost/dbmongoose'
  mongoose.connect(url, function(error){
  	if(error){
    	    throw error;
    }
    else{
    	console.log('Conectado a MongoDB');
    }
  });
  
  «prims.clear»
  «tuples.clear»
  «refs.clear»
  «ags.clear»
  «getAggregatesCommons(commonAggrs)»
  «getAggregatesNotCommons(notCommonAggrs)»
    
  var «ent.entity.name.toFirstLower»Schema = new mongoose.Schema({
    «FOR a: commonAttrs»
      «printAttribute(a,a.name,true)»
    «ENDFOR»
    «FOR r: commonRefs»
      «printRef(r,true)»
    «ENDFOR»
    «FOR at: notCommonAttrs»
      «analyzeAttribute(at.type,at.name,prims,tuples)»
    «ENDFOR»
    «FOR r2: notCommonRefs»
      «analyzeReference(r2,r2.name,refs)»
    «ENDFOR»
    «FOR agC:commonAggrs»
     «tab»«agC.name»:	{type:«agC.name»Obj, required:true},
    «ENDFOR»
    «FOR agN:notCommonAggrs»
     «tab»«agN.name»:	«agN.name»Obj,
    «ENDFOR»
    
  },{collection:'«ent.entity.name.toFirstLower»'});
  
  «var versionProps=entVer.propertySpecs»
  «FOR prop: versionProps»
     «ent.entity.name.toFirstLower»Schema.path('«prop.property.name»').required()
  «ENDFOR»
  
  var «ent.entity.name.toFirstUpper» = mongoose.model('«ent.entity.name.toFirstUpper»',«ent.entity.name.toFirstLower»Schema);

  «ENDFOR»
  
'''
}

def getProps(EntityDiffSpec ent){
var commonPropsAux=ent.commonProps
  var List<Property> commonProps=new ArrayList
  for (i : 0 ..< commonPropsAux.size){
  	commonProps.add(i,commonPropsAux.get(i).property)
  }
  
  commonAttrs=commonProps.filter(Attribute).toList
  commonAggrs=commonProps.filter(Aggregate).toList
  commonRefs=commonProps.filter(Reference).toList
  var int contVer=-1
  for(entVer: ent.entityVersionProps){
    var eV=ent.entityVersionProps.get(contVer+=1)
    var notCommonPropsAux=eV.propertySpecs
    var List<Property> notCommonProps=new ArrayList
    for (i : 0 ..< notCommonPropsAux.size){
  	   notCommonProps.add(i,notCommonPropsAux.get(i).property)
    }
    var at=notCommonProps.filter(Attribute)
    var aggr=notCommonProps.filter(Aggregate)
    var ref=notCommonProps.filter(Reference)
    var int contAt=-1
    var int contRf=-1
    var int contAgg=-1
    for (i:0..<at.size){
      notCommonAttrs.add(contAt+=1,at.get(i))
    }
    for (i:0..<ref.size){
      notCommonRefs.add(contRf+=1,ref.get(i))
    }
    for (i:0..<aggr.size){
      notCommonAggrs.add(contAgg+=1,aggr.get(i))
    }
  }
  var notCommonAttrsSet=notCommonAttrs.toSet
  var notCommonRefsSet=notCommonRefs.toSet
  var notCommonAggrsSet=notCommonAggrs.toSet
  notCommonAttrs=notCommonAttrsSet.toList
  notCommonRefs=notCommonRefsSet.toList
  notCommonAggrs=notCommonAggrsSet.toList
}

def dispatch printRef(Reference r, boolean isC){
  '''	«r.name»«IF r.upperBound==-1»:	{type: {}, required: true},«ELSE»:	{type: String, required: true},«ENDIF»'''
}

def dispatch printRef(Reference r){
  '''	«r.name»«IF r.upperBound==-1»:	{},«ELSE»:	String,«ENDIF»'''
}

def checkAggr(Aggregate aggr, boolean isR){
  if(aggr.refTo!=null)
  {
  	checkAggregate(aggr.refTo,aggr.name, isR)
  }
}

//check Aggregate.refTo
def checkAggregate(List <EntityVersion> agL, String nameAg, boolean isR)'''
  «var List<Attribute> at=new ArrayList»
  «var List<Reference> ref=new ArrayList»
  «var List<Aggregate> aggr=new ArrayList»
  «var List<String> prims=new ArrayList»
  «var List<String> tuples=new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> ags=new ArrayList»
  «var int contAt=-1»«var int contRf=-1»«var int contAgg=-1»
  «FOR EntityVersion ev:agL»
    «var a=ev.properties.filter(Attribute)»
    «var r=ev.properties.filter(Reference)»
    «var ag=ev.properties.filter(Aggregate)»
    «FOR i:0..<a.size»
      «at.add(contAt+=1,a.get(i))»
    «ENDFOR»
    «FOR i:0..<r.size»
      «ref.add(contRf+=1,r.get(i))»
    «ENDFOR»
    «FOR i:0..<ag.size»
      «aggr.add(contAgg+=1,ag.get(i))»
    «ENDFOR»
  «ENDFOR»
  «IF isR==true»
    «nameAg»Obj=	{
    «ELSE»
    «nameAg»:	{
  «ENDIF»
  	«FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,prims,tuples)»
  	«ENDFOR»
  	«FOR Aggregate ag: aggr»
  	«analyzeAggregate(ag,ag.name,ags)»
  	«ENDFOR»
  	«FOR Aggregate a4: ags»
  	 «checkAggr(a4, false)»
  	«ENDFOR»
  	«FOR Reference rf2: ref»
  	«analyzeReference(rf2,rf2.name,refs)»
  	«ENDFOR»
  } 
'''

def dispatch printAttribute(Attribute a, String name, boolean isC)'''
  «printType(a.type,name, isC)»
'''

def dispatch printType(Type at2, String name, boolean isC) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name, boolean isC){
  '''	«name»:	{type:«primT.name», required:true},'''
}

def dispatch printType(Tuple tuple, String name, boolean isC){
  '''	«name»:	{type:[], required:true},'''
}


def dispatch printAttribute(Attribute a, String name)'''
  «printType(a.type,name)»
'''

def dispatch printType(Type at2, String name) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name){
  '''	«name»:	«primT.name»,'''
}

def dispatch printType(Tuple tuple, String name){
  '''	«name»:	[],'''
}

def dispatch analyzeAggregate(Aggregate ag, String name, List<Aggregate> AgL) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name)
	  if (!rAggreg)
	    {
	      AgL.add(AgL.size,ag)
	      //AgL.add(ag)
	    } 
}

	
def boolean reviseAggregList(List<Aggregate> ag, String name) {
	 for (i : 0 ..< ag.size) {
	    val element = ag.get(i)
	    if(element.name==name)
	    	return true
	 }
     return false
}

//is repeated reference?	
def analyzeReference(Reference ref, String name, List<Reference> RfL) {
  var boolean rRef
  rRef=analyzeRefList(RfL,name)
  if (!rRef)
    {
      RfL.add(ref)
      '''«printRef(ref)»'''
      //checkRef(ref)
    }
}

def boolean analyzeRefList(List<Reference> r, String name) {
 for (i : 0 ..< r.size) {
    val element = r.get(i)
    if(element.name==name)
    	return true
 }
    return false
}
	
	
//for abstract Type class
def dispatch analyzeAttribute(Type at2, String name, List<String> PrL,List<String> TuL) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}


def dispatch analyzeAttribute(PrimitiveType primT, String name, List<String> PrL,List<String> TuL) {
  var boolean rPrim
  rPrim=analyzePrimList(PrL,primT,name)  
  if (!rPrim){
   	PrL.add(name)
   	'''«printType(primT,name)»'''
  }
}


def boolean analyzePrimList(List<String> p, PrimitiveType pr, String name) {
  for (i : 0 ..< p.size) {
    val element = p.get(i)
    if(element==name)
    	return true
  }
  return false
}
	
def dispatch analyzeAttribute(Tuple tuple, String name, List<String> PrL,List<String> TuL) {
  var boolean rTuple
  rTuple=analyzeTupleList(TuL,name)  
  if (!rTuple)
   {
   	TuL.add(name)
   	'''«printType(tuple,name)»'''
   	//'''	«name»:	[]'''
   }
}

def boolean analyzeTupleList(List<String> t, String name) {
   for (i : 0 ..< t.size) {
    val element = t.get(i)
    if(element==name)
    	return true
    }
    return false
}

def getAggregatesCommons(List<Aggregate> ags)'''
«FOR p:ags» 
 «reviseProp(p,true)»
«ENDFOR»
'''

def getAggregatesNotCommons(List<Aggregate> ags)'''
  «var List<Aggregate> ags2= new ArrayList»
  «FOR a1:ags» 
    «FOR a2:ags»
      «var rA=compareAggregates(a1, a2)»
      «IF !rA» 
      «ags2.add(ags2.size, p)»
      «var ver=p.refTo.»
      «finalNotCommonAggrs.put(p.name,p)»
    «ENDIF»
    «ENDFOR»
  «ENDFOR»
  «FOR p:ags2» 
      «reviseProp(p,true)»
  «ENDFOR»
'''

def getAggregates2(Map<Entity, String> props){
for (Entry<Entity, String> p : props.entrySet()){
			var Entity clave = p.getKey();
			var String valor = p.getValue();
			System.out.println(clave+"  ->  "+valor.toString());
		}

}

def boolean compareAggregates(Aggregate a1, Aggregate a2) {
  if(a1.name != a2.name)
    return false
  else if(a1.refTo.size != a2.refTo.size)
     return false
  else{
  	var List<String> entVs1= new ArrayList
    var List<String> entVs2= new ArrayList
  	for(i:0..<a1.refTo.size){
  	  entVs1.add(i,a1.refTo.get(i).versionId.toString)
  	}
  	for(j:0..<a2.refTo.size){
  	  entVs2.add(j,a1.refTo.get(j).versionId.toString)
  	}
  	for(i:0..<entVs1.size){
  	  if(entVs1.get(i)!=entVs2.get(i))
  	   return false
  	}
  }
  return true
}

//for abstract Property class
def dispatch reviseProp(Property p, boolean r) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}
	
def dispatch reviseProp(Association ass, boolean r)'''
  «reviseAssociation(ass,r)»
'''
	
//for abstract association class
def dispatch reviseAssociation(Association ass2, boolean r) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}
	
def dispatch reviseAssociation(Aggregate ag, boolean r) {
   checkAggr(ag, r)
}	
	
}
