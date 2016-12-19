package data.generation

import Version_Diff.NoSQLDifferences
import Version_Diff.TypeDifference
import Version_Diff.TypeHint
import Version_Diff.HasField
import Version_Diff.HasNotField
import Version_Diff.Version_DiffFactory
import org.eclipse.emf.common.util.EList
import Version_Diff.EntityType
import Version_Diff.HomogeneousTupleType
import Version_Diff.HeterogeneousTupleType
import Version_Diff.AggregateType
import Version_Diff.ReferenceType
import Version_Diff.FieldType
import Version_Diff.PrimitiveType

class DifferencesToJSObj
{
	// If the hint is an instance of HasField, generate code.
	// Else the hint is instance of HasNotField. In that case, generate code if and only if we are generating exclusive comparisons.
	/**
	 * Method used to generate an Inclusive/Exclusive differences file for a NoSQLDifferences object.
	 */
	def generateObjFunctions(NoSQLDifferences dataDifferences, boolean GENERATE_INCLUSIVE_COMPARISONS)
	'''
		var DiffMethods«IF GENERATE_INCLUSIVE_COMPARISONS»Inclusive«ELSE»Exclusive«ENDIF» =
		{
			«FOR TypeDifference typeDiff : dataDifferences.hasTypeDifferences SEPARATOR ','»
				isOfExactType_«typeDiff.name.toFirstUpper»: function (obj)
				{
					«FOR TypeHint hint : typeDiff.hints»
						«IF hint instanceof HasField || !GENERATE_INCLUSIVE_COMPARISONS»
							«generateHintTest(hint)»
						«ENDIF»
					«ENDFOR»

					return true;
				}

			«ENDFOR»
		};
	'''

	/**
	 * Template method.
	 */
	def dispatch generateHintTest(TypeHint hint)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	/**
	 * Method used to generate a HasField Hint code block.
	 */
	def dispatch generateHintTest(HasField hint)
	'''
		if (!("«hint.withName»" in obj) || «generateTypeCheck(hint.withName, hint.withType)»)
			return false;
	'''

	/**
	 * Method used to generate a HasNotField Hint code block.
	 */
	def dispatch generateHintTest(HasNotField hint)
	'''
		if ("«hint.withName»" in obj && !(«generateTypeCheck(hint.withName, hint.withType)»))
			return false;
	'''

	/**
	 * Template method.
	 */
	def dispatch generateTypeCheck(String name, FieldType type)
	{
		throw new UnsupportedOperationException("TODO: auto-generated method stub");
	}

	/**
	 * Method used to generate code for a Primitive Type comparison.
	 */
	def dispatch CharSequence generateTypeCheck(String name, PrimitiveType typePrimitive)
	{
		if (isInt(typePrimitive.type))
			printInt(name)
		else if (isFloat(typePrimitive.type))
			printFloat(name)
		else if (isBoolean(typePrimitive.type))
			printBoolean(name)
		else if (isString(typePrimitive.type))
			printString(name)
		else
			throw new UnsupportedOperationException("Unsupported primitive type.")
	}

	/**
	 * Method used to generate code for a Entity Type comparison.
	 */
	def dispatch CharSequence generateTypeCheck(String name, EntityType EntityType)
	{
		printType(name, EntityType.type)
	}

	/**
	 * Method used to generate code for a Homogeneous Tuple Type comparison.
	 */
	def dispatch CharSequence generateTypeCheck(String name, HomogeneousTupleType TupleType)
	'''!(obj.«name».constructor === Array) || (!checkAllOf(obj.«name», "«TupleType.type»"))'''

	/**
	 * Method used to generate code for a Heterogeneous Tuple Type comparison.
	 */
	def dispatch CharSequence generateTypeCheck(String name, HeterogeneousTupleType TupleType)
	'''!(obj.«name».constructor === Array) || !(obj.«name».length === «TupleType.type.size»)
	«var i = 0»
	«FOR String sType : TupleType.type»
		«IF sType.startsWith("Tuple[") && sType.endsWith("]")»
			«var tTuple = Version_DiffFactory.eINSTANCE.createHeterogeneousTupleType()»
			«fillTupleList(tTuple.type, sType.substring(6, sType.length - 1))»
			|| «generateTypeCheck(name + "[" + i++ + "]", tTuple)»
		«ELSE»
			«var tPrimitive = Version_DiffFactory.eINSTANCE.createPrimitiveType()»
			«tPrimitive.setType(sType)»
			|| «generateTypeCheck(name + "[" + i++ + "]", tPrimitive)»
		«ENDIF»
	«ENDFOR»'''

