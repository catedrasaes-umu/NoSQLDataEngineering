package es.um.nosql.schemainference.gen.schema.xtend
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource$Factory$Registry
import org.eclipse.emf.ecore.resource.Resource$Factory
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl


import es.um.nosql.schemainference.util.emf.ResourceManager
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage
import java.io.File
import java.io.PrintStream
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema
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
import java.util.Arrays
import java.util.List
import java.util.Map
import java.util.HashMap
import java.util.Iterator
import java.util.Map.Entry
import java.util.stream.Stream
import java.util.stream.Collectors

import es.um.nosql.schemainference.dsl4mongoose.Dsl4mongoosePackage
import es.um.nosql.schemainference.dsl4mongoose.MongooseModel
import es.um.nosql.schemainference.dsl4mongoose.EntityParameter
import es.um.nosql.schemainference.dsl4mongoose.Index
import es.um.nosql.schemainference.dsl4mongoose.Unique
import es.um.nosql.schemainference.dsl4mongoose.IndexKind
import es.um.nosql.schemainference.dsl4mongoose.Validator
import es.um.nosql.schemainference.dsl4mongoose.FieldParameter
import es.um.nosql.schemainference.dsl4mongoose.Update

import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import java.util.Set

class DiffDSLParametersforMorphia {
	val whiteLine="\n"
	val tab="\t"
	var String morphiaPackage
	val boolean root=true
	val boolean noRoot=false
	var List<Attribute>commonAttrs=new ArrayList
    var List<Aggregate>commonAggrs=new ArrayList
    var List<Reference>commonRefs=new ArrayList
    var List<Attribute> notCommonAttrs=new ArrayList
    var List<Aggregate> notCommonAggrs=new ArrayList
    var List<Reference> notCommonRefs=new ArrayList
    var Map<String, Aggregate>finalCommonAggrs=new HashMap
    var Map<String, Aggregate>finalNotCommonAggrs=new HashMap
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
        
               
        val inputModel2 = new File(args.get(1))
        val ResourceManager rm2 = new ResourceManager(Dsl4mongoosePackage.eINSTANCE)
        rm2.loadResourcesAsStrings(inputModel2.getPath)
        val MongooseModel td2 = rm2.resources.head.contents.head as MongooseModel
        
        
        
        
		val outputDir = new File(if (args.length > 1) args.get(2) else ".")
							.toPath().resolve(td.name).toFile()
        //val outputDir = new File(args.get(2)).toPath().resolve(td.name).toFile()
        //outDir=outputDir.toString								
		// Create destination directory if it does not exist
		//val modelName=inputModel.name as String
		//println(modelName)
		outputDir.mkdirs()
        System.out.println("Generating Javascript for " 
        					+ inputModel.getPath() 
        					+ " in "
        					+ outputDir.getPath())

		val diff_to_morphia = new DiffDSLParametersforMorphia
		val outFile = outputDir.toPath().resolve("morphiaMovie.java").toFile()
		//outDir=outputDir.toString
		val outFileWriter = new PrintStream(outFile)

        outFileWriter.println(diff_to_morphia.generate(td, td2))
        outFileWriter.close()
        
