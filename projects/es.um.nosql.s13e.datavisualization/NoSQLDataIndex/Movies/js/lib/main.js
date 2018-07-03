/**
 * Div ids.
 */
var DIV_IDS = ["#dBubbles", "#dBars", "#dDonuts", "#dTree"];

/**
 * Button ids.
 */
var BUTTON_IDS = ["#bBubbles", "#bBars", "#bDonuts", "#bTree"];

/**
 * Method used to start the drawing operation over the divs.
 * @param jsonData: A json object containing all the entity variations.
 */

$(function()
{
	// Load the JSON file and call the main method to start drawing stuff.
	$.getJSON(DATA_DIFF_NAME + ".json", function(jsonData)
	{
		$("#title").text("Data visualization: " + DATA_DIFF_NAME);

		$.each(jsonData, function(key, value)
		{
			var type = isOfExclusiveType(value);
			for (var i = 0; i < type.length; i++)
				DiffStruct[type[i].substring(0, type[i].lastIndexOf("_"))][type[i]]++;
		});

		drawD3Bubbles(DiffStruct, DIV_IDS[0]);
		drawD3Bars(DiffStruct, DIV_IDS[1]);
		drawD3Donuts(DiffStruct, DIV_IDS[2]);
		drawD3Tree(DiffStruct, DIV_IDS[3]);

		bindButtons();
	});
});

/**
 * Method used to bind click actions on buttons to show/hide the associated chart divs.
 */
function bindButtons()
{
	$(BUTTON_IDS[0]).click(function()
	{
		toggleDivs(DIV_IDS[0]);
	});

	$(BUTTON_IDS[1]).click(function()
	{
		toggleDivs(DIV_IDS[1]);
	});

	$(BUTTON_IDS[2]).click(function()
	{
		toggleDivs(DIV_IDS[2]);
	});

	$(BUTTON_IDS[3]).click(function()
	{
		toggleDivs(DIV_IDS[3]);
	});
};

/**
 * Method used to, given a DIV id containing a chart, show this div chart and hide all the others.
 */
function toggleDivs(id)
{
	for (var i in DIV_IDS)
		if (DIV_IDS[i] === id)
			$(DIV_IDS[i]).show(800);
		else
			$(DIV_IDS[i]).hide(800);
};
