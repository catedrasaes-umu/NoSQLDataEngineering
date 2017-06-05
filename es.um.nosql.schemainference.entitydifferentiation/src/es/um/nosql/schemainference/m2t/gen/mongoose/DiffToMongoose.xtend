package es.um.nosql.schemainference.m2t.gen.mongoose

import es.um.nosql.schemainference.entitydifferentiation.EntityDifferentiation
import es.um.nosql.schemainference.util.emf.ResourceManager
import es.um.nosql.schemainference.entitydifferentiation.EntitydifferentiationPackage
import es.um.nosql.schemainference.NoSQLSchema.NoSQLSchemaPackage
import java.io.File
import es.um.nosql.schemainference.entitydifferentiation.EntityDiffSpec
import java.util.List
import es.um.nosql.schemainference.entitydifferentiation.EntityVersionProp
import java.io.PrintStream
import es.um.nosql.schemainference.entitydifferentiation.PropertySpec
import org.eclipse.xtext.xbase.lib.Functions.Function1
import es.um.nosql.schemainference.NoSQLSchema.Property
import es.um.nosql.schemainference.NoSQLSchema.PrimitiveType
import es.um.nosql.schemainference.NoSQLSchema.Attribute
import es.um.nosql.schemainference.NoSQLSchema.Type
import es.um.nosql.schemainference.NoSQLSchema.Tuple
import es.um.nosql.schemainference.NoSQLSchema.Reference
import es.um.nosql.schemainference.NoSQLSchema.Aggregate
import es.um.nosql.schemainference.NoSQLSchema.Entity
import java.util.Collections
import java.util.HashMap
import java.util.Set

class DiffToMongoose
{
	var modelName = "";

	final val EXACT_TYPE = true
	final val DUCK_TYPE = !EXACT_TYPE

	final val SPECIAL_TYPE_IDENTIFIER = "type"
	
	// list of entities
	List<Entity> entities
	
	HashMap<Entity, Set<Entity>> entityDeps
	HashMap<Entity, Set<Entity>> inverseEntityDeps
	HashMap<Entity, EntityDiffSpec> diffByEntity
	
	static File outputDir

	def static void main(String[] args)
    {
		if (args.length < 1)
		{
			System.out.println("Usage: DiffToMongoose model [outdir]")
			System.exit(-1)
		}

        val inputModel = new File(args.head)
        val ResourceManager rm = new ResourceManager(EntitydifferentiationPackage.eINSTANCE,
        	NoSQLSchemaPackage.eINSTANCE)
        rm.loadResourcesAsStrings(inputModel.getPath())
        val EntityDifferentiation td = rm.resources.head.contents.head as EntityDifferentiation

		outputDir = new File(if (args.length > 1) args.get(1) else ".")
								.toPath().resolve(td.name).toFile()
		// Create destination directory if it does not exist
		outputDir.mkdirs()
        System.out.println("Generating Javascript for "
        					+ inputModel.getPath()
        					+ " in "
        					+ outputDir.getPath())

		val diff_to_mongoose = new DiffToMongoose()
		writeToFile(td.name+".js", diff_to_mongoose.generate(td))
		
        System.exit(0)
    }

	def static void writeToFile(String filename, CharSequence toWrite)
	{
		val outFile = outputDir.toPath().resolve(filename).toFile()
		val outFileWriter = new PrintStream(outFile)
        outFileWriter.print(toWrite)
        outFileWriter.close()
	}

	/**
	 * Method used to generate an Inclusive/Exclusive differences file for a NoSQLDifferences object.
	 */
	def generate(EntityDifferentiation diff)
	{
		modelName = diff.name;
		diffByEntity = newHashMap(diff.entityDiffSpecs.map[ed | ed.entity -> ed])

		// Calc dependencies between entities
		val order = calcDeps(diff)

		order.forEach[e | writeToFile(schemaFileName(e), generateSchema(e)) ]
	
		'''
		'use strict'

		var «diff.name» = {

			name: "«diff.name»",
			«genSpecs(diff.entityDiffSpecs)»
		}

		module.exports = «diff.name»;
		'''
	}

