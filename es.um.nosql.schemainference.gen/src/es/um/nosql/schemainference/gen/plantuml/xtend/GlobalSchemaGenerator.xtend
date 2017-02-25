package es.um.nosql.schemainference.gen.plantuml.xtend
import es.um.nosql.schemainference.NoSQLSchema.Entity
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Type
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema
import java.util.ArrayList
import java.util.List
import java.util.Map
import java.util.HashMap
import java.util.Map.Entry

class GlobalSchemaGenerator {
  var List<Entity> entsList=new ArrayList
  EntityVersion evAux
  Entity entAux
  var int indexEl=0
  var int totalEnts=0
  //var Map<Entity, String> entNames=new HashMap
  var List<String> ents= new ArrayList
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
    «ents.clear»

    File «ent2.name.toFirstUpper»«contVer+=1»
    @startuml
    title <b> «ent2.name.toFirstUpper»«contVer»
    skinparam backgroundColor AntiqueWhite/Gold 
    skinparam class { 
      BackgroundColor PaleGreen \n
      ArrowColor Blue 
      BorderColor SeaGreen \n
      FontSize 18 \n
      FontName Courier \n
    }
	    			  
    skinparam stereotypeCBackgroundColor YellowGreen
    skinparam stereotypeCBorderColor SpringGreen
    
    Class «ent2.name.toFirstUpper»{
    «ents.add(0,ent2.name)»
    «var eV=ent2.entityversions.get(contVer-1)»
    «var At=eV.properties.filter(Attribute)»
    «var Ref=eV.properties.filter(Reference)»
    «var Aggreg=eV.properties.filter(Aggregate)»
    «FOR Attribute a: At»
    	«printAttribute(a,a.name)»
    «ENDFOR»
    }
    «FOR Reference r: Ref»
     «printRef(r,ent2.name)»
     «checkRef(r)»
    «ENDFOR»
    «FOR Aggregate ag2: Aggreg»
     «printAgg(ag2,ent2.name)»
     «checkAggr(ag2)»
    «ENDFOR»
    @enduml
  «ENDFOR»  
'''
}

def wasVisited(List<String> ents, String entName){
  for (ent : ents){
    if(ent==entName)
      return true	
  }
  return false  
}

def printAgg(Aggregate ag3, String name)'''

  «var Entity entAg=ag3.refTo.get(0).eContainer as Entity»
  «IF ag3.upperBound==-1»
   «name.toFirstUpper» *--> "[1..*] «ag3.name.toLowerCase»" «entAg.name.toFirstUpper»
  «ELSE»
  	«name.toFirstUpper» *--> "[1..1] «ag3.name.toLowerCase»" «entAg.name.toFirstUpper»
  «ENDIF»
'''

def checkAggr(Aggregate aggr){
  if(aggr.refTo!=null)
  {
  	var Entity entAg=aggr.refTo.get(0).eContainer as Entity
  	checkAggregate(aggr.refTo.toList,aggr.name, entAg)
  }
}

def printRef(Reference r, String name){
r.name=r.name.replace("_id","").replace("id","")
'''

   «IF r.upperBound==1»
   «name» --> "[1..1] «r.name»" «r.refTo.name»
  «ELSE»
   «name» --> "[1..*] «r.name»" «r.refTo.name»
  «ENDIF»
'''
}

def checkRef(Reference ref){
  var boolean visited=false
  if(ref.refTo!=null){
    visited=wasVisited(ents,ref.refTo.name)
    if(!visited){
      ents.add(ref.refTo.name)
      checkReference(ref.refTo) 
    }
    
  }
}

//check Reference.refTo
def checkReference(Entity e)'''
  «var List<Attribute> at = new ArrayList»
  «var List<Reference> ref = new ArrayList»
  «var List<Aggregate> aggr =new ArrayList»
  «var List<String> prims = new ArrayList»
  «var List<String> tuples = new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> ags = new ArrayList»
  «var int contAt=-1»
  «var int contRf=-1»  
  «var int contAgg=-1»      
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
     
