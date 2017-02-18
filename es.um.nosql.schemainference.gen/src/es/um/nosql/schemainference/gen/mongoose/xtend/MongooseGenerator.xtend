package es.um.nosql.schemainference.gen.mongoose.xtend

import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchema

import es.um.nosql.schemainference.NoSQLSchema.Entity
import es.um.nosql.schemainference.NoSQLSchema.EntityVersion
import es.um.nosql.schemainference.NoSQLSchema.Property
import es.um.nosql.schemainference.NoSQLSchema.Association
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Type
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Reference
import java.util.ArrayList

class MongooseGenerator 
{
    var ArrayList<Entity> entsList = new ArrayList
	EntityVersion eV
	var int totalEnts = 0

	def generate (NoSQLSchema nosqlschema) 
  	{
		'''
		var mongoose = require('mongoose');
		mongoose.connect('mongodb://localhost/dbmongoose', function(error){
			if(error){
		    	throw error;
		    }
		});
		«FOR entL: nosqlschema.entities.filter[e | e.entityversions.exists[ev | ev.root]]»
			«reviseEnt(entL)»
		«ENDFOR»
		'''
   	}
   		
	def reviseEnt(Entity ent2)  {
	  var ArrayList<String> PrimList = new ArrayList
      var ArrayList<String> TupleList = new ArrayList
      var ArrayList<String> RefList=new ArrayList
      var ArrayList<String> AggregList=new ArrayList
      //var ArrayList<String> propsList=new ArrayList
	  PrimList.clear
	  TupleList.clear
	  RefList.clear
  	  //propsList.clear
  	  AggregList.clear
  	  eV=ent2.entityversions.get(0)
  	  totalEnts++
  	  '''
	  «FOR EntityVersion entVer : ent2.entityversions»
	  «IF entVer.root==true»
	    «IF entVer.versionId==1»
	    
	    var «ent2.name.toFirstLower»Schema = new mongoose.Schema({	
	  	«ENDIF» «reviseEntVer(entVer, PrimList, TupleList, RefList,AggregList)»
	  «ENDIF»
	  «ENDFOR»   
	  «IF eV.root==true && eV.versionId==1»
	  	},
	  	{collection: '«ent2.name.toFirstLower»'});
	  	var «ent2.name.toFirstUpper» = mongoose.model('«ent2.name.toFirstUpper»',«ent2.name.toFirstLower»Schema);
	  	
	  	«ent2.name.toFirstUpper».find(function(err,«ent2.name.toFirstLower»){
	  		if (err) return console.error(err);
	  		var stringVar="\n«ent2.name.toFirstUpper»:";
	  		console.log(stringVar);
	  		console.log(«ent2.name.toFirstLower»);
	  	«IF totalEnts==entsList.size»	mongoose.disconnect();
	  	«ENDIF»
	  	});
      «ENDIF»
	  '''
	}
	
	def reviseEntVer (EntityVersion entVer2,ArrayList<String> PrL,ArrayList<String> TuL,ArrayList<String> RfL,ArrayList<String> AgL) '''
		«FOR Property prop : entVer2.properties»
			«reviseProp(prop,prop.name, PrL, TuL, RfL, AgL)»
		«ENDFOR»
	'''
	
	//for abstract Property class
	def dispatch reviseProp(Property p, String name,ArrayList<String> PrL,ArrayList<String> TuL,ArrayList<String> RfL,ArrayList<String> AgL) {
	  throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def dispatch reviseProp(Association ass, String name,ArrayList<String> PrL,ArrayList<String> TuL,ArrayList<String> RfL,ArrayList<String> AgL) '''
	     «reviseAssociation(ass, name, ass.upperBound,RfL,AgL)»
	'''
	
	def dispatch reviseProp(Attribute at, String name,ArrayList<String> PrL,ArrayList<String> TuL,ArrayList<String> RfL,ArrayList<String> AgL) '''
	     «reviseAttribute(at.type,name,PrL,TuL)»
	'''
	
	//for abstract association class
	def dispatch reviseAssociation(Association ass2, String name, int upBound, ArrayList<String> RfL,ArrayList<String> AgL) {
	  throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	def dispatch reviseAssociation(Aggregate ag, String name, int upBound,ArrayList<String> RfL,ArrayList<String> AgL) {
	  var boolean rAggreg
	  rAggreg=reviseAggregList(AgL,name)
	  if (!rAggreg)
	    {
	      AgL.add(name)
	    } 
	   var int totalAg=0
	   var EntityVersion entVaG=ag.refTo.get(0)
	   '''
	   «FOR entRef: ag.refTo»
	      «var ArrayList<String> prAgL = new ArrayList»
	      «var ArrayList<String> tuAgL = new ArrayList»
	      «var ArrayList<String> rfAgL=new ArrayList»
	      «var ArrayList<String> agAgL=new ArrayList»
	      «IF entRef.versionId==1»
	      «name»: {
	  	  «ENDIF»«reviseEntVer(entRef,prAgL,tuAgL,rfAgL,agAgL)»
		  «IF entRef.versionId==ag.refTo.size»
		  },
		  «ENDIF»  	 
	  «ENDFOR»   
	  '''
	}
	
	def boolean reviseAggregList(ArrayList<String> ag, String name) {
	 for (i : 0 ..< ag.size) {
	    val element = ag.get(i)
	    if(element==name)
	    	return true
	 }
     return false
	}
	
	def dispatch reviseAssociation(Reference ref, String name, int upBound, ArrayList<String> RfL,ArrayList<String> AgL) {
	  var boolean rRef
	  rRef=reviseRefList(RfL,name)
	  if (!rRef)
	    {
	      RfL.add(name)
	     '''	«name»«IF upBound==-1» : {},«ELSE» : String,«ENDIF»'''
	    }
	}
	
	def boolean reviseRefList(ArrayList<String> r, String name) {
	 for (i : 0 ..< r.size) {
	    val element = r.get(i)
	    if(element==name)
	    	return true
	 }
     return false
	}
	
	//for abstract Type class
	def dispatch reviseAttribute(Type at2, String name, ArrayList<String> PrL,ArrayList<String> TuL) {
	  throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	def dispatch reviseAttribute(PrimitiveType primT, String name, ArrayList<String> PrL,ArrayList<String> TuL) {
      var boolean rPrim
      rPrim=revisePrimList(PrL,primT,name)  
      if (!rPrim)
       {
       	PrL.add(name)
       	/*var String prop
        prop=name+": "+primT.typeName
	    propsList.add(prop)*/
        '''	«name»: «primT.name»,'''
       }
	}
	
	def boolean revisePrimList(ArrayList<String> p, PrimitiveType pr, String name) {
	 for (i : 0 ..< p.size) {
	    val element = p.get(i)
	    if(element==name)
	    	return true
	 }
     return false
	}
	
	def dispatch reviseAttribute(Tuple tuple, String name, ArrayList<String> PrL,ArrayList<String> TuL) {
	var boolean rTuple
	  rTuple=TuL.contains(name)
	  if (!rTuple)
	   {
	   	TuL.add(name)
	    '''	«name»: [],'''
	   }
	}
}//end program