	def generateSchema(Entity e) {
		''''''
	}

	def schemaFileName(Entity e) {
		e.name + "-schema.js"
	}
	
		
	def calcDeps(EntityDifferentiation diff) 
	{
		entities = diff.entityDiffSpecs.map[entity]

		entityDeps = newHashMap(entities.map[e | e -> depListFor(e)])
		inverseEntityDeps = newHashMap(entities.map[e | 
			e -> entities.filter[e2 | entityDeps.get(e2).contains(e)].toSet
		])
		
		// Implement a topological order, Khan's algorithm
		// https://en.wikipedia.org/wiki/Topological_sorting#Kahn.27s_algorithm
		var order = topOrder()
		
		order.forEach[e | System.out.println(e.name)]
		order
	}
	
	// Get the first level of dependencies for an Entity
	def Set<Entity> depListFor(Entity entity)
	{
		entity.entityversions.map[ev | 
			ev.properties.filter[p |
				p instanceof Aggregate
			].map[p | 
				(p as Aggregate).refTo.map[ev2 | ev2.eContainer as Entity]
			].flatten
		].flatten.toSet
	}

	def List<Entity> topOrder()
	{
		depListRec(
			entityDeps.filter[k, v| v.empty].keySet,
			newLinkedList(),
			newHashSet()
		)
	}

	def List<Entity> depListRec(Set<Entity> to_consider, List<Entity> top_order, Set<Entity> seen)
	{
		// End condition
		if (to_consider.isEmpty)
			top_order
		else
		{
			// Recursive
			val e = to_consider.head
			val to_consider_ = to_consider.tail.toSet
			
			// Add current node (no dependencies to cover)
			top_order.add(e)
			seen.add(e)
			
			val dependent = inverseEntityDeps.get(e)
			to_consider_.addAll(
				dependent.filter[ d | 
					seen.containsAll(entityDeps.get(d))
				])			
			
			depListRec(to_consider_, top_order, seen)
		}
	}

	def genSpecs(List<EntityDiffSpec> list) '''
		«FOR EntityDiffSpec de : list SEPARATOR ','»
			«genEntityDiffs(de)»
		«ENDFOR»
	'''

	def genEntityDiffs(EntityDiffSpec spec) '''
		«FOR evp : spec.entityVersionProps SEPARATOR ','»
			«genEntityVersionDiff(evp, spec)»
		«ENDFOR»
	'''

	def genEntityVersionDiff(EntityVersionProp evp, EntityDiffSpec spec) {
		val entityVersionName = spec.entity.name.toFirstUpper + "_" + evp.entityVersion.versionId

		'''
		«entityVersionName»: {
			name: "«entityVersionName»",
			isOfExactType: function (obj)
			{
			    var b = true;
				«generateHints(evp, spec, EXACT_TYPE)»
				return b;
			},
			isOfType: function (obj)
			{
			    var b = true;
				«generateHints(evp, spec, DUCK_TYPE)»
		        return b;
			}
		}'''
	}

	def generateHints(EntityVersionProp evp, EntityDiffSpec spec, boolean exact) {
	    var propsToGenerate = spec.commonProps + evp.propertySpecs
	    var propsToGenerateNot = Collections.<PropertySpec>emptyList as Iterable<PropertySpec>

	    val f = [PropertySpec p | p.property.name.equalsIgnoreCase(SPECIAL_TYPE_IDENTIFIER)]
	    val notf = [Function1<PropertySpec,Boolean> q | [ PropertySpec v | !q.apply(v)]]

	    var typeCheck = ""
	    if (propsToGenerate.exists(f))
	    {
	       typeCheck += genEntityNameCheck(SPECIAL_TYPE_IDENTIFIER, spec.entity.name).toString
	       propsToGenerate = propsToGenerate.filter(notf.apply(f))
	    }

	    // Add the not checks
		if (exact == EXACT_TYPE)
			propsToGenerateNot = evp.notProps

		'''
		«IF !typeCheck.empty»b = b && «typeCheck»;«ENDIF»
		«FOR p : propsToGenerate»
			b = b && «genProp(p)»;
		«ENDFOR»
		«IF exact == EXACT_TYPE && !propsToGenerateNot.empty»
		««« As we only need the name, avoid repeated names of properties here.»
		«FOR p : propsToGenerateNot.map[p | p.property.name].toSet»
			b = b && «genNotPropForPropName(p)»;
		«ENDFOR»
		«ENDIF»
		'''
    }