	// Remember we previously saved the parent entity followed by a "_" and the versionId so we can now make a function call based on that string following the string "isOfExactType_".
	/**
	 * Method used to generate code for an Aggregate Type comparison.
	 */
	def dispatch CharSequence generateTypeCheck(String name, AggregateType typeAggregate)
	'''(typeof obj.«name» === "object" && !(obj.«name» instanceof Array) && («IF typeAggregate.lowerBound > 1»true || «ENDIF»!this.isOfExactType_«typeAggregate.type.get(0)»(obj.«name»)))
		|| (obj.«name».constructor === Array && («IF typeAggregate.lowerBound >= 0»«typeAggregate.lowerBound» > obj.«name».size || «ENDIF»«IF typeAggregate.upperBound >= 0»«typeAggregate.upperBound» < obj.«name».size || «ENDIF»!checkAllOf(obj.«name», "object")
		«var i = 0»
		«FOR String sAggr : typeAggregate.type»
			|| obj.«name»[«i»] == null || !this.isOfExactType_«sAggr»(obj.«name»[«i++»])
		«ENDFOR»)) || (typeof obj.«name» !== "object" && obj.«name».constructor !== Array)'''

	/**
	 * Method used to generate code for a Reference Type comparison.
	 */
	def dispatch CharSequence generateTypeCheck(String name, ReferenceType typeReference)
	'''(typeof obj.«name» === "string" «IF typeReference.lowerBound <= 1»&& false«ENDIF»)
		|| (obj.«name».constructor === Array && («IF typeReference.lowerBound >= 0»«typeReference.lowerBound» > obj.«name».size || «ENDIF»«IF typeReference.upperBound >= 1»«typeReference.upperBound» < obj.«name».size || «ENDIF»!checkAllOf(obj.«name», "string"))
		|| (typeof obj.«name» !== "string" && obj.«name».constructor !== Array))'''

	/**
	 * Method used to check if a string type is an "int".
	 */
	def boolean isInt(String type)
	{
		return type.equals("Int") || type.equals("int");
	}

	/**
	 * Method used to check if a string type is a "float" or equivalent.
	 */
	def boolean isFloat(String type)
	{
		return type.equals("Float") || type.equals("float") || type.equals("Double") || type.equals("double");
	}

	/**
	 * Method used to check if a string type is a "boolean" value.
	 */
	def boolean isBoolean(String type)
	{
		return type.equals("Boolean") || type.equals("boolean") || type.equals("bool");
	}

	/**
	 * Method used to check if a string type is a "string".
	 */
	def boolean isString(String type)
	{
		return type.equals("String") || type.equals("string");
	}

	/**
	 * Method used to generate code for an Integer comparison.
	 */
	def printInt(String name)
	'''!(typeof obj.«name» === "number") || !(obj.«name» % 1 === 0)'''

	/**
	 * Method used to generate code for a Float comparison.
	 */
	def printFloat(String name)
	'''!(typeof obj.«name» === "number") || (obj.«name» % 1 === 0)'''

	/**
	 * Method used to generate code for a Boolean comparison.
	 */
	def printBoolean(String name)
	'''!(typeof obj.«name» === "boolean") || ((obj.«name» !== true) && (obj.«name» !== false))'''

	/**
	 * Method used to generate code for a String comparison.
	 */
	def printString(String name)
	'''!(typeof obj.«name» === "string")'''

	/**
	 * Method used to generate code for a Type comparison.
	 */
	def printType(String name, String type)
	'''!(typeof obj.«name» === "string") || (obj.«name» !== "«type»")'''

	/**
	 * Method used to fill a String list with all the elements contained in a TypeChain tuple such as Tuple[Tuple[int, int], int] into [int, int, int].
	 */
	def void fillTupleList(EList<String> list, String typeChain)
	{
		var types = typeChain;
		if (types.startsWith(";"))
			types = types.substring(1);

		if (types.startsWith("Tuple["))
		{
			var auxVar = types.substring(6);
			var keyBalance = 1;
			var keepLooping = true;

			for (var i = 0; i < auxVar.length && keepLooping; i++)
			{
				switch (auxVar.charAt(i).toString)
				{
					case "[": keyBalance++
					case "]": keyBalance--
				}
				if (keyBalance == 0)
				{
					list.add("Tuple[" + auxVar.substring(0, i + 1));
					if (i + 1 < auxVar.length)
					{
						fillTupleList(list, auxVar.substring(i + 1, auxVar.length))
					}
					keepLooping = false;
				}
			}
		}
		else
		{
			if (types.contains(";"))
			{
				list.add(types.substring(0, types.indexOf(";")));
				fillTupleList(list, types.substring(types.indexOf(";") + 1));
			}
			else
			{
				list.add(types);
			}
		}
	}
}
