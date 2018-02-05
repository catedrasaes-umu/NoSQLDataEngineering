/**
 * Method used to draw a D3 Tree chart on a given div given a Differences Structure such the one shown on the _STRUCT_.js file.
 * @param DiffStruct: The structure to transform to D3 format and draw.
 * @param divId: The div in which the D3 chart will be drawn.
 */
function drawD3Tree(DiffStruct, divId)
{
	var jsonObj = transformIntoD3ExtendedJson(DiffStruct);
	var margin = {top: 20, right: 120, bottom: 20, left: 120};
	var width = screen.width * 0.9 - margin.right - margin.left;
	var height = screen.height * 0.7 - margin.top - margin.bottom;
	var i = 0;
	var duration = 750;
	var tree = d3.layout.tree().size([height, width]);
	var diagonal = d3.svg.diagonal().projection(function(d) { return [d.y, d.x]; });

	var svg = d3.select(divId).append("svg")
		.attr("width", width + margin.right + margin.left)
		.attr("height", height + margin.top + margin.bottom)
		.append("g")
		.attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	jsonObj.x0 = height / 2;
	jsonObj.y0 = 0;
	jsonObj.children.forEach(collapse);
	update(jsonObj);

	d3.select(self.frameElement).style("height", height);

	function collapse(d)
	{
		if (d.children)
		{
			d._children = d.children;
			d._children.forEach(collapse);
			d.children = null;
		}
	}

	function update(source)
	{
		// Compute the new tree layout.
		var nodes = tree.nodes(jsonObj).reverse();
		var links = tree.links(nodes);

		// Normalize for fixed-depth.
		nodes.forEach(function(d) { d.y = d.depth * 180; });

		// Update the nodes…
		var node = svg.selectAll("g.tree_node")
			.data(nodes, function(d) { return d.id || (d.id = ++i); });

		// Enter any new nodes at the parent's previous position.
		var nodeEnter = node.enter().append("g")
			.attr("class", "tree_node")
			.attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
			.on("click", click);

		nodeEnter.append("circle")
			.attr("r", 1e-6)
			.style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

		nodeEnter.append("text")
			.attr("x", function(d) { return d.children || d._children ? -10 : 10; })
			.attr("dy", ".35em")
			.attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
			.text(function(d) { return d.name; })
			.style("fill-opacity", 1e-6);

		// Transition nodes to their new position.
		var nodeUpdate = node.transition()
			.duration(duration)
			.attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

		nodeUpdate.select("circle")
			.attr("r", 4.5)
			.style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

		nodeUpdate.select("text")
			.style("fill-opacity", 1);

		// Transition exiting nodes to the parent's new position.
		var nodeExit = node.exit().transition()
			.duration(duration)
			.attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
			.remove();

		nodeExit.select("circle")
			.attr("r", 1e-6);

		nodeExit.select("text")
			.style("fill-opacity", 1e-6);

		// Update the links…
		var link = svg.selectAll("path.tree_link")
			.data(links, function(d) { return d.target.id; });

		// Enter any new links at the parent's previous position.
		link.enter().insert("path", "g")
			.attr("class", "tree_link")
			.attr("d", function(d)
			{
				var o = {x: source.x0, y: source.y0};
				return diagonal({source: o, target: o});
			});

		// Transition links to their new position.
		link.transition()
			.duration(duration)
			.attr("d", diagonal);

		// Transition exiting nodes to the parent's new position.
		link.exit().transition()
			.duration(duration)
			.attr("d", function(d)
			{
				var o = {x: source.x, y: source.y};
				return diagonal({source: o, target: o});
			})
			.remove();

		// Stash the old positions for transition.
		nodes.forEach(function(d)
		{
			d.x0 = d.x;
			d.y0 = d.y;
		});
	}

	// Toggle children on click.
	function click(d)
	{
		if (d.children)
		{
			d._children = d.children;
			d.children = null;
		}
		else
		{
			d.children = d._children;
			d._children = null;
		}
		update(d);
	}
};