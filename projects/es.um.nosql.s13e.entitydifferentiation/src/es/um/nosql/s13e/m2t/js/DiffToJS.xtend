package es.um.nosql.s13e.m2t.js

import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiation
import es.um.nosql.s13e.util.emf.ResourceManager
import es.um.nosql.s13e.EntityDifferentiation.EntityDifferentiationPackage
import es.um.nosql.s13e.NoSQLSchema.NoSQLSchemaPackage
import java.io.File
import es.um.nosql.s13e.EntityDifferentiation.EntityDiffSpec
import java.util.List
import java.io.PrintStream
import es.um.nosql.s13e.EntityDifferentiation.PropertySpec
import org.eclipse.xtext.xbase.lib.Functions.Function1
import es.um.nosql.s13e.NoSQLSchema.Property
import es.um.nosql.s13e.NoSQLSchema.PrimitiveType
import es.um.nosql.s13e.NoSQLSchema.Attribute
import es.um.nosql.s13e.NoSQLSchema.Type
import es.um.nosql.s13e.NoSQLSchema.Tuple
import es.um.nosql.s13e.NoSQLSchema.Reference
import es.um.nosql.s13e.NoSQLSchema.Aggregate
import es.um.nosql.s13e.NoSQLSchema.Entity
import java.util.Collections
import es.um.nosql.s13e.EntityDifferentiation.EntityVariationProp

class DiffToJS
{
	var modelName = "";

	final val EXACT_TYPE = true
	final val DUCK_TYPE = !EXACT_TYPE

	final val SPECIAL_TYPE_IDENTIFIER = "type"

	def static void main(String[] args)
    {
		if (args.length < 1)
		{
			System.out.println("Usage: DiffToJS model [outdir]")
			System.exit(-1)
		}

        val inputModel = new File(args.head)
        val ResourceManager rm = new ResourceManager(EntityDifferentiationPackage.eINSTANCE,
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

		val diff_to_js = new DiffToJS()
		val outFile = outputDir.toPath().resolve(td.name + ".js").toFile()
		val outFileWriter = new PrintStream(outFile)

        outFileWriter.println(diff_to_js.generate(td))
        outFileWriter.close()

        System.exit(0)
    }

	/**
	 * Method used to generate an Inclusive/Exclusive differences file for a NoSQLDifferences object.
	 */
	def generate(EntityDifferentiation diff)
	{
		modelName = diff.name;

		'''
		'use strict'

		var «diff.name» = {

			name: "«diff.name»",
			«genSpecs(diff.entityDiffSpecs)»
		}

		module.exports = «diff.name»;
		'''
	}

	def genSpecs(List<EntityDiffSpec> list) '''
		«FOR EntityDiffSpec de : list SEPARATOR ','»
			«genEntityDiffs(de)»
		«ENDFOR»
	'''

	def genEntityDiffs(EntityDiffSpec spec) '''
		«FOR evp : spec.entityVariationProps SEPARATOR ','»
			«genEntityVariationDiff(evp, spec)»
		«ENDFOR»
	'''

	def genEntityVariationDiff(EntityVariationProp evp, EntityDiffSpec spec) {
		val entityVariationName = spec.entity.name.toFirstUpper + "_" + evp.entityVariation.variationId

		'''
		«entityVariationName»: {
			name: "«entityVariationName»",
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

	def generateHints(EntityVariationProp evp, EntityDiffSpec spec, boolean exact) {
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
		            «modelName».«(rt.eContainer as Entity).name»_«rt.variationId».isOfExactType(e)
		            «ENDFOR»);
		        })
		«ELSE»
		«var refToEV = a.refTo.get(0)»
		(typeof obj.«a.name» === 'object') && !(obj.«a.name».constructor === Array)
		    && «modelName».«(refToEV.eContainer as Entity).name»_«refToEV.variationId».isOfExactType(obj.«a.name»)
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