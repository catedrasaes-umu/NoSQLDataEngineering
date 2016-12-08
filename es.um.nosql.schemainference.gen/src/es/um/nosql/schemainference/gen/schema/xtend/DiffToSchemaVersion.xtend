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
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec

class DiffToSchemaVersion
{
	var modelName = "";
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

		val diff_to_js = new DiffToSchemaVersion()
		val outFile = outputDir.toPath().resolve(td.name + ".js").toFile()
		val outFileWriter = new PrintStream(outFile)

        outFileWriter.println(diff_to_js.generate(td))
        outFileWriter.close()
        
        System.exit(0)
    }

	def generate(EntityDifferentiation diff)
	{
		modelName = diff.name;
		var ArrayList<EntityDiffSpec> eDiffRoots=new ArrayList;
		
		var tf=diff
		eDiffRoots.clear
		for(eD: tf.entityDiffSpecs){
	      if(eD.entity.entityversions.get(0).root==true){
	      	eDiffRoots.add(eD)
	      }
	}
  '''
  «FOR entL: eDiffRoots»
	«analyzeEnt(entL)»
  «ENDFOR»
  '''
}	
	
def analyzeEnt(EntityDiffSpec ent){
  var int contVer=0;
  var commonPropsAux=ent.commonProps
  var List<Property> commonProps=new ArrayList
  for (i : 0 ..< commonPropsAux.size){
  	commonProps.add(i,commonPropsAux.get(i).property)
  }
  var commonAttrs=commonProps.filter(Attribute)
  var commonAggrs=commonProps.filter(Aggregate)
  var commonRefs=commonProps.filter(Reference)

'''
  «var List<Attribute> notCommonAttrs=new ArrayList»
  «var List<Aggregate> notCommonAggrs=new ArrayList»
  «var List<Reference> notCommonRefs=new ArrayList»
  «var List<String> prims=new ArrayList»
  «var List<String> tuples=new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> ags=new ArrayList»
    
  «FOR entVer: ent.entityVersionProps»
    «var eV=ent.entityVersionProps.get(contVer)»
    «var notCommonPropsAux=eV.propertySpecs»
    «var List<Property> notCommonProps=new ArrayList»
    «FOR i : 0 ..< notCommonPropsAux.size»
  	   «notCommonProps.add(i,notCommonPropsAux.get(i).property)»
    «ENDFOR»
    «var at=notCommonProps.filter(Attribute)»
    «var aggr=notCommonProps.filter(Aggregate)»
    «var ref=notCommonProps.filter(Reference)»
    «var int contAt=-1»
    «var int contRf=-1»
    «var int contAgg=-1»    
    «FOR i:0..<at.size»
      «notCommonAttrs.add(contAt+=1,at.get(i))»
    «ENDFOR»
    «FOR i:0..<ref.size»
      «notCommonRefs.add(contRf+=1,ref.get(i))»
    «ENDFOR»
    «FOR i:0..<aggr.size»
      «notCommonAggrs.add(contAgg+=1,aggr.get(i))»
    «ENDFOR»
    
    //File «ent.entity.name.toFirstUpper»«contVer+=1»
    var mongoose = require('mongoose');
    var url='mongodb://localhost/dbmongoose'
    mongoose.connect(url, function(error){
    	if(error){
    	    throw error;
        }
    	else{
    		console.log('Conectado a MongoDB');
        }
    });
    
    var «ent.entity.name.toFirstLower»Schema = new mongoose.Schema({
    «FOR a: commonAttrs»
      «printAttribute(a,a.name)»
    «ENDFOR»
    «FOR Aggregate ag2: commonAggrs»
      «printAgg(ag2)»
      «checkRefAggr(ag2)»
    «ENDFOR»
    «FOR r: commonRefs»
      «printRef(r)»
    «ENDFOR»
    «FOR at2: notCommonAttrs»
      «analyzeAttribute(at2.type,at2.name,prims,tuples)»
    «ENDFOR»
    «FOR a3:notCommonAggrs»
      «println(a3.class.getSimpleName())»
      «analyzeAggregate(a3,a3.name,ags)»
   	«ENDFOR»
   	«FOR a4: ags»
      «checkRefAggr(a4)»
    «ENDFOR»
    «FOR rf2: notCommonRefs»
      «analyzeReference(rf2,rf2.name,refs)»
    «ENDFOR»
    },
    
    {collection:'«ent.entity.name.toFirstLower»'});
    var «ent.entity.name.toFirstUpper» = mongoose.model('«ent.entity.name.toFirstUpper»',«ent.entity.name.toFirstLower»Schema);

    «FOR r: notCommonRefs»
    «checkRefAggr(r)»
    «ENDFOR»
  «ENDFOR»  
'''
}

def getEntitySchema(Entity e)'''
  «var ArrayList<Attribute> at = new ArrayList»
  «var ArrayList<Reference> ref = new ArrayList»
  «var ArrayList<Aggregate> aggr =new ArrayList»
  «var ArrayList<String> prims = new ArrayList»
  «var ArrayList<String> tuples = new ArrayList»
  «var ArrayList<Reference> refs=new ArrayList»
  «var ArrayList<Aggregate> ags = new ArrayList»
  «var int contAt=-1»«var int contRf=-1»«var int contAgg=-1»
  «FOR EntityVersion ev:e.entityversions»
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
  	«FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,prims,tuples)»
  	«ENDFOR»
  	«FOR Aggregate a3:aggr»
  	«analyzeSpeAggregate(a3,a3.name,ags)»
  	«ENDFOR»
  	«FOR Aggregate a4: ags»
  	«printAgg(a4)»
  	«checkRefAggr(a4)»
  	«ENDFOR»
  	«FOR Reference rf2: ref»
  	«analyzeReference(rf2,rf2.name,refs)»
  	«ENDFOR»
  '''

