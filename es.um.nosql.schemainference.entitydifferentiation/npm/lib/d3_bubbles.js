/**
 * Method used to draw a D3 Bubbles chart on a given div given a Differences Structure such the one shown on the _STRUCT_.js file.
 * @param DiffStruct: The structure to transform to D3 format and draw.
 * @param divId: The div in which the D3 chart will be drawn.
 */
function drawD3Bubbles(DiffStruct, divId)
{
	var jsonObj = transformIntoD3Json(DiffStruct);
	var diameter = (screen.width + screen.height) / 3;
	var margin = 20;
	var format = d3.format(",d");
	var pack = d3.layout.pack()
		.size([diameter - 4, diameter - 4])
		.value(function(d) { return d.size; });
	var svg = d3.select(divId).append("svg")
		.attr("width", diameter)
		.attr("height", diameter)
		.append("g")
		.attr("transform", "translate(2,2)");
	var color = d3.scale.linear()
		.domain([-1, 5])
		.range(["hsl(152,80%,80%)", "hsl(228,30%,40%)"])
		.interpolate(d3.interpolateHcl);
	var node = svg.datum(jsonObj).selectAll(".node")
		.data(pack.nodes)
		.enter().append("g")
		.attr("class", function(d) { return d.children ? "bubble_node" : "bubble_leaf bubble_node"; })
		.style("fill", function(d) { return d.children ? color(d.depth) : null; })
		.style("display", function(d) { return d.value > 0 ? "inline" : "none"})
		.attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });

	node.append("circle")
		.attr("class", "bubble_circle")
		.attr("r", function(d) { return d.r; });
	node.filter(function(d) { return !d.children; }).append("text")
		.attr("class", "bubble_text")
		.attr("dy", ".3em")
		.style("text-anchor", "middle")
		.text(function(d) { return d.name + ": " + d.size; });
	// Legend.
	node.append("title")
		.attr("class", "bubble_title")
		.text(function(d) { return (d.children ? (d.parent ? "Entity " : "" ) : "Entity version ") + d.name + (d.children ? "" : ": " + format(d.size) + " instances"); });

	d3.select(self.frameElement).style("height", diameter + "px");
	d3.select("#dBubbles").style("background", color(-1));
};