  Class «e.name.toFirstUpper» {
  «FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,prims,tuples)»
  «ENDFOR»
  }  
  «FOR Reference rf2: ref»
    «analyzeReference(rf2,rf2.name,refs,e.name)»
  «ENDFOR»
  «FOR Reference rf3: refs»
    «checkRef(rf3)»
  «ENDFOR»
  «FOR Aggregate a3:aggr»
      «analyzeAggregate(a3,a3.name,ags,e.name)»
  «ENDFOR»
  «FOR Aggregate a4: ags»
     «checkAggr(a4)»
  «ENDFOR»
'''

//check Aggregate.refTo
def checkAggregate(List <EntityVersion> agL, String nameAg, Entity entAg)'''
  «var List<Attribute> at = new ArrayList»
  «var List<Reference> ref = new ArrayList»
  «var List<Aggregate> aggr =new ArrayList»
  «var List<String> prims = new ArrayList»
  «var List<String> tuples = new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> ags = new ArrayList»
  «var int contAt=-1»
  «var int contRf=-1»  
  «var int contAgg=-1»
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

  Class «entAg.name.toFirstUpper» {
  «FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,prims,tuples)»
  «ENDFOR»
  }  
  «FOR Reference rf2: ref»
    «analyzeReference(rf2,rf2.name,refs,nameAg)»
  «ENDFOR»
  «FOR Reference rf3: refs»
    «checkRef(rf3)»
  «ENDFOR»
  «FOR Aggregate ag: aggr»
      «analyzeAggregate(ag,ag.name,ags,entAg.name)»
  «ENDFOR»
  «FOR Aggregate a4: ags»
    «checkAggr(a4)»
  «ENDFOR»
'''

def printAttribute(Attribute a, String name)'''
  «printType(a.type,name)»
'''

def dispatch printType(Type at2, String name) {
  throw new UnsupportedOperationException("TODO: auto-generated method stub")
}

def dispatch printType(PrimitiveType primT, String name){
  if (primT.name=="Number"){
    var t="int"
    '''	<b> «t» «name»'''
  }  
  else
	'''	<b> «primT.name» «name»'''
}

def dispatch printType(Tuple tuple, String name){
  if (tuple.elements.size>0){
    var el=tuple.elements.get(0) as PrimitiveType
    '''	<b> «el.name»[] «name»Tuple'''
  }  
  else
	'''	<b> [] «name»Tuple'''
}

def dispatch analyzeAggregate(Aggregate ag, String name, List<Aggregate> AgL, String name3) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name,ag)
	  if (!rAggreg)
	    {
	      AgL.add(ag)
	      printAgg(ag,name3)
	    } 
	   
	}
	
	def boolean reviseAggregList(List<Aggregate> ag, String name, Aggregate Ag) {
	 for (i : 0 ..< ag.size) {
	    val element = ag.get(i)
	    if(element.name==name && compareAggregates(element,Ag))
	    	return true
	 }
     return false
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


//is repeated reference?	
def analyzeReference(Reference ref, String name, List<Reference> RfL, String name2) {
  var boolean rRef
  rRef=analyzeRefList(RfL,name,ref)
  if (!rRef)
    {
      RfL.add(RfL.size,ref)
      printRef(ref,name2)
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

def dispatch analyzeAttribute(PrimitiveType primT, String name, List<PrimitiveType> PrL,List<Tuple> TuL,List<String> pL,List<String> t) {
  var boolean rPrim
  rPrim=analyzePrimList(PrL,pL,primT,name)  
  if (!rPrim){
   	PrL.add(PrL.size,primT)
   	pL.add(pL.size,name)
   	if (primT.name=="Number"){
      var tS="int"
      '''	<b> «tS» «name»'''
    }  
    else
      '''	<b> «primT.name» «name»'''
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

def dispatch analyzeAttribute(Tuple tuple, String name, List<PrimitiveType> PrL,List<Tuple> TuL, List<String> pL,List<String> t) {
  var boolean rTuple
  rTuple=analyzeTupleList(TuL,t,tuple,name)  
  if (!rTuple)
   {
   	t.add(t.size,name)
   	TuL.add(TuL.size,tuple)
   	if (tuple.elements.size>0){
      var el=tuple.elements.get(0) as PrimitiveType
      '''	<b> «el.name»[] «name»Tuple'''
    }  
    else
     '''	<b> [] «name»Tuple'''
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
	
}