def printAgg(Aggregate ag3)
  '''«ag3.name»:	{'''

def dispatch checkRefAggr(Aggregate aggr){
  {
  	checkAssociation(aggr.refTo,aggr.name)
  }
}

def printRef(Reference r){
  '''«r.name»«IF r.upperBound==-1»:	{},«ELSE»:	String,«ENDIF»'''
}

def dispatch checkRefAggr(Reference ref){
  if(ref.refTo!=null)
  {
  	checkAssociation(ref.refTo)
  }
}

//check Reference.refTo
def dispatch checkAssociation(Entity e)'''
  «var ArrayList<Attribute> at = new ArrayList»
  «var ArrayList<Reference> ref = new ArrayList»
  «var ArrayList<Aggregate> aggr =new ArrayList»
  «var ArrayList<String> prims = new ArrayList»
  «var ArrayList<String> tuples = new ArrayList»
  «var ArrayList<Reference> refs=new ArrayList»
  «var ArrayList<Aggregate> ags = new ArrayList»
  «var int contAt=-1»«var int contRf=-1»«var int contAgg=-1»
  «FOR EntityVersion ev:e.entityversions»
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

  var «e.name.toFirstLower»Schema = new mongoose.Schema({
  	«FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,prims,tuples)»
  	«ENDFOR»
  	«FOR Aggregate a3:aggr»
  	«analyzeAggregate(a3,a3.name,ags)»
  	«ENDFOR»
  	«FOR Aggregate a4: ags»
  	«checkRefAggr(a4)»
  	«ENDFOR»
  	«FOR Reference rf2: ref»
  	«analyzeReference(rf2,rf2.name,refs)»
  	«ENDFOR»
  },
  {collection:'«e.name.toFirstLower»'});
  var «e.name.toFirstUpper» = mongoose.model('«e.name.toFirstUpper»',«e.name.toFirstLower»Schema);
          
  «e.name.toFirstUpper».find(function(err,«e.name.toFirstLower»){
  	if (err) return console.error(err);
  	var stringVar="\n«e.name.toFirstUpper»:";
  	console.log(stringVar);
  	console.log(«e.name.toFirstLower»);
  });
  «FOR Reference rf3: refs»
    «checkRefAggr(rf3)»
  «ENDFOR»
'''

//check Aggregate.refTo
def dispatch checkAssociation(List <EntityVersion> agL, String nameAg)'''
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
  	«FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,prims,tuples)»
  	«ENDFOR»
  	«FOR Aggregate ag: aggr»
  	«analyzeAggregate(ag,ag.name,ags)»
  	«ENDFOR»
  	«FOR Aggregate a4: ags»
  	 «checkRefAggr(a4)»
  	«ENDFOR»
  	«FOR Reference rf2: ref»
  	«analyzeReference(rf2,rf2.name,refs)»
  	«ENDFOR»
  } 
  «FOR Reference rf3: refs»
    «checkRefAggr(rf3)»
  «ENDFOR»
'''

def printAttribute(Attribute a, String name)'''
  «printType(a.type,name)»
'''

def dispatch printType(Type at2, String name) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name){
  '''«name»:	«primT.name»,'''
}

def dispatch printType(Tuple tuple, String name){
  '''«name»:	[],'''
}

def dispatch analyzeAggregate(Aggregate ag, String name, List<Aggregate> AgL) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name)
	  if (!rAggreg)
	    {
	      AgL.add(ag)
	      '''«printAgg(ag)»'''
	    } 
}

def dispatch analyzeSpeAggregate(Aggregate ag, String name, ArrayList<Aggregate> AgL) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name)
	  if (!rAggreg)
	    {
	      AgL.add(AgL.size,ag)
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

def analyzeSpeReference(Reference ref, String name, List<Reference> RfL) {
  var boolean rRef
  rRef=analyzeRefList(RfL,name)
  if (!rRef)
    {
      RfL.add(RfL.size,ref)
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
	
//special types
def analyzeSpeAttribute(Attribute at2, String name, ArrayList<Attribute> types) {
  var boolean rTypes
  rTypes=analyzeTypesList(types,name)  
  if (!rTypes){
   	types.add(types.size,at2)
  }
}

def boolean analyzeTypesList(ArrayList<Attribute> a, String name) {
  for (i : 0 ..< a.size) {
    val element = a.get(i)
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

//for abstract Property class
def dispatch reviseProp(Property p) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}
	
def dispatch reviseProp(Association ass)'''
  «reviseAssociation(ass)»
'''
	
def dispatch reviseProp(Attribute at) '''
  «printAttribute(at,at.name)»
'''
	
//for abstract association class
def dispatch reviseAssociation(Association ass2) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}
	
def dispatch reviseAssociation(Aggregate ag) {
   printAgg(ag)
}	
	
def dispatch reviseAssociation(Reference ref) {
   printRef(ref)	
}
	
}
