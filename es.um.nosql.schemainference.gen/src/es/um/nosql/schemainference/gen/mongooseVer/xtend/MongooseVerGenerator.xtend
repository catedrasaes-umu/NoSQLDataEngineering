package es.um.nosql.schemainference.gen.mongooseVer.xtend

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema

import es.um.nosql.schemainference.NoSQLSchema.Entity
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Type
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Reference
import java.util.ArrayList

class MongooseVerGenerator {
  var ArrayList<Entity> entsList=new ArrayList
  EntityVersion evAux
  Entity entAux
  var int indexEl=0
  var int totalEnts=0
  
def generate (NoSQLSchema nosqlschema)
{
  for(lEnt:nosqlschema.entities){
    entAux=lEnt
  	evAux=entAux.entityversions.get(0)
  	if(evAux.root==true){
  	  indexEl++
  	  entsList.add(lEnt)
  	}
  }
  '''
  «FOR entL: entsList»
	«analyzeEnt(entL)»
  «ENDFOR»
  '''
}	
	
def analyzeEnt(Entity ent2)  {
  totalEnts++
  var int totalEntVersions=ent2.entityversions.size
  var int contVer=0;
'''
  «FOR EntityVersion entVer : ent2.entityversions»
    
    //File «ent2.name.toFirstUpper»«contVer+=1»
    var mongoose = require('mongoose');
    mongoose.connect('mongodb://localhost/dbmongoose', function(error){
    	if(error){
    	    throw error;
        }
    	else{
    		console.log('Conectado a MongoDB');
        }
    });
    	
    var «ent2.name.toFirstLower»Schema = new mongoose.Schema({	
    «var eV=ent2.entityversions.get(contVer-1)»
    «var At=eV.properties.filter(Attribute)»
    «var Ref=eV.properties.filter(Reference)»
    «var Aggreg=eV.properties.filter(Aggregate)»
    «FOR Attribute a: At»
      «printAttribute(a,a.name)»
    «ENDFOR»
    «FOR Aggregate ag2: Aggreg»
      «printAgg(ag2)»
      «checkRefAggr(ag2)»
    «ENDFOR»
    «FOR Reference r: Ref»
      «printRef(r)»
    «ENDFOR»
    },      
    {collection:'«ent2.name.toFirstLower»'});
    var «ent2.name.toFirstUpper» = mongoose.model('«ent2.name.toFirstUpper»',«ent2.name.toFirstLower»Schema);
    
    «ent2.name.toFirstUpper».find({}).
    	«FOR Attribute a: At»
    	where('«a.name»').exists().
   		«ENDFOR»    
    	«FOR Aggregate ag2: Aggreg»
    	where('«ag2.name»').exists().
    	«ENDFOR» 
    	«FOR Reference r: Ref»
    	where('«r.name»').exists().
    	«ENDFOR»         	
    	exists().exec(function(err,«ent2.name.toFirstLower»){
    		if (err) throw err;	
    		console.log(«ent2.name.toFirstLower»);
    });
    «FOR Reference r: Ref»
      «checkRefAggr(r)»
    «ENDFOR»      
  «ENDFOR»  
'''
}

def printAgg(Aggregate ag3)
  '''	«ag3.name»:{'''

def dispatch checkRefAggr(Aggregate aggr){
  if(aggr.refTo!=null)
  {
  	checkAssociation(aggr.refTo,aggr.name)
  }
}

def printRef(Reference r){
  '''	«r.name»«IF r.upperBound==-1»:	{},«ELSE»:	String,«ENDIF»'''
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
def dispatch checkAssociation(Iterable <EntityVersion> agL, String nameAg)'''
  «var ArrayList<Attribute> at = new ArrayList»
  «var ArrayList<Reference> ref = new ArrayList»
  «var ArrayList<Aggregate> aggr =new ArrayList»
  «var ArrayList<String> prims = new ArrayList»
  «var ArrayList<String> tuples = new ArrayList»
  «var ArrayList<Reference> refs=new ArrayList»
  «var ArrayList<Aggregate> ags = new ArrayList»
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
  	},  
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
  '''	«name»:	«primT.name»,'''
}

def dispatch printType(Tuple tuple, String name){
  '''	«name»:	[],'''
}

def dispatch analyzeAggregate(Aggregate ag, String name, ArrayList<Aggregate> AgL) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name)
	  if (!rAggreg)
	    {
	      AgL.add(ag)
	      '''«printAgg(ag)»'''
	      //printAgg(ag)
	    } 
}
	
def boolean reviseAggregList(ArrayList<Aggregate> ag, String name) {
	 for (i : 0 ..< ag.size) {
	    val element = ag.get(i)
	    if(element.name==name)
	    	return true
	 }
     return false
}

//is repeated reference?	
def analyzeReference(Reference ref, String name, ArrayList<Reference> RfL) {
  var boolean rRef
  rRef=analyzeRefList(RfL,name)
  if (!rRef)
    {
      RfL.add(ref)
      '''«printRef(ref)»'''
      //checkRef(ref)
    }
}
	
def boolean analyzeRefList(ArrayList<Reference> r, String name) {
 for (i : 0 ..< r.size) {
    val element = r.get(i)
    if(element.name==name)
    	return true
 }
    return false
}
	
//for abstract Type class
def dispatch analyzeAttribute(Type at2, String name, ArrayList<String> PrL,ArrayList<String> TuL) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}


def dispatch analyzeAttribute(PrimitiveType primT, String name, ArrayList<String> PrL,ArrayList<String> TuL) {
  var boolean rPrim
  rPrim=analyzePrimList(PrL,primT,name)  
  if (!rPrim){
   	PrL.add(name)
   	'''	«printType(primT,name)»'''
  }
}
	
def boolean analyzePrimList(ArrayList<String> p, PrimitiveType pr, String name) {
  for (i : 0 ..< p.size) {
    val element = p.get(i)
    if(element==name)
    	return true
  }
  return false
}
	
def dispatch analyzeAttribute(Tuple tuple, String name, ArrayList<String> PrL,ArrayList<String> TuL) {
  var boolean rTuple
  rTuple=analyzeTupleList(TuL,name)  
  if (!rTuple)
   {
   	TuL.add(name)
   	'''	«printType(tuple,name)»'''
   	//'''	«name»:	[]'''
   }
}
	
def boolean analyzeTupleList(ArrayList<String> t, String name) {
   for (i : 0 ..< t.size) {
    val element = t.get(i)
    if(element==name)
    	return true
    }
    return false
}
	
}