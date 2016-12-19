package data.generation

import Version_Diff.NoSQLDifferences
import Version_Diff.TypeDifference
import Version_Diff.TypeHint
import Version_Diff.HasField
import Version_Diff.EntityType
import java.util.Collection

class DifferencesToJSData
{
	/**
	 * Method used to generate a Struct file.
	 */
	def generateStructFunctions(NoSQLDifferences dataDifferences)
	{
		var typeList = newHashSet
		var evList = newHashSet

		for (TypeDifference typeDiff : dataDifferences.hasTypeDifferences)
		{
			evList.add(typeDiff.name)
			for (TypeHint typeHint : typeDiff.hints.filter[ hint | hint instanceof HasField && hint.withName.equals("type") && hint.withType instanceof EntityType])
				typeList.add((typeHint.withType as EntityType).type)
		}

		printStruct(dataDifferences.name, typeList, evList);
	}

	/**
	 * Recreates a Struct file in which a Json structure is initialized.
	 */
	def printStruct(String name, Collection<String> typeList, Collection<String> evList)
	'''
	/**
	 * The name of the NoSQLData model.
	 */
	var DATA_DIFF_NAME = "«name»";

	/**
	 * A DiffStruct which will store the EntityVersions and how many of them exist.
	 */
	var DiffStruct =
	{
		«FOR String type : typeList.sort SEPARATOR ', '»
			«type»:
			{
				«FOR String str : evList.filter[ ev | ev.equals(type + ev.substring(ev.lastIndexOf("_")))].sort SEPARATOR ','»
					«str»: 0
				«ENDFOR»
			}
		«ENDFOR»
	};
	'''

	/**
	 * Method used to generate an index.html file depending on the input model data being generated.
	 */
	def printIndex(String name)
	'''
	<!DOCTYPE html>
	<html>
		<head>
			<meta charset="utf-8">
			<link rel="stylesheet" type="text/css" href="css/style.css">
			<title>Data visualization</title>
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
			<script src="http://d3js.org/d3.v3.min.js"></script>
			<script type="text/javascript" src="js/gen/_STRUCT_«name».js"></script>
			<script type="text/javascript" src="js/gen/_OBJ_E_«name».js"></script>
			<script type="text/javascript" src="js/gen/_OBJ_I_«name».js"></script>
			<script type="text/javascript" src="js/lib/functions.js"></script>
			<script type="text/javascript" src="js/lib/d3_bubbles.js"></script>
			<script type="text/javascript" src="js/lib/d3_bars.js"></script>
			<script type="text/javascript" src="js/lib/d3_donuts.js"></script>
			<script type="text/javascript" src="js/lib/d3_tree.js"></script>
			<script type="text/javascript" src="js/lib/main.js"></script>
		</head>
		<body>
			<header>
				<nav>
					<ul>
						<li><h3 id="title"></h3></li>
						<li><a id="bBubbles" href="#">Bubbles</a></li>
						<li><a id="bBars" href="#">Bars</a></li>
						<li><a id="bDonuts" href="#">Donuts</a></li>
						<li><a id="bTree" href="#">Tree</a></li>
					</ul>
				</nav>
			</header>
			<div class="d3Container" id="dBubbles"></div>
			<div class="d3Container" id="dBars"></div>
			<div class="d3Container" id="dDonuts"></div>
			<div class="d3Container" id="dTree"></div>
		</body>
	</html>
	'''
}
