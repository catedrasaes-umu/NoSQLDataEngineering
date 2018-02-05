/**
 * Base name of the comparative functions.
 */
var FUNCTION_BASE_NAME = "isOfExactType_";

/**
 * Flag used to denote an EXCLUSIVE comparison.
 */
var EXCLUSIVE_DIFF = true;

/**
 * Flag used to denote an INCLUSIVE comparison.
 */
var INCLUSIVE_DIFF = false;

/**
 * Method used to check an inclusive object type.
 * @param obj: The object whose type we want to know.
 * @return Type: All the possible types of the object in an array.
 */
function isOfInclusiveType (obj)
{
	return isOfType(obj, INCLUSIVE_DIFF);
}

/**
 * Method used to check an exclusive object type.
 * @param obj: The object whose type we want to know.
 * @return Type: All the possible types of the object in an array.
 */
function isOfExclusiveType (obj)
{
	return isOfType(obj, EXCLUSIVE_DIFF);
}

/**
 * Private method used to check an object type.
 * @param obj: The object whose type we want to know.
 * @param exclusive_bool: True if the comparison must be exclusive, false if it must be inclusive.
 * @return Type: All the possible types of the object in an array.
 */
function isOfType (obj, exclusive_bool)
{
	var DiffMethods = exclusive_bool ? DiffMethodsExclusive : DiffMethodsInclusive;

	var response = [];

	for (var i in DiffMethods)
		if (DiffMethods[i](obj))
			response.push(i.replace(FUNCTION_BASE_NAME, ""));

	return response;
};

/**
 * Method used to check if all the elements of a given array are of a certain type.
 * @param array: The array to be checked.
 * @param type: The type that must be compared with each element type.
 * @return: True if all the elements of the given array are of the given type, false otherwise.
 */
function checkAllOf (array, type)
{
	var funcToApply;

	switch (type)
	{
		case "string": case "String": { funcToApply = isString; break; }
		case "int": { funcToApply = isInt; break; }
		case "float": { funcToApply = isFloat; break; }
		case "boolean": case "bool": { funcToApply = isBoolean; break; }
		case "object": { funcToApply = isObject; break; }
	}

	for (var i = 0; i < array.length; i++)
	{
		if (array[i].constructor === Array)
		{
			if (!checkAllOf(array[i], type))
				return false;
		}
		else if (!funcToApply(array[i]))
		{
			return false;
		}
	}
	return true;
};

/**
 * Method used to check if a given element is a string.
 * @param element: The element to be checked.
 * @return: True if the element is a string, false otherwise.
 */
function isString (element)
{
	return typeof element === "string";
};

/**
 * Method used to check if a given element is an int.
 * @param element: The element to be checked.
 * @return: True if the element is an int, false otherwise.
 */
function isInt (element)
{
	return typeof element === "number" && element % 1 === 0;
};

/**
 * Method used to check if a given element is a float.
 * @param element: The element to be checked.
 * @return: True if the element is a float, false otherwise.
 */
function isFloat (element)
{
	return typeof element === "number" && element % 1 !== 0;
};

/**
 * Method used to check if a given element is a boolean.
 * @param element: The element to be checked.
 * @return: True if the element is a boolean, false otherwise.
 */
function isBoolean (element)
{
	return typeof element === "boolean" && (element === true || element === false);
};

/**
 * Method used to check if a given element is an object.
 * @param element: The element to be checked.
 * @return: True if the element is an object, false otherwise.
 */
function isObject (element)
{
	return typeof element === "object";
};

/**
 * Method used to transform a given JSON input object into another JSON object with a different structure so it is suitable as an input for some of the D3 drawing methods.
 * @param element: The JSON element to be transformed.
 * @return: A D3 JSON object.
 */
function transformIntoD3Json (element)
{
	var d3Json = {"name": DATA_DIFF_NAME, "children": []};

	for (var field in element)
	{
		var newField = {"name": field, "children": []};

		for (var subField in element[field])
			newField.children.push({"name": subField, "size": element[field][subField]});

		d3Json.children.push(newField);
	}

	return d3Json;
};

/**
 * Method used to transform a given JSON input object into another JSON object with a different structure so it is suitable as an input for some of the D3 drawing methods.
 * @param element: The JSON element to be transformed.
 * @return: A D3 JSON object.
 */
function transformIntoD3Csv (element)
{
	var d3Csv = [];
	var maxVersion = getMaxVersion(element);

	for (var field in element)
	{
		var newField = {"Entity": field};

		for (var i = 1; i <= maxVersion; i++)
			newField["Version " + i] = 0;

		for (var subField in element[field])
			newField["Version " + subField.substring(subField.lastIndexOf("_") + 1)] = element[field][subField];

		d3Csv.push(newField);
	}

	return d3Csv;
};

/**
 * Method used to transform a given JSON input object into another JSON object with a different structure so it is suitable as an input for the D3 Tree drawing process.
 * @param element: The JSON element to be transformed.
 * @return: A D3 JSON object.
 */
function transformIntoD3ExtendedJson (element)
{
	var d3Json = {"name": DATA_DIFF_NAME, "children": []};

	for (var field in element)
	{
		var newField = {"name": field, "children": []};
		var counter = 0;

		for (var subField in element[field])
			counter += element[field][subField];

		for (var subField in element[field])
		{
			var numInstances = element[field][subField];
			if (numInstances > 0)
			{
				var newFieldChildren = {"name": subField, "children": []};
				newField.children.push(newFieldChildren);
				var newNewField = {"name": "Instances: " + numInstances + " ( " + numInstances + " / " + counter + " ) ( " + (numInstances/counter * 100).toFixed(2) + "% )", "size": 1};
				newFieldChildren.children.push(newNewField);
			}
			else
			{
				var newFieldChildren = {"name": subField, "size": 0};
				newField.children.push(newFieldChildren);
			}
		}
		d3Json.children.push(newField);
	}

	return d3Json;
};

/**
 * Private method used to get the maximum version number of a JSON object with entityVersions defined on it.
 * @param element: The element of which we want to know the maximum version.
 * @return: The maximum version found on the JSON object.
 */
function getMaxVersion (element)
{
	var maxVersion = 1;

	for (var field in element)
		for (var subField in element[field])
			if (parseInt(subField.substring(subField.lastIndexOf("_") + 1)) > maxVersion)
				maxVersion = parseInt(subField.substring(subField.lastIndexOf("_") + 1));

	return maxVersion;
};
