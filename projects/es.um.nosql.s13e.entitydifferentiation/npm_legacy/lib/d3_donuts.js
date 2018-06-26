/**
 * Method used to draw a D3 Donut chart on a given div given a Differences Structure such the one shown on the _STRUCT_.js file.
 * @param DiffStruct: The structure to transform to D3 format and draw.
 * @param divId: The div in which the D3 chart will be drawn.
 */
function drawD3Donuts(DiffStruct, divId)
{
	var jsonObj = transformIntoD3Csv(DiffStruct);
	var radius = screen.width / 18;
	var padding = 10;
	var color = d3.scale.ordinal().range(["#5858FA", "#58FA58", "#FA5858", "#58FAF4", "#AC58FA", "#F4FA58", "#FA58D0", "#FAAC58", "#BDBDBD"]);
	var arc = d3.svg.arc().outerRadius(radius).innerRadius(radius / 2);
	var pie = d3.layout.pie()
		.sort(null)
		.value(function(d) { return d.population; });

	color.domain(d3.keys(jsonObj[0]).filter(function(key) { return key !== "Entity"; }));

	// Create two temporary fields.
	jsonObj.forEach(function(d)
	{
		var counter = 0;
		for (var numVersions in d)
			if (typeof d[numVersions] === "number")
				counter += d[numVersions];

		d.tmp = color.domain().map(function(name)
		{
			return {name: name, population: +d[name], percentage: (+d[name] / counter * 100).toFixed(2)};
		});
	});

	var legend = d3.select(divId).append("svg")
		.attr("class", "donut_legend")
		.attr("width", radius * 2)
		.attr("height", radius * 2)
		.selectAll("g")
		.data(color.domain().slice())
		.enter().append("g")
		.attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
	legend.append("rect")
		.attr("width", 18)
		.attr("height", 18)
		.style("fill", color);
	legend.append("text")
		.attr("x", 24)
		.attr("y", 9)
		.attr("dy", ".35em")
		.text(function(d) { return d; });

	var svg = d3.select(divId).selectAll(".donut_pie")
		.data(jsonObj)
		.enter().append("svg")
		.attr("class", "donut_pie")
		.attr("width", radius * 2.1)
		.attr("height", radius * 2.1)
		.append("g")
		.attr("transform", "translate(" + radius * 1.05 + "," + radius * 1.05 + ")");
	svg.selectAll(".donut_arc")
		.data(function(d) { return pie(d.tmp); })
		.enter().append("path")
		.attr("class", "donut_arc")
		.attr("d", arc)
		.style("fill", function(d) { return color(d.data.name); });
	svg.selectAll(".donut_arc")
		.append("title")
		.attr("class", "donut_arc_title")
		.text(function(d) { return (d.data.name + ": " + d.value + " instances (" + d.data.percentage + "%)")});
	svg.append("text")
		.attr("class", "donut_text")
		.attr("dy", ".35em")
		.text(function(d) { return d.Entity; });
};