        System.exit(0)
    }

	def generate(EntityDifferentiation diff, MongooseModel dslM)
	{
		morphiaPackage=diff.name+".morphiaMapper"
		println(morphiaPackage)
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
  «FOR entRL: eDiffRoots»
	«analyzeEnt(entRL, dslM,root )»
  «ENDFOR»
  «FOR entNRL: eDiffs»
	«analyzeEnt(entNRL, dslM, noRoot)»
  «ENDFOR»
  '''
}	
	
def analyzeEnt(EntityDiffSpec ent,MongooseModel dslM, boolean root){
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
  finalNotCommonAggrs.clear
  finalCommonAggrs.clear
  getProps(ent)
  var EntityParameter entM 
  var params=dslM.parameters.toList
  var Entity entSch=ent.entity
  var List<FieldParameter>paramsL=new ArrayList
  var boolean areThereParams=false
  entM=getDslEntity(params, entSch)
  if(entM!=null)
    areThereParams=true
 
'''
  «var contVer2=0»
  «FOR entVer: ent.entityVersionProps»	
  «var List<Attribute> atV=new ArrayList»
  «var List<Reference> refV=new ArrayList»
  «var List<Aggregate> aggrV=new ArrayList»
  «var List<Property> props=new ArrayList»
  «var List<PrimitiveType> primsL=new ArrayList»
  «var List<Tuple> tuplesL=new ArrayList»
  «var propSp=entVer.propertySpecs.toList»
  «FOR i:0..<propSp.size»
    «props.add(i,propSp.get(i).property)»
  «ENDFOR»
  
  «IF root»
  //Root Entity
  //File «ent.entity.name.toFirstUpper»«contVer2+=1».java
  package «morphiaPackage»;
  import com.mongodb.MongoClient;
  import org.bson.types.ObjectId;
  import org.mongodb.morphia.Datastore;
  import org.mongodb.morphia.Morphia;
  import org.mongodb.morphia.annotations.Entity;
  import org.mongodb.morphia.annotations.Field;
  import org.mongodb.morphia.annotations.Id;
  import org.mongodb.morphia.annotations.Index;
  import org.mongodb.morphia.annotations.Indexes;
  import org.mongodb.morphia.annotations.Indexed;
  import org.mongodb.morphia.annotations.IndexOptions; 
  import org.mongodb.morphia.utils.IndexDirection;
  import org.mongodb.morphia.utils.IndexType;
  import static org.mongodb.morphia.utils.IndexType.TEXT;
  import static org.mongodb.morphia.utils.IndexType.HASHED;
  import org.mongodb.morphia.annotations.Property;
  import org.mongodb.morphia.annotations.Reference;
  import org.mongodb.morphia.annotations.Embedded;
  import org.mongodb.morphia.query.Query;
  import org.mongodb.morphia.query.UpdateOperations;
  import org.mongodb.morphia.query.UpdateResults;
  import java.net.UnknownHostException;
  import java.util.ArrayList;
  import java.util.List;
  «getAggregates(commonAggrs, finalCommonAggrs,dslM)»
  «getAggregates(notCommonAggrs, finalNotCommonAggrs,dslM)»
  «var aV=props.filter(Attribute).toList»
  «var rV=props.filter(Reference).toList»
  «var agV=props.filter(Aggregate).toList»
  «concatAtts(atV,aV,atV)»
  «concatRs(refV,rV,refV)»
  «concatAgs(aggrV,agV,aggrV)»
  «var Map<String, Aggregate>verAggs=new HashMap»
  «FOR a1:aggrV»
    «var String nameA=(a1.eContainer.eContainer as Entity).name+a1.name+(a1.eContainer as EntityVersion).versionId.toString»
    «verAggs.put(nameA,a1)»
  «ENDFOR»
  «var Map<String, Aggregate>remainingAgs=new HashMap»
  «FOR Entry<String, Aggregate> agV1: finalNotCommonAggrs.entrySet()»
    «var String nameAg = agV1.key»
    «var Aggregate Ag = agV1.value»
    «remainingAgs.put(nameAg,Ag)»
  «ENDFOR»
  «removeVerAgs(verAggs,remainingAgs)»
  «prims.clear»
  «tuples.clear»
  «primsL.clear»
  «tuplesL.clear»  
  «refs.clear»
  «ags.clear»
  
  @Entity(«ent.entity.name.toFirstLower»)
  class «ent.entity.name.toFirstUpper»{
  
  // Common Properties	
    «FOR ac: commonAttrs»
    «paramsL.clear»
    «IF areThereParams»«lookFor(ac.name,entM, paramsL)»«ENDIF»
    «IF !paramsL.empty»	«printAttribute(ac,ac.name, true, paramsL)»
    «ELSE»«printAttribute(ac,ac.name,true)»
    «ENDIF»
   	«ENDFOR»
  	«FOR rc: commonRefs»
  	 «printRef(rc,true)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aA : finalCommonAggrs.entrySet()»
    «var String nameAg = aA.getKey()»
    «var Aggregate Ag = aA.getValue()»
    	«Ag.name»:	«nameAg»,
  	«ENDFOR»
    
  // add required for «ent.entity.name.toFirstUpper»«entVer.entityVersion.versionId» entity version
    «var List<Attribute> restAtts=new ArrayList»
    «removeAtts(atV,notCommonAttrs,restAtts)»
    «FOR ac: atV»
    «paramsL.clear»
    «IF areThereParams»«lookFor(ac.name,entM, paramsL)»«ENDIF»
    «IF !paramsL.empty»	«printAttribute(ac,ac.name, true, paramsL)»
    «ELSE»«printAttribute(ac,ac.name,true)»
    «ENDIF»
   	«ENDFOR»
    «FOR r2: refV»
      «printRef(r2,true)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aggV : verAggs.entrySet()»
    «var String nameAg = aggV.getKey()»
    «var Aggregate Ag = aggV.getValue()»
    	«Ag.name»:	{type:«nameAg», required:true},
  	«ENDFOR»
  	«primsL.clear»
  	«prims.clear»

  // Not Common Properties 
    «FOR at: restAtts»
      «analyzeAttribute(at.type,at.name,primsL, tuplesL,prims,tuples)»
    «ENDFOR»
  	«FOR i:0..<primsL.size»
  	«printType(primsL.get(i),prims.get(i))»
  	«ENDFOR»
  	«FOR j:0..<tuplesL.size»
  	«printType(tuplesL.get(j),tuples.get(j))»
  	«ENDFOR»
    «FOR r2: notCommonRefs»
      «analyzeReference(r2,r2.name,refs)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aA : remainingAgs.entrySet()»
    «var String nameAg = aA.getKey()»
    «var Aggregate Ag = aA.getValue()»
    	«Ag.name»:	«nameAg»,
  	«ENDFOR»

  public «ent.entity.name.toFirstUpper»{
  }

  //Root Entity Code
  @Entity(«ent.entity.name.toFirstLower»)
  class «ent.entity.name.toFirstUpper»{
  
  // Common Properties	
    «FOR ac: commonAttrs»
    «printAttribute(ac,ac.name,true,"code")»
   	«ENDFOR»
  	«FOR rc: commonRefs»
  	 «printRef(rc,true)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aA : finalCommonAggrs.entrySet()»
    «var String nameAg = aA.getKey()»
    «var Aggregate Ag = aA.getValue()»
    	«Ag.name»:	«nameAg»,
  	«ENDFOR»
    
  // add required for «ent.entity.name.toFirstUpper»«entVer.entityVersion.versionId» entity version
    «FOR ac: atV»
    «paramsL.clear»
    «IF areThereParams»«lookFor(ac.name,entM, paramsL)»«ENDIF»
    «IF !paramsL.empty»	«printAttribute(ac,ac.name, true, paramsL)»
    «ELSE»«printAttribute(ac,ac.name,true)»
    «ENDIF»
   	«ENDFOR»
    «FOR r2: refV»
      «printRef(r2,true)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aggV : verAggs.entrySet()»
    «var String nameAg = aggV.getKey()»
    «var Aggregate Ag = aggV.getValue()»
    	«Ag.name»:	{type:«nameAg», required:true},
  	«ENDFOR»
  	«primsL.clear»
  	«prims.clear»

  // Not Common Properties 
    «FOR at: restAtts»
      «analyzeAttribute(at.type,at.name,primsL, tuplesL,prims,tuples)»
    «ENDFOR»
  	«FOR i:0..<primsL.size»
  	«printType(primsL.get(i),prims.get(i))»
  	«ENDFOR»
  	«FOR j:0..<tuplesL.size»
  	«printType(tuplesL.get(j),tuples.get(j))»
  	«ENDFOR»
    «FOR r2: notCommonRefs»
      «analyzeReference(r2,r2.name,refs)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aA : remainingAgs.entrySet()»
    «var String nameAg = aA.getKey()»
    «var Aggregate Ag = aA.getValue()»
    	«Ag.name»:	«nameAg»,
  	«ENDFOR»
  }
    
  
  
  «ELSE»
  //Embedded Entity
  //File «ent.entity.name.toFirstUpper»
  package «morphiaPackage»;
  import org.mongodb.morphia.annotations.Embedded;
  «getAggregates(commonAggrs, finalCommonAggrs,dslM)»
  «getAggregates(notCommonAggrs, finalNotCommonAggrs,dslM)»
  «var aV=props.filter(Attribute).toList»
  «var rV=props.filter(Reference).toList»
  «var agV=props.filter(Aggregate).toList»
  «concatAtts(atV,aV,atV)»
  «concatRs(refV,rV,refV)»
  «concatAgs(aggrV,agV,aggrV)»
  «var Map<String, Aggregate>verAggs=new HashMap»
  «FOR a1:aggrV»
    «var String nameA=(a1.eContainer.eContainer as Entity).name+a1.name+(a1.eContainer as EntityVersion).versionId.toString»
    «verAggs.put(nameA,a1)»
  «ENDFOR»
  «var Map<String, Aggregate>remainingAgs=new HashMap»
  «FOR Entry<String, Aggregate> agV1: finalNotCommonAggrs.entrySet()»
    «var String nameAg = agV1.key»
    «var Aggregate Ag = agV1.value»
    «remainingAgs.put(nameAg,Ag)»
  «ENDFOR»
  «removeVerAgs(verAggs,remainingAgs)»
  «prims.clear»
  «tuples.clear»
  «primsL.clear»
  «tuplesL.clear»  
  «refs.clear»
  «ags.clear»
  @Embedded
  public class «ent.entity.name.toFirstUpper» {
  
  // Common Properties	
    «FOR ac: commonAttrs»
    «paramsL.clear»
    «IF areThereParams»«lookFor(ac.name,entM, paramsL)»«ENDIF»
    «IF !paramsL.empty»	«printAttribute(ac,ac.name, true, paramsL)»
    «ELSE»«printAttribute(ac,ac.name,true)»
    «ENDIF»
   	«ENDFOR»
  	«FOR rc: commonRefs»
  	 «printRef(rc,true)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aA : finalCommonAggrs.entrySet()»
    «var String nameAg = aA.getKey()»
    «var Aggregate Ag = aA.getValue()»
    	«Ag.name»:	«nameAg»,
  	«ENDFOR»
    
  // add required for «ent.entity.name.toFirstUpper»«entVer.entityVersion.versionId» entity version
    «var List<Attribute> restAtts=new ArrayList»
    «removeAtts(atV,notCommonAttrs,restAtts)»
    «FOR ac: atV»
    «paramsL.clear»
    «IF areThereParams»«lookFor(ac.name,entM, paramsL)»«ENDIF»
    «IF !paramsL.empty»	«printAttribute(ac,ac.name, true, paramsL)»
    «ELSE»«printAttribute(ac,ac.name,true)»
    «ENDIF»
   	«ENDFOR»
    «FOR r2: refV»
      «printRef(r2,true)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aggV : verAggs.entrySet()»
    «var String nameAg = aggV.getKey()»
    «var Aggregate Ag = aggV.getValue()»
    	«Ag.name»:	{type:«nameAg», required:true},
  	«ENDFOR»
  	«primsL.clear»
  	«prims.clear»

  // Not Common Properties 
    «FOR at: restAtts»
      «analyzeAttribute(at.type,at.name,primsL, tuplesL,prims,tuples)»
    «ENDFOR»
  	«FOR i:0..<primsL.size»
  	«printType(primsL.get(i),prims.get(i))»
  	«ENDFOR»
  	«FOR j:0..<tuplesL.size»
  	«printType(tuplesL.get(j),tuples.get(j))»
  	«ENDFOR»
    «FOR r2: notCommonRefs»
      «analyzeReference(r2,r2.name,refs)»
    «ENDFOR»
  	«FOR Entry<String, Aggregate> aA : remainingAgs.entrySet()»
    «var String nameAg = aA.getKey()»
    «var Aggregate Ag = aA.getValue()»
    	«Ag.name»:	«nameAg»,
  	«ENDFOR»
  },{collection:'«ent.entity.name.toFirstLower»'});
  
  var «ent.entity.name.toFirstUpper» = mongoose.model('«ent.entity.name.toFirstUpper»',«ent.entity.name.toFirstLower»Schema);
  
  
  «ENDIF»
    
  
  // Update
  
  «var List<Update> updList=new ArrayList»
  «IF areThereParams»
  «updList=entM.updates.toList» 
  «IF updList!=null»
 
  function «ent.entity.name.toLowerCase»_Updating(query , fieldsToUpdate) {
  «ent.entity.name.toFirstUpper».findOne (
  query ,
  function (err , «ent.entity.name.toLowerCase») {
  if (! err ) {
  «FOR Update fUpd:updList»	
  «ent.entity.name.toLowerCase».«fUpd.fieldName» = aGenre ;
  «ent.entity.name.toLowerCase»movie . save (function (err , user ) {«ENDFOR»
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});
  «ENDIF» 
  «ENDIF»	
  
«ENDFOR»
  
'''
}

def removeAtts(List<Attribute> verL, List<Attribute> origL,List<Attribute> resL){
  for(j:0..<origL.size)
      resL.add(j,origL.get(j))
  for(i:0..<origL.size){
  	var Attribute a=origL.get(i)
  	for(k:0..<verL.size){
  	   var Attribute at=verL.get(k)
  	   if(a.name==at.name && a.type.class.name.toString==at.type.class.name.toString)
  	    resL.remove(a)
    }
  }
}

def removeVerAgs(Map <String, Aggregate> aggs,Map <String, Aggregate>finalAggs){
  for(Entry<String, Aggregate> agV2: aggs.entrySet()){
     finalAggs.remove(agV2.key)
  }
  	
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
}

def dispatch printRef(Reference r, boolean isC){
'''«r.name»«IF r.upperBound==-1»:	{type: {}, required: true, ref: «r.refTo.name»},
«ELSE»:	{type: String, required: true, ref: «r.refTo.name»},«ENDIF»'''
}

def dispatch printRef(Reference r){
  '''	«r.name»«IF r.upperBound==-1»:	{type:{}, ref: «r.refTo.name»}«ELSE»:	{type:String, ref: «r.refTo.name»}«ENDIF»'''
}

//Commons Attributes for annotations
def dispatch printAttribute(Attribute a, String name, boolean isC)'''
  «printType(a.type,name, isC)»
'''

def dispatch printType(Type at2, String name, boolean isC) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name, boolean isC){
  primT.name=primT.name.replace("Number","int")
  '''	private «primT.name» «name»;'''
}

def dispatch printType(Tuple tuple, String name, boolean isC){
  var List<Type>tupleElements=tuple.elements.toList
  var String typeName=findingFirst(tupleElements,0)
  '''	private «typeName»[] «name»;'''
}

//Commons Attributes for methods
def dispatch printAttribute(Attribute a, String name, boolean isC, String c)'''
  «printType(a.type,name, c)»
'''

def dispatch printType(Type at2, String name, boolean isC, String c) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name, boolean isC, String c){
  primT.name=primT.name.replace("Number","int")
  '''	private «primT.name» «name»;'''
}

def dispatch printType(Tuple tuple, String name, boolean isC, String c){
  var List<Type>tupleElements=tuple.elements.toList
  var String typeName=findingFirst(tupleElements,0)
  '''	private «typeName»[] «name»;'''
}

//find first one in tuple
def String findingFirst(List<Type>tupleL, int i){
  if (tupleL.get(i).class.getSimpleName=="PrimitiveTypeImpl")
   return ((tupleL.get(i) as PrimitiveType).name)
  else{
    var j=i+1
    findingFirst(tupleL, j)
  }
}

//Commons Attributes with parameters for annotations
def dispatch printAttribute(Attribute a, String name, boolean isC, List<FieldParameter> pL)'''
  «printType(a.type,name, isC, pL)»
'''

def dispatch printType(Type at2, String name, boolean isC, List<FieldParameter> pL) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name, boolean isC, List<FieldParameter> pL){
  var String fSchema
  primT.name=primT.name.replace("Number","int")
  var String fieldSchema="@"
//":\t{type: "+primT.name	
	for(FieldParameter fp:pL){
	  fSchema=checkParameter(fp, fieldSchema)
	  fieldSchema=fSchema
	}
	'''
	«fSchema»
		private «primT.name» «name»;
	'''
}

def dispatch printType(Tuple tuple, String name, boolean isC, List<FieldParameter> pL){
  var String fSchema
  var String fieldSchema="@"
  var List<Type>tupleElements=tuple.elements.toList
  var String typeName=findingFirst(tupleElements,0)
  //name+":\t{type: "+"[]"	
	for(FieldParameter fp:pL){
	  fSchema=checkParameter(fp, fieldSchema)
	  fieldSchema=fSchema
	}
	'''
	«fSchema»
		private «typeName»[] «name»;
	'''
}


//ojo ojo ojo
def dispatch printType(Type at2, String name) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name){
  primT.name=primT.name.replace("Number","int")
  '''private «primT.name» «name»;'''
}

def dispatch printType(Tuple tuple, String name){
  var List<Type>tupleElements=tuple.elements.toList
  var String typeName=findingFirst(tupleElements,0)
  '''private «typeName»[] «name»;'''

}

def dispatch printAttribute(Attribute a, String name, List<FieldParameter> pL)'''
  «printType(a.type,name, pL)»
'''

def dispatch printType(Type at2, String name, List<FieldParameter> pL) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name, List<FieldParameter> pL){
  var String fSchema
  primT.name=primT.name.replace("Number","int")
  var String fieldSchema="@"
//":\t{type: "+primT.name	
	for(FieldParameter fp:pL){
	  fSchema=checkParameter(fp, fieldSchema)
	  fieldSchema=fSchema
	}
	'''
	«fSchema»
		private «primT.name» «name»;
	'''
}

def dispatch printType(Tuple tuple, String name, List<FieldParameter> pL){
  var String fSchema
  var String fieldSchema="@"
  var List<Type>tupleElements=tuple.elements.toList
  var String typeName=findingFirst(tupleElements,0)
  //name+":\t{type: "+"[]"	
	for(FieldParameter fp:pL){
	  fSchema=checkParameter(fp, fieldSchema)
	  fieldSchema=fSchema
	}
	'''
	«fSchema»
		private «typeName»[] «name»;
	'''
}

/* 
def dispatch printTypeAg(Type at2, String name,  List<FieldParameter> pL) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printTypeAg(Tuple t, String name,  List<FieldParameter> pL){
  var String fSchema
  var String fieldSchema=name+":\t{type: "+"[]"	
	for(FieldParameter fp:pL){
	  fSchema=checkParameter(fp, fieldSchema)
	  fieldSchema=fSchema
	}
	'''«fSchema»},'''
}


def dispatch printTypeAg(PrimitiveType primT, String name,  List<FieldParameter> pL){
  var String fSchema
  var String fieldSchema=name+":\t{type: "+primT.name	
	for(FieldParameter fp:pL){
	  fSchema=checkParameter(fp, fieldSchema)
	}
	'''«fSchema»},'''
}
* 
*/

def dispatch checkParameter(FieldParameter fp, String fieldS){
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch String checkParameter(Unique fp, String fieldSchema){
  var String fS=fieldSchema
  fS+="Indexed (unique=true)"          
  return(fS)
}

def dispatch String checkParameter(Index fp, String fieldSchema){
  var String fS=fieldSchema
  fS+="Indexed (IndexDirection."   
  var String kind=fp.kind.toString     
  switch (kind){
  	case "ASC": fS+="ASC)"
  	case "DESC": fS+="DESC)"
  	case "GEO2D": fS+="GEO2D)"
  	case "GEO2DSPHERE": fS+="GEO2DSPHERE)"
  }
  return(fS)
}

//In here it define validators
def dispatch String checkParameter(Validator fp, String fieldSchema){
  var String fS=fieldSchema
  var String vName=fp.validatorName
  var String v
  var String valName
  if(vName.contains("enum"))
    v="enum"
  else if(vName.contains("length"))
         v="length"
  switch (v){
    case "enum":{
  	             valName=vName.replace('(','[').replace(')',']')
                 valName=valName.replaceFirst("enum","enum:")
                 fS+=", "+valName
                }
    case "length":{
    	            var int i=vName.indexOf('<')
    	            var String lValue=vName.substring(i+2,vName.length)
    	            fS+=","+" maxlength: "+lValue
                  }
}//end switch

   return(fS)
}

/*
def dispatch printType(Tuple tuple, String name,  List<FieldParameter> pL){
    var String vt=v.validatorName
  var boolean enumIs=vt.contains("enum")
  if(enumIs){
    var String enumVal=vt.replace('(','[').replace(')',']')
    '''	«name»:	{type: [], «enumVal»}'''
  }
  else

  '''	«name»:	[],'''
}
*/

def dispatch analyzeAggregate(Aggregate ag, String name, List<Aggregate> AgL) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name,ag)
	  if (!rAggreg)
	    {
	      AgL.add(AgL.size,ag)
	      //AgL.add(ag)
	    } 
}

	
def boolean reviseAggregList(List<Aggregate> ag, String name, Aggregate a) {
	 for (i : 0 ..< ag.size) {
	    val element = ag.get(i)
	    if(element.name==name && element.refTo.size==a.refTo.size)
	    	return true
	 }
     return false
}

//is repeated reference?	
def analyzeReference(Reference ref, String name, List<Reference> RfL) {
  var boolean rRef
  rRef=analyzeRefList(RfL,name,ref)
  if (!rRef)
    {
      RfL.add(RfL.size,ref)
      '''«printRef(ref)»'''
      //checkRef(ref)
    }
}

def boolean analyzeRefList(List<Reference> rL, String name, Reference r) {
 for (i : 0 ..< rL.size) {
    val Reference element = rL.get(i)
    if(element.name==name && element.refTo.name==r.refTo.name)
    	return true
 }
    return false
}
	
	
//for abstract Type class
def dispatch analyzeAttribute(Type at2, String name,List<PrimitiveType>PrL,List<Tuple>TuL,List<String> pL, List<String> t) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}


def dispatch analyzeAttribute(PrimitiveType primT, String name, List<PrimitiveType> PrL,List<Tuple> TuL,List<String> pL, List<String> t) {
  var boolean rPrim
  rPrim=analyzePrimList(PrL,pL,primT,name)  
  if (!rPrim){
   	pL.add(pL.size,name)
   	PrL.add(PrL.size,primT)
   	//'''«printType(primT,name)»'''
  }
}


def boolean analyzePrimList(List<PrimitiveType> ptL,List<String> ps, PrimitiveType pr, String name) {
  for (i : 0 ..< ps.size) {
    val element = ps.get(i)
    val elementP = ptL.get(i)
    if(element==name && elementP.name==pr.name)
    	return true
  }
  return false
}
	
def dispatch analyzeAttribute(Tuple tuple, String name, List<PrimitiveType> PrL,List<Tuple> TuL,List<String> pL, List<String> t) {
  var boolean rTuple
  rTuple=analyzeTupleList(TuL,t,tuple,name)  
  if (!rTuple)
   {
   	t.add(t.size,name)
   	TuL.add(TuL.size,tuple)
   	//'''«printType(tuple,name)»'''
   	//'''	«name»:	[]'''
   }
}

def boolean analyzeTupleList(List<Tuple> tt,List<String> ts, Tuple t, String name) {
   for (i : 0 ..< ts.size) {
    val element = ts.get(i)
    val elementT = tt.get(i)
    if(element==name && elementT.elements.size==t.elements.size)
    	return true
    }
    return false
}
		
def getAggregates(List<Aggregate> ags, Map<String, Aggregate> restListAg, MongooseModel dslM){
  var List<Aggregate> ags2= new ArrayList
  
  for(a1:ags){
     var indexPosi=reviseAggregNotCommons(a1, ags2)
     if (indexPosi == -1){
       ags2.add(ags2.size, a1)
       var String nameA=(a1.eContainer.eContainer as Entity).name+a1.name+(a1.eContainer as EntityVersion).versionId.toString
       addAg(restListAg,nameA,a1)
     }
     else{
       var res=compareAggregates(a1, ags2.get(indexPosi))
       if (res==false){ 
          ags2.add(ags2.size, a1)
          var String nameA=(a1.eContainer.eContainer as Entity).name+a1.name+(a1.eContainer as EntityVersion).versionId.toString
          addAg(restListAg,nameA,a1)
       }
     }
  }
}

def addAg(Map <String, Aggregate> agMap,String name, Aggregate a){
	agMap.put(name,a)
}

def int reviseAggregNotCommons(Aggregate a, List<Aggregate> ag) {
	 var List<Aggregate> ags3= new ArrayList
	 for (i : 0 ..< ag.size) {
	    val element = ag.get(i)
	    if(element.name==a.name)
	    return i  
	 }
     return -1
}


def boolean compareAggregates(Aggregate a1, Aggregate a2) {
  if(a1.refTo.size != a2.refTo.size)
    return false
  else{
    var List<String> entVs1= new ArrayList
    var List<String> entVs2= new ArrayList
    for(i:0..<a1.refTo.size){
      entVs1.add(i,a1.refTo.get(i).versionId.toString)
    }
    for(j:0..<a2.refTo.size){
      entVs2.add(j,a2.refTo.get(j).versionId.toString)
    }
    for(i:0..<entVs1.size){
      if(entVs1.get(i)!=entVs2.get(i))
        return false
    }
  }
  return true
}

/*
//for abstract Property class
def dispatch reviseProp(Property p, boolean r, String nameA) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}
	
def dispatch reviseProp(Association ass, boolean r, String nameA)'''
  «reviseAssociation(ass,r,nameA)»
'''
	
//for abstract association class
def dispatch reviseAssociation(Association ass2, boolean r, String nameA) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}
	
def dispatch reviseAssociation(Aggregate ag, boolean r, String nameA) {
   checkAggr(ag, r, nameA)
}
* 
*/

/*

def checkAggr(Aggregate aggr, boolean isR, String nameA, MongooseModel dslM){
  if(aggr.refTo!=null)
  {
  	checkAggregate(aggr.refTo,nameA, isR, dslM)
  }
}

def checkAggregate(List <EntityVersion> agL, String nameAg, boolean isR, MongooseModel dslM){
  var List<Attribute> at=new ArrayList
  var List<Reference> ref=new ArrayList
  var List<Aggregate> aggr=new ArrayList
  var List<String> prims=new ArrayList
  var List<String> tuples=new ArrayList
  var List<PrimitiveType> primsL=new ArrayList
  var List<Tuple> tuplesL=new ArrayList
  var EntityParameter entM 
  var List<Reference> refs=new ArrayList
  var List<Aggregate> ags=new ArrayList

  for (EntityVersion ev:agL){
    var a=ev.properties.filter(Attribute).toList
    var r=ev.properties.filter(Reference).toList
    var ag=ev.properties.filter(Aggregate).toList
    concatAtts(at,a,at)
    concatRs(ref,r,ref)
    concatAgs(aggr,ag,aggr)
  }
  
  var Entity entAg=agL.get(0).eContainer as Entity
  var params=dslM.parameters.toList
  var List<FieldParameter>paramsL=new ArrayList
  var boolean areThereParams=false
  entM=getDslEntity(params, entAg)
  if(entM!=null)
    areThereParams=true
 
'''
  «IF isR==true»
    //code for embedded «nameAg» File
    package «morphiaPackage»;
    import org.mongodb.morphia.annotations.Embedded;
    @Embbeded
    public class «nameAg»{
    «ELSE»
    @Embbeded
    «nameAg»:	{
  «ENDIF»
  	«FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,primsL, tuplesL, prims,tuples)»
  	«ENDFOR»
  	«FOR i:0..<primsL.size»
  	«paramsL.clear»
  	«IF areThereParams»«lookFor(prims.get(i),entM, paramsL)»«ENDIF»
  	«IF !paramsL.empty»
  	«printTypeAg(primsL.get(i),prims.get(i), paramsL)»
  	«ELSE»	«printType(primsL.get(i),prims.get(i))»
    «ENDIF»
  	«ENDFOR»
  	«FOR i:0..<tuplesL.size»
  	«paramsL.clear»
  	«IF areThereParams»«lookFor(tuples.get(i),entM, paramsL)»«ENDIF»
  	«IF !paramsL.empty»
  	«printTypeAg(tuplesL.get(i),tuples.get(i), paramsL)»
  	«ELSE»	«printType(tuplesL.get(i),tuples.get(i))»
    «ENDIF»
  	«ENDFOR»
  	«FOR Aggregate ag: aggr»
  	«analyzeAggregate(ag,ag.name,ags)»
  	«ENDFOR»
  	«FOR Aggregate a4: ags»
  	 «checkAggr(a4,false,a4.name, dslM)»
  	«ENDFOR»
  	«FOR Reference rf2: ref»
  	«analyzeReference(rf2,rf2.name,refs)»
  	«ENDFOR»
      } 
'''
}

* 
* 
*/


def lookFor(String nameF,EntityParameter ep, List<FieldParameter>paramsL){
  var List<Validator> vals=new ArrayList	
  var List<Unique> uniqs=new ArrayList
  var List<Index> indxs=new ArrayList
  if(ep!=null){
  	vals=ep.validators
  	uniqs=ep.uniques
  	indxs=ep.indexes
    if(vals!=null){
   	 for(Validator v:vals){
  	   if(nameF==v.fieldName)
  	     paramsL.add(v)
   	 }//end for
   }//end vals==null
   if(uniqs!=null){
   	 for(Unique u:uniqs){
  	   if(nameF==u.fieldName)
  	     paramsL.add(u)
   	 }//end for
   }//end uniqs==null
   if(indxs!=null){
   	 for(Index ind:indxs){
  	   if(nameF==ind.fieldName)
  	     paramsL.add(ind)
   	 }//end for
   }//end indxs==null
}//end ep==null
}//end def

def getDslEntity(List <EntityParameter> ps, Entity eT){
  	for(e:ps){
  		if(eT.name==e.name){
  		  return e 
  		}
  	}
	return null
}

def concatAtts(List<Attribute> atLA,List<Attribute> atLB, List<Attribute> resL){	
  var Stream<Attribute> s1=atLA.stream
  var Stream<Attribute> s2=atLB.stream
  var Stream<Attribute> s3=Stream.concat(s1,s2)
  var List<Attribute>res= s3.collect(Collectors.toList)
  resL.clear
  for(i:0..<res.size){
  	resL.add(i,res.get(i))
  }
}

def concatRs(List<Reference> atLA,List<Reference> atLB,List<Reference> resL){	
  var Stream<Reference> s1=atLA.stream
  var Stream<Reference> s2=atLB.stream
  var Stream<Reference> s3=Stream.concat(s1,s2)
  var List<Reference>res= s3.collect(Collectors.toList)
  resL.clear
  for(i:0..<res.size){
  	resL.add(i,res.get(i))
  }
}

def concatAgs(List<Aggregate> atLA,List<Aggregate> atLB, List<Aggregate> resL){	
  var Stream<Aggregate> s1=atLA.stream
  var Stream<Aggregate> s2=atLB.stream
  var Stream<Aggregate> s3=Stream.concat(s1,s2)
  var List<Aggregate>res= s3.collect(Collectors.toList)
  resL.clear
  for(i:0..<res.size){
  	resL.add(i,res.get(i))
  }
}


	
}