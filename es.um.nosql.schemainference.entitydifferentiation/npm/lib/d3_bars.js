'use strict'

var d3 = require('d3');

module.exports = {
/**
 * Method used to draw a D3 Bar chart on a given div given a Differences Structure such the one shown on the _STRUCT_.js file.
 * @param DiffStruct: The structure to transform to D3 format and draw.
 * @param divId: The div in which the D3 chart will be drawn.
 */
drawD3Bars : function (DiffStruct, divId)
{
    var jsonObj = transformIntoD3Csv(DiffStruct);
    var margin = {top: 20, right: 20, bottom: 30, left: 40};
    var	width = screen.width * 0.75 - margin.left - margin.right;
    var	height = screen.height * 0.6 - margin.top - margin.bottom;
    var x0 = d3.scale.ordinal().rangeRoundBands([0, width], .1);
    var x1 = d3.scale.ordinal();
    var y = d3.scale.linear().range([height, 10]);
    var xAxis = d3.svg.axis().scale(x0).orient("bottom");
    var yAxis = d3.svg.axis().scale(y).orient("left").tickFormat(d3.format("d"));
    var color = d3.scale.ordinal().range(["#5858FA", "#58FA58", "#FA5858", "#58FAF4", "#AC58FA", "#F4FA58", "#FA58D0", "#FAAC58", "#BDBDBD"]);;
    var eVersions = d3.keys(jsonObj[0]).filter(function(key) { return key !== "Entity"; });
    var svg = d3.select(divId).append("svg")
	.attr("width", width + margin.left + margin.right)
	.attr("height", height + margin.top + margin.bottom)
	.append("g")
	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    // Create two temporary fields.
    jsonObj.forEach(function(d)
	            {
		        var counter = 0;
		        for (var numVersions in d)
			    if (typeof d[numVersions] === "number")
				counter += d[numVersions];

		        d.tmp = eVersions.map(function(name) { return {name: name, value: +d[name], total: counter}; });
	            });

    x0.domain(jsonObj.map(function(d) { return d.Entity; }));
    x1.domain(eVersions).rangeRoundBands([0, x0.rangeBand()]);
    y.domain([0, d3.max(jsonObj, function(d) { return d3.max(d.tmp, function(d) { return d.value; }); })]);

    svg.append("g")
	.attr("class", "bars_x bars_axis")
	.attr("transform", "translate(0," + height + ")")
	.call(xAxis);
    svg.append("g")
	.attr("class", "bars_y bars_axis")
	.call(yAxis)
	.append("text")
	.attr("class", "bars_text")
	.attr("transform", "rotate(-90)")
	.attr("y", 6)
	.attr("dy", ".71em")
	.style("text-anchor", "end")
	.text("Instances")
	.attr("class", "bars_text");
    svg.selectAll(".bars_rect")
	.append("title")
	.attr("class", "bars_arc_title")
	.text(function(d) { return d.name + ": " + d.value + " instances (" + d.value + "/" + d.total + ")"});

    var state = svg.selectAll(".state")
	.data(jsonObj)
	.enter().append("g")
	.attr("class", "bars_state")
	.attr("transform", function(d) { return "translate(" + x0(d.Entity) + ",0)"; });
    state.selectAll("rect")
	.data(function(d) { return d.tmp; })
	.enter().append("rect")
	.attr("width", x1.rangeBand())
	.attr("class", "bars_rect")
	.attr("x", function(d) { return x1(d.name); })
	.attr("y", function(d) { return y(d.value); })
	.attr("height", function(d) { return height - y(d.value); })
	.style("fill", function(d) { return color(d.name); })
    state.selectAll(".bars_state")
	.data(function(d) { return d.tmp; })
	.enter().append("text")
	.attr("class", "bars_label")
	.attr("x", function(d) { return x1(d.name); })
	.attr("y", function(d) { return y(d.value) - 10; })
	.style("display", function(d) { return d.value > 0 ? "inline" : "none"})
	.append('tspan')
	.attr('dx', 4)
	.attr('dy', -10)
	.text(function(d) { return "V_" + d.name.substring(d.name.lastIndexOf(" ") + 1); })
	.append('tspan')
	.attr('dx', -28)
	.attr('dy', 17)
	.text(function(d) { return "(" + d.value + "/" + d.total + ")"; });

    // Legend.
    var legend = svg.selectAll(".legend")
	.data(eVersions.slice())
	.enter().append("g")
	.attr("class", "bars_legend")
	.attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
    legend.append("rect")
	.attr("x", width - 18)
	.attr("width", 18)
	.attr("height", 18)
	.style("fill", color);
    legend.append("text")
	.attr("class", "bars_text")
	.attr("x", width - 24)
	.attr("y", 9)
	.attr("dy", ".35em")
	.text(function(d) { return d; });
}
};
