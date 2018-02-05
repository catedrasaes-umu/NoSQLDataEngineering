var DiffMethodsExclusive =
{
	isOfExactType_Restaurant_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("location" in obj) || !(typeof obj.location === "string"))
			return false;
		if (!("manager" in obj) || !(typeof obj.manager === "string"))
			return false;
		if (!("hasWaiters" in obj) || (typeof obj.hasWaiters === "string" && false)
				|| (obj.hasWaiters.constructor === Array && (0 > obj.hasWaiters.size || !checkAllOf(obj.hasWaiters, "string"))
				|| (typeof obj.hasWaiters !== "string" && obj.hasWaiters.constructor !== Array)))
			return false;
		if (!("hasMenu" in obj) || (typeof obj.hasMenu === "string" && false)
				|| (obj.hasMenu.constructor === Array && (1 > obj.hasMenu.size || 1 < obj.hasMenu.size || !checkAllOf(obj.hasMenu, "string"))
				|| (typeof obj.hasMenu !== "string" && obj.hasMenu.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Restaurant"))
			return false;

		return true;
	},

	isOfExactType_Table_1: function (obj)
	{
		if (!("number" in obj) || !(typeof obj.number === "number") || !(obj.number % 1 === 0))
			return false;
		if (!("occupied" in obj) || !(typeof obj.occupied === "string"))
			return false;
		if (!("location" in obj) || !(typeof obj.location === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Table"))
			return false;
		if ("isOccupied" in obj && !(!(typeof obj.isOccupied === "boolean") || ((obj.isOccupied !== true) && (obj.isOccupied !== false))))
			return false;
		if ("position" in obj && !(!(obj.position.constructor === Array) || (!checkAllOf(obj.position, "int"))))
			return false;

		return true;
	},

	isOfExactType_Table_2: function (obj)
	{
		if (!("number" in obj) || !(typeof obj.number === "number") || !(obj.number % 1 === 0))
			return false;
		if (!("isOccupied" in obj) || !(typeof obj.isOccupied === "boolean") || ((obj.isOccupied !== true) && (obj.isOccupied !== false)))
			return false;
		if (!("position" in obj) || !(obj.position.constructor === Array) || (!checkAllOf(obj.position, "int")))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Table"))
			return false;
		if ("occupied" in obj && !(!(typeof obj.occupied === "string")))
			return false;
		if ("location" in obj && !(!(typeof obj.location === "string")))
			return false;

		return true;
	},

	isOfExactType_Waiter_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("nif" in obj) || !(typeof obj.nif === "string"))
			return false;
		if (!("hours_per_week" in obj) || !(typeof obj.hours_per_week === "string"))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || !(obj.salary % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Waiter"))
			return false;
		if ("hours_per_week" in obj && !(!(typeof obj.hours_per_week === "number") || !(obj.hours_per_week % 1 === 0)))
			return false;
		if ("salary" in obj && !(!(typeof obj.salary === "number") || (obj.salary % 1 === 0)))
			return false;
		if ("tablesAssigned" in obj && !(!(obj.tablesAssigned.constructor === Array) || (!checkAllOf(obj.tablesAssigned, "int"))))
			return false;
		if ("hasTables" in obj && !((typeof obj.hasTables === "object" && !(obj.hasTables instanceof Array) && (!this.isOfExactType_Table_2(obj.hasTables)))
				|| (obj.hasTables.constructor === Array && (0 > obj.hasTables.size || 5 < obj.hasTables.size || !checkAllOf(obj.hasTables, "object")
				|| obj.hasTables[0] == null || !this.isOfExactType_Table_2(obj.hasTables[0])
				)) || (typeof obj.hasTables !== "object" && obj.hasTables.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Waiter_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("nif" in obj) || !(typeof obj.nif === "string"))
			return false;
		if (!("hours_per_week" in obj) || !(typeof obj.hours_per_week === "number") || !(obj.hours_per_week % 1 === 0))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("tablesAssigned" in obj) || !(obj.tablesAssigned.constructor === Array) || (!checkAllOf(obj.tablesAssigned, "int")))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Waiter"))
			return false;
		if ("hasTables" in obj && !((typeof obj.hasTables === "object" && !(obj.hasTables instanceof Array) && (!this.isOfExactType_Table_2(obj.hasTables)))
				|| (obj.hasTables.constructor === Array && (0 > obj.hasTables.size || 5 < obj.hasTables.size || !checkAllOf(obj.hasTables, "object")
				|| obj.hasTables[0] == null || !this.isOfExactType_Table_2(obj.hasTables[0])
				)) || (typeof obj.hasTables !== "object" && obj.hasTables.constructor !== Array)))
			return false;
		if ("hours_per_week" in obj && !(!(typeof obj.hours_per_week === "string")))
			return false;
		if ("salary" in obj && !(!(typeof obj.salary === "number") || !(obj.salary % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Waiter_3: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("nif" in obj) || !(typeof obj.nif === "string"))
			return false;
		if (!("hours_per_week" in obj) || !(typeof obj.hours_per_week === "number") || !(obj.hours_per_week % 1 === 0))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("hasTables" in obj) || (typeof obj.hasTables === "object" && !(obj.hasTables instanceof Array) && (!this.isOfExactType_Table_2(obj.hasTables)))
				|| (obj.hasTables.constructor === Array && (0 > obj.hasTables.size || 5 < obj.hasTables.size || !checkAllOf(obj.hasTables, "object")
				|| obj.hasTables[0] == null || !this.isOfExactType_Table_2(obj.hasTables[0])
				)) || (typeof obj.hasTables !== "object" && obj.hasTables.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Waiter"))
			return false;
		if ("tablesAssigned" in obj && !(!(obj.tablesAssigned.constructor === Array) || (!checkAllOf(obj.tablesAssigned, "int"))))
			return false;
		if ("hours_per_week" in obj && !(!(typeof obj.hours_per_week === "string")))
			return false;
		if ("salary" in obj && !(!(typeof obj.salary === "number") || !(obj.salary % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Menu_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("hasDishes" in obj) || (typeof obj.hasDishes === "string" && false)
				|| (obj.hasDishes.constructor === Array && (1 > obj.hasDishes.size || !checkAllOf(obj.hasDishes, "string"))
				|| (typeof obj.hasDishes !== "string" && obj.hasDishes.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Menu"))
			return false;

		return true;
	},

	isOfExactType_Dish_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("hasIngredients" in obj) || (typeof obj.hasIngredients === "object" && !(obj.hasIngredients instanceof Array) && (!this.isOfExactType_Ingredient_2(obj.hasIngredients)))
				|| (obj.hasIngredients.constructor === Array && (1 > obj.hasIngredients.size || !checkAllOf(obj.hasIngredients, "object")
				|| obj.hasIngredients[0] == null || !this.isOfExactType_Ingredient_2(obj.hasIngredients[0])
				)) || (typeof obj.hasIngredients !== "object" && obj.hasIngredients.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Dish"))
			return false;
		if ("isSeasonal" in obj && !(!(typeof obj.isSeasonal === "boolean") || ((obj.isSeasonal !== true) && (obj.isSeasonal !== false))))
			return false;
		if ("suitableForCeliacs" in obj && !(!(typeof obj.suitableForCeliacs === "boolean") || ((obj.suitableForCeliacs !== true) && (obj.suitableForCeliacs !== false))))
			return false;
		if ("suitableForDiabetics" in obj && !(!(typeof obj.suitableForDiabetics === "boolean") || ((obj.suitableForDiabetics !== true) && (obj.suitableForDiabetics !== false))))
			return false;
		if ("calories" in obj && !(!(typeof obj.calories === "number") || (obj.calories % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Dish_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("isSeasonal" in obj) || !(typeof obj.isSeasonal === "boolean") || ((obj.isSeasonal !== true) && (obj.isSeasonal !== false)))
			return false;
		if (!("hasIngredients" in obj) || (typeof obj.hasIngredients === "object" && !(obj.hasIngredients instanceof Array) && (!this.isOfExactType_Ingredient_2(obj.hasIngredients)))
				|| (obj.hasIngredients.constructor === Array && (1 > obj.hasIngredients.size || !checkAllOf(obj.hasIngredients, "object")
				|| obj.hasIngredients[0] == null || !this.isOfExactType_Ingredient_2(obj.hasIngredients[0])
				)) || (typeof obj.hasIngredients !== "object" && obj.hasIngredients.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Dish"))
			return false;
		if ("suitableForCeliacs" in obj && !(!(typeof obj.suitableForCeliacs === "boolean") || ((obj.suitableForCeliacs !== true) && (obj.suitableForCeliacs !== false))))
			return false;
		if ("suitableForDiabetics" in obj && !(!(typeof obj.suitableForDiabetics === "boolean") || ((obj.suitableForDiabetics !== true) && (obj.suitableForDiabetics !== false))))
			return false;
		if ("calories" in obj && !(!(typeof obj.calories === "number") || (obj.calories % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Dish_3: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("isSeasonal" in obj) || !(typeof obj.isSeasonal === "boolean") || ((obj.isSeasonal !== true) && (obj.isSeasonal !== false)))
			return false;
		if (!("suitableForCeliacs" in obj) || !(typeof obj.suitableForCeliacs === "boolean") || ((obj.suitableForCeliacs !== true) && (obj.suitableForCeliacs !== false)))
			return false;
		if (!("suitableForDiabetics" in obj) || !(typeof obj.suitableForDiabetics === "boolean") || ((obj.suitableForDiabetics !== true) && (obj.suitableForDiabetics !== false)))
			return false;
		if (!("hasIngredients" in obj) || (typeof obj.hasIngredients === "object" && !(obj.hasIngredients instanceof Array) && (!this.isOfExactType_Ingredient_2(obj.hasIngredients)))
				|| (obj.hasIngredients.constructor === Array && (1 > obj.hasIngredients.size || !checkAllOf(obj.hasIngredients, "object")
				|| obj.hasIngredients[0] == null || !this.isOfExactType_Ingredient_2(obj.hasIngredients[0])
				)) || (typeof obj.hasIngredients !== "object" && obj.hasIngredients.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Dish"))
			return false;
		if ("calories" in obj && !(!(typeof obj.calories === "number") || (obj.calories % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Dish_4: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("isSeasonal" in obj) || !(typeof obj.isSeasonal === "boolean") || ((obj.isSeasonal !== true) && (obj.isSeasonal !== false)))
			return false;
		if (!("suitableForCeliacs" in obj) || !(typeof obj.suitableForCeliacs === "boolean") || ((obj.suitableForCeliacs !== true) && (obj.suitableForCeliacs !== false)))
			return false;
		if (!("suitableForDiabetics" in obj) || !(typeof obj.suitableForDiabetics === "boolean") || ((obj.suitableForDiabetics !== true) && (obj.suitableForDiabetics !== false)))
			return false;
		if (!("calories" in obj) || !(typeof obj.calories === "number") || (obj.calories % 1 === 0))
			return false;
		if (!("hasIngredients" in obj) || (typeof obj.hasIngredients === "object" && !(obj.hasIngredients instanceof Array) && (!this.isOfExactType_Ingredient_2(obj.hasIngredients)))
				|| (obj.hasIngredients.constructor === Array && (1 > obj.hasIngredients.size || !checkAllOf(obj.hasIngredients, "object")
				|| obj.hasIngredients[0] == null || !this.isOfExactType_Ingredient_2(obj.hasIngredients[0])
				)) || (typeof obj.hasIngredients !== "object" && obj.hasIngredients.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Dish"))
			return false;

		return true;
	},

	isOfExactType_Ingredient_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Ingredient"))
			return false;
		if ("quantity" in obj && !(!(typeof obj.quantity === "string")))
			return false;
		if ("isSeasonal" in obj && !(!(typeof obj.isSeasonal === "boolean") || ((obj.isSeasonal !== true) && (obj.isSeasonal !== false))))
			return false;

		return true;
	},

	isOfExactType_Ingredient_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("price" in obj) || !(typeof obj.price === "number") || (obj.price % 1 === 0))
			return false;
		if (!("quantity" in obj) || !(typeof obj.quantity === "string"))
			return false;
		if (!("isSeasonal" in obj) || !(typeof obj.isSeasonal === "boolean") || ((obj.isSeasonal !== true) && (obj.isSeasonal !== false)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Ingredient"))
			return false;

		return true;
	}

};

