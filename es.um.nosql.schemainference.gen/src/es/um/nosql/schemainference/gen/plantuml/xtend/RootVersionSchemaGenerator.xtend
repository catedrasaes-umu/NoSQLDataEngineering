package es.um.nosql.schemainference.gen.plantuml.xtend
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
import java.util.List
import java.util.Map
import java.util.HashMap
import java.util.Map.Entry

class RootVersionSchemaGenerator {
  var List<Entity> entsList=new ArrayList
  EntityVersion evAux
  Entity entAux
  var int indexEl=0
  var int totalEnts=0
  var boolean root=true
  var boolean notRoot=false
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
    skinparam backgroundColor transparent 
    skinparam class { 
      BackgroundColor Blue \n
      ArrowColor Blue 
      BorderColor Red \n
      FontSize 18 \n
      FontName Arial \n
    }
	    			  
    skinparam stereotypeCBackgroundColor Blue
    skinparam stereotypeCBorderColor SpringGreen
    
    Class «ent2.name.toFirstUpper»«contVer»<<(R,Turquoise)>>{
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
     «printRef(r,ent2.name+contVer)»
     «checkRef(r)»
    «ENDFOR»
    «FOR Aggregate ag2: Aggreg»
    «var Entity entAg=ag2.refTo.get(0).eContainer as Entity»
    «checkAggr(ag2,ent2.name+contVer.toString)»
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

def printRef2(Reference r, String name){
r.name=r.name.replace("_id","").replace("id","")
'''
  «IF r.upperBound==1»	<i><color:Navy>ref «r.refTo.name» «r.name»</color>
  «ELSE»	<i><color:Navy>ref «r.refTo.name»[] «r.name»</color>
  «ENDIF»
'''
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
  if(ref.refTo!=null)
      checkReference(ref.refTo) 
}

//check Reference.refTo
def checkReference(Entity e)'''
  «var List<Attribute> at = new ArrayList»
  «var List<Reference> ref = new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> aggr =new ArrayList»
  «var List<String> prims = new ArrayList»
  «var List<String> tuples = new ArrayList»
  «var List<Aggregate> ags = new ArrayList»
  «var List<PrimitiveType> primsL=new ArrayList»
  «var List<Tuple> tuplesL=new ArrayList»
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

  Class «e.name.toFirstUpper»<<(E,Tomato)>>{
  «FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,primsL, tuplesL,prims,tuples)»
  «ENDFOR»
  «FOR Reference rf2: ref»
    «analyzeReference2(rf2,rf2.name,refs,e.name)»
  «ENDFOR»
  }
  «FOR Aggregate a3:aggr»
      «analyzeAggregate(a3,a3.name,ags,e.name)»
  «ENDFOR»
  «FOR Aggregate a4: ags»
     «checkAggr(a4)»
  «ENDFOR»
'''

def printAgg(String name, String entAg)
'''

  	«name.toFirstUpper» *--> "[1..1] «entAg.toLowerCase»" «entAg.toFirstUpper»
'''



def printAgg(Aggregate ag3, String name){
'''

  «var Entity entAg=ag3.refTo.get(0).eContainer as Entity»
  «IF ag3.upperBound==-1»
   «name.toFirstUpper» *--> "[1..*] «ag3.name.toLowerCase»" «entAg.name.toFirstUpper»
  «ELSE»
  	«name.toFirstUpper» *--> "[1..1] «ag3.name.toLowerCase»" «entAg.name.toFirstUpper»
  «ENDIF»
'''
}

def printAggs(List <EntityVersion> agL, String father, String child)
'''
  «FOR ev:agL»
  «printAgg(father,child+ev.versionId)»
  «ENDFOR»	
'''


def checkAggr(Aggregate aggr, String FatherName){
  if(aggr.refTo!=null)
  {
  	var Entity entAg=aggr.refTo.get(0).eContainer as Entity
  	var aL=aggr.refTo.toList
    '''
  	«printAggs(aL, FatherName, entAg.name)»
  	«checkAggregate(aL, entAg.name)»
  	'''
  }
}

def checkAggr(Aggregate aggr){
  if(aggr.refTo!=null)
  {
  	var Entity entAg=aggr.refTo.get(0).eContainer as Entity
  	checkAggregate(aggr.refTo.toList,aggr.name, entAg)
  }
}

//check Aggregate.refTo for root
def checkAggregate(List <EntityVersion> agL, String father)'''
  «var List<String> prims = new ArrayList»
  «var List<String> tuples = new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> ags = new ArrayList»
  «var List<PrimitiveType> primsL=new ArrayList»
  «var List<Tuple> tuplesL=new ArrayList»
  
  «FOR EntityVersion ev:agL»
  «var at=ev.properties.filter(Attribute).toList»
  «var aggr=ev.properties.filter(Aggregate).toList»

  Class «(father+ev.versionId).toFirstUpper»<<(V,BurlyWood)>>{
  «FOR Attribute at2: at»
  	«printAttribute(at2,at2.name)»
  «ENDFOR»
  }
  «FOR Aggregate a4: aggr»
    «checkAggr(a4,father+ev.versionId)»
  «ENDFOR»
 «ENDFOR»
  
'''

//check Aggregate.refTo for not root
def checkAggregate(List <EntityVersion> agL, String nameAg, Entity entAg)'''
  «var List<Attribute> at = new ArrayList»
  «var List<Aggregate> aggr =new ArrayList»
  «var List<String> prims = new ArrayList»
  «var List<String> tuples = new ArrayList»
  «var List<Reference> refs=new ArrayList»
  «var List<Aggregate> ags = new ArrayList»
  «var List<PrimitiveType> primsL=new ArrayList»
  «var List<Tuple> tuplesL=new ArrayList»
  
  «var int contAt=-1»
  «var int contRf=-1»
  «var int contAgg=-1»
  «FOR EntityVersion ev:agL»
     «var a=ev.properties.filter(Attribute)»
     «var ag=ev.properties.filter(Aggregate)»
     «FOR i:0..<a.size»
        «at.add(contAt+=1,a.get(i))»
     «ENDFOR»
     «FOR i:0..<ag.size»
       «aggr.add(contAgg+=1,ag.get(i))»
     «ENDFOR»
  «ENDFOR»

  Class «entAg.name.toFirstUpper»<<(E,Tomato)>>{
  «FOR Attribute at2: at»
  	«analyzeAttribute(at2.type,at2.name,primsL, tuplesL,prims,tuples)»
  «ENDFOR»
  }  
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
    '''	<b> «el.name»[] «name»'''
  }  
  else
	'''	<b> [] «name»'''
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
def analyzeReference2(Reference ref, String name, List<Reference> RfL, String name2) {
  var boolean rRef
  rRef=analyzeRefList(RfL,name,ref)
  if (!rRef)
    {
      RfL.add(RfL.size,ref)
      printRef2(ref,name2)
      //checkRef(ref)
    }
}
	
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
      '''	<b> «el.name»[] «name»'''
    }  
    else
     '''	<b> [] «name»'''
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