    def genEntityNameCheck(String att, String entity_name)
		'''("«SPECIAL_TYPE_IDENTIFIER»" in obj) && (obj.«SPECIAL_TYPE_IDENTIFIER».match(/«entity_name»/i) ? true : false)'''

    def genProp(PropertySpec p)
    {
		if (p.needsTypeCheck)
			'''("«p.property.name»" in obj) && «genTypeCheck(p.property)»'''
		else
			'''("«p.property.name»" in obj)'''
	}

    def genNotPropForPropName(String p)
		'''!("«p»" in obj)'''

	def dispatch genTypeCheck(Property p) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	def dispatch genTypeCheck(Aggregate a) '''
		«IF a.lowerBound == 0»
		(obj.«a.name».constructor === Array) &&
		    obj.«a.name».every(function(e)
		        { return (typeof e === 'object') && !(e.constructor === Array)
		            && («FOR rt : a.refTo SEPARATOR " || "»
		            «modelName».«(rt.eContainer as Entity).name»_«rt.versionId».isOfExactType(e)
		            «ENDFOR»);
		        })
		«ELSE»
		«var refToEV = a.refTo.get(0)»
		(typeof obj.«a.name» === 'object') && !(obj.«a.name».constructor === Array)
		    && «modelName».«(refToEV.eContainer as Entity).name»_«refToEV.versionId».isOfExactType(obj.«a.name»)
		«ENDIF»
	'''

	def dispatch genTypeCheck(Reference r) '''
		«IF r.lowerBound == 0»
		(obj.«r.name».constructor === Array) &&
		    (obj.«r.name».every(function(e) { return typeof e === "number";})
		     ||
		     obj.«r.name».every(function(e) { return typeof e === "string";}))
		«ELSE»
		((typeof obj.«r.name» === "number") || (typeof obj.«r.name» === "string"))
		«ENDIF»
	'''


	def dispatch genTypeCheck(Attribute a) {
		genTypeCheckLowLevel(a.type, "obj." + a.name);
	}

	def dispatch genTypeCheckLowLevel(Type type, String name) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	private def isInt(String type) { #["int", "integer", "number"].contains(type) }
	private def isFloat(String type) { #["float", "double"].contains(type) }
	private def isBoolean(String type) { #["boolean", "bool"].contains(type) }

	def dispatch CharSequence genTypeCheckLowLevel(PrimitiveType type, String name) {
		switch typeName : type.name.toLowerCase {
			case "string" : '''(typeof «name» === "string")'''
			case typeName.isInt : '''(typeof «name» === "number") && («name» % 1 === 0)'''
			case typeName.isFloat :  '''(typeof «name» === "number") && !(«name» % 1 === 0)'''
			case typeName.isBoolean : '''(typeof «name» === "boolean")'''
			default: ''''''
		}
	}

	def dispatch CharSequence genTypeCheckLowLevel(Tuple type, String name) {
	    '''(«name».constructor === Array) && («name».length === «type.elements.size»)
	    «IF type.elements.size != 0»
	    &&
	    «var i = 0»
	    «FOR t : type.elements SEPARATOR " && "»
	    «genTypeCheckLowLevel(t, name + '[' + i++ + ']')»
	    «ENDFOR»
	    «ENDIF»'''
	}
}
