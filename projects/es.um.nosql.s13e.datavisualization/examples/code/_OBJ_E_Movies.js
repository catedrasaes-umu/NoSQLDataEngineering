var DiffMethodsExclusive =
{
	isOfExactType_Actor_1: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "number") || !(obj.Age % 1 === 0))
			return false;
		if (!("Birthday" in obj) || !(typeof obj.Birthday === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Actor"))
			return false;
		if ("Nationality" in obj && !(!(obj.Nationality.constructor === Array) || (!checkAllOf(obj.Nationality, "String"))))
			return false;
		if ("Debut_year" in obj && !(!(typeof obj.Debut_year === "number") || !(obj.Debut_year % 1 === 0)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("Website" in obj && !(!(typeof obj.Website === "string")))
			return false;

		return true;
	},

	isOfExactType_Actor_2: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "number") || !(obj.Age % 1 === 0))
			return false;
		if (!("Birthday" in obj) || !(typeof obj.Birthday === "string"))
			return false;
		if (!("Nationality" in obj) || !(obj.Nationality.constructor === Array) || (!checkAllOf(obj.Nationality, "String")))
			return false;
		if (!("hasAwards" in obj) || (typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Actor"))
			return false;
		if ("Debut_year" in obj && !(!(typeof obj.Debut_year === "number") || !(obj.Debut_year % 1 === 0)))
			return false;
		if ("Website" in obj && !(!(typeof obj.Website === "string")))
			return false;

		return true;
	},

	isOfExactType_Actor_3: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "number") || !(obj.Age % 1 === 0))
			return false;
		if (!("Birthday" in obj) || !(typeof obj.Birthday === "string"))
			return false;
		if (!("Nationality" in obj) || !(obj.Nationality.constructor === Array) || (!checkAllOf(obj.Nationality, "String")))
			return false;
		if (!("Debut_year" in obj) || !(typeof obj.Debut_year === "number") || !(obj.Debut_year % 1 === 0))
			return false;
		if (!("hasAwards" in obj) || (typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Actor"))
			return false;
		if ("Website" in obj && !(!(typeof obj.Website === "string")))
			return false;

		return true;
	},

	isOfExactType_Actor_4: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "number") || !(obj.Age % 1 === 0))
			return false;
		if (!("Birthday" in obj) || !(typeof obj.Birthday === "string"))
			return false;
		if (!("Nationality" in obj) || !(obj.Nationality.constructor === Array) || (!checkAllOf(obj.Nationality, "String")))
			return false;
		if (!("Debut_year" in obj) || !(typeof obj.Debut_year === "number") || !(obj.Debut_year % 1 === 0))
			return false;
		if (!("Website" in obj) || !(typeof obj.Website === "string"))
			return false;
		if (!("hasAwards" in obj) || (typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Actor"))
			return false;

		return true;
	},

	isOfExactType_Award_1: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Institution" in obj) || !(typeof obj.Institution === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Award"))
			return false;

		return true;
	},

	isOfExactType_Director_1: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Director"))
			return false;
		if ("Age" in obj && !(!(typeof obj.Age === "number") || !(obj.Age % 1 === 0)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Director_2: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "number") || !(obj.Age % 1 === 0))
			return false;
		if (!("Oscars" in obj) || !(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Director"))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("Age" in obj && !(!(typeof obj.Age === "string")))
			return false;

		return true;
	},

	isOfExactType_Director_3: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Age" in obj) || !(typeof obj.Age === "number") || !(obj.Age % 1 === 0))
			return false;
		if (!("hasAwards" in obj) || (typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Director"))
			return false;
		if ("Age" in obj && !(!(typeof obj.Age === "string")))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Movie_1: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Director" in obj) || !(typeof obj.Director === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Rating" in obj) || !(typeof obj.Rating === "number") || (obj.Rating % 1 === 0))
			return false;
		if (!("Genre" in obj) || !(obj.Genre.constructor === Array) || (!checkAllOf(obj.Genre, "String")))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Movie"))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;
		if ("hasDirector" in obj && !((typeof obj.hasDirector === "string" && false)
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "string"))
				|| (typeof obj.hasDirector !== "string" && obj.hasDirector.constructor !== Array))))
			return false;
		if ("hasProducers" in obj && !((typeof obj.hasProducers === "string" && false)
				|| (obj.hasProducers.constructor === Array && (1 > obj.hasProducers.size || !checkAllOf(obj.hasProducers, "string"))
				|| (typeof obj.hasProducers !== "string" && obj.hasProducers.constructor !== Array))))
			return false;
		if ("hasActors" in obj && !((typeof obj.hasActors === "string" && false)
				|| (obj.hasActors.constructor === Array && (0 > obj.hasActors.size || !checkAllOf(obj.hasActors, "string"))
				|| (typeof obj.hasActors !== "string" && obj.hasActors.constructor !== Array))))
			return false;
		if ("Awards" in obj && !(!(typeof obj.Awards === "number") || !(obj.Awards % 1 === 0)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_1(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_1(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_2(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_2(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "string" && false)
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "string"))
				|| (typeof obj.hasRating !== "string" && obj.hasRating.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Movie_2: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Rating" in obj) || !(typeof obj.Rating === "number") || (obj.Rating % 1 === 0))
			return false;
		if (!("Genre" in obj) || !(obj.Genre.constructor === Array) || (!checkAllOf(obj.Genre, "String")))
			return false;
		if (!("Oscars" in obj) || !(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0))
			return false;
		if (!("hasDirector" in obj) || (typeof obj.hasDirector === "string" && false)
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "string"))
				|| (typeof obj.hasDirector !== "string" && obj.hasDirector.constructor !== Array)))
			return false;
		if (!("hasProducers" in obj) || (typeof obj.hasProducers === "string" && false)
				|| (obj.hasProducers.constructor === Array && (1 > obj.hasProducers.size || !checkAllOf(obj.hasProducers, "string"))
				|| (typeof obj.hasProducers !== "string" && obj.hasProducers.constructor !== Array)))
			return false;
		if (!("hasActors" in obj) || (typeof obj.hasActors === "string" && false)
				|| (obj.hasActors.constructor === Array && (0 > obj.hasActors.size || !checkAllOf(obj.hasActors, "string"))
				|| (typeof obj.hasActors !== "string" && obj.hasActors.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Movie"))
			return false;
		if ("Director" in obj && !(!(typeof obj.Director === "string")))
			return false;
		if ("Awards" in obj && !(!(typeof obj.Awards === "number") || !(obj.Awards % 1 === 0)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_1(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_1(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_2(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_2(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "string" && false)
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "string"))
				|| (typeof obj.hasRating !== "string" && obj.hasRating.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Movie_3: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Genre" in obj) || !(obj.Genre.constructor === Array) || (!checkAllOf(obj.Genre, "String")))
			return false;
		if (!("Awards" in obj) || !(typeof obj.Awards === "number") || !(obj.Awards % 1 === 0))
			return false;
		if (!("hasDirector" in obj) || (typeof obj.hasDirector === "string" && false)
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "string"))
				|| (typeof obj.hasDirector !== "string" && obj.hasDirector.constructor !== Array)))
			return false;
		if (!("hasProducers" in obj) || (typeof obj.hasProducers === "string" && false)
				|| (obj.hasProducers.constructor === Array && (1 > obj.hasProducers.size || !checkAllOf(obj.hasProducers, "string"))
				|| (typeof obj.hasProducers !== "string" && obj.hasProducers.constructor !== Array)))
			return false;
		if (!("hasActors" in obj) || (typeof obj.hasActors === "string" && false)
				|| (obj.hasActors.constructor === Array && (0 > obj.hasActors.size || !checkAllOf(obj.hasActors, "string"))
				|| (typeof obj.hasActors !== "string" && obj.hasActors.constructor !== Array)))
			return false;
		if (!("hasRating" in obj) || (typeof obj.hasRating === "string" && false)
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "string"))
				|| (typeof obj.hasRating !== "string" && obj.hasRating.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Movie"))
			return false;
		if ("Director" in obj && !(!(typeof obj.Director === "string")))
			return false;
		if ("Rating" in obj && !(!(typeof obj.Rating === "number") || (obj.Rating % 1 === 0)))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_1(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_1(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_2(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_2(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Movie_4: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Genre" in obj) || !(obj.Genre.constructor === Array) || (!checkAllOf(obj.Genre, "String")))
			return false;
		if (!("Awards" in obj) || !(typeof obj.Awards === "number") || !(obj.Awards % 1 === 0))
			return false;
		if (!("hasDirector" in obj) || (typeof obj.hasDirector === "string" && false)
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "string"))
				|| (typeof obj.hasDirector !== "string" && obj.hasDirector.constructor !== Array)))
			return false;
		if (!("hasProducers" in obj) || (typeof obj.hasProducers === "string" && false)
				|| (obj.hasProducers.constructor === Array && (1 > obj.hasProducers.size || !checkAllOf(obj.hasProducers, "string"))
				|| (typeof obj.hasProducers !== "string" && obj.hasProducers.constructor !== Array)))
			return false;
		if (!("hasActors" in obj) || (typeof obj.hasActors === "string" && false)
				|| (obj.hasActors.constructor === Array && (0 > obj.hasActors.size || !checkAllOf(obj.hasActors, "string"))
				|| (typeof obj.hasActors !== "string" && obj.hasActors.constructor !== Array)))
			return false;
		if (!("hasRating" in obj) || (typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_1(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_1(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Movie"))
			return false;
		if ("Director" in obj && !(!(typeof obj.Director === "string")))
			return false;
		if ("Rating" in obj && !(!(typeof obj.Rating === "number") || (obj.Rating % 1 === 0)))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_2(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_2(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "string" && false)
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "string"))
				|| (typeof obj.hasRating !== "string" && obj.hasRating.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Movie_5: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Genre" in obj) || !(obj.Genre.constructor === Array) || (!checkAllOf(obj.Genre, "String")))
			return false;
		if (!("Awards" in obj) || !(typeof obj.Awards === "number") || !(obj.Awards % 1 === 0))
			return false;
		if (!("hasDirector" in obj) || (typeof obj.hasDirector === "string" && false)
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "string"))
				|| (typeof obj.hasDirector !== "string" && obj.hasDirector.constructor !== Array)))
			return false;
		if (!("hasProducers" in obj) || (typeof obj.hasProducers === "string" && false)
				|| (obj.hasProducers.constructor === Array && (1 > obj.hasProducers.size || !checkAllOf(obj.hasProducers, "string"))
				|| (typeof obj.hasProducers !== "string" && obj.hasProducers.constructor !== Array)))
			return false;
		if (!("hasActors" in obj) || (typeof obj.hasActors === "string" && false)
				|| (obj.hasActors.constructor === Array && (0 > obj.hasActors.size || !checkAllOf(obj.hasActors, "string"))
				|| (typeof obj.hasActors !== "string" && obj.hasActors.constructor !== Array)))
			return false;
		if (!("hasRating" in obj) || (typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_2(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_2(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Movie"))
			return false;
		if ("Director" in obj && !(!(typeof obj.Director === "string")))
			return false;
		if ("Rating" in obj && !(!(typeof obj.Rating === "number") || (obj.Rating % 1 === 0)))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_1(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_1(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasAwards" in obj && !((typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "string" && false)
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "string"))
				|| (typeof obj.hasRating !== "string" && obj.hasRating.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Movie_6: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Genre" in obj) || !(obj.Genre.constructor === Array) || (!checkAllOf(obj.Genre, "String")))
			return false;
		if (!("hasDirector" in obj) || (typeof obj.hasDirector === "string" && false)
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "string"))
				|| (typeof obj.hasDirector !== "string" && obj.hasDirector.constructor !== Array)))
			return false;
		if (!("hasProducers" in obj) || (typeof obj.hasProducers === "string" && false)
				|| (obj.hasProducers.constructor === Array && (1 > obj.hasProducers.size || !checkAllOf(obj.hasProducers, "string"))
				|| (typeof obj.hasProducers !== "string" && obj.hasProducers.constructor !== Array)))
			return false;
		if (!("hasActors" in obj) || (typeof obj.hasActors === "string" && false)
				|| (obj.hasActors.constructor === Array && (0 > obj.hasActors.size || !checkAllOf(obj.hasActors, "string"))
				|| (typeof obj.hasActors !== "string" && obj.hasActors.constructor !== Array)))
			return false;
		if (!("hasRating" in obj) || (typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_2(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_2(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array))
			return false;
		if (!("hasAwards" in obj) || (typeof obj.hasAwards === "object" && !(obj.hasAwards instanceof Array) && (!this.isOfExactType_Award_1(obj.hasAwards)))
				|| (obj.hasAwards.constructor === Array && (0 > obj.hasAwards.size || !checkAllOf(obj.hasAwards, "object")
				|| obj.hasAwards[0] == null || !this.isOfExactType_Award_1(obj.hasAwards[0])
				)) || (typeof obj.hasAwards !== "object" && obj.hasAwards.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Movie"))
			return false;
		if ("Director" in obj && !(!(typeof obj.Director === "string")))
			return false;
		if ("Rating" in obj && !(!(typeof obj.Rating === "number") || (obj.Rating % 1 === 0)))
			return false;
		if ("Oscars" in obj && !(!(typeof obj.Oscars === "number") || !(obj.Oscars % 1 === 0)))
			return false;
		if ("Awards" in obj && !(!(typeof obj.Awards === "number") || !(obj.Awards % 1 === 0)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "object" && !(obj.hasRating instanceof Array) && (!this.isOfExactType_Rating_1(obj.hasRating)))
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "object")
				|| obj.hasRating[0] == null || !this.isOfExactType_Rating_1(obj.hasRating[0])
				)) || (typeof obj.hasRating !== "object" && obj.hasRating.constructor !== Array)))
			return false;
		if ("hasRating" in obj && !((typeof obj.hasRating === "string" && false)
				|| (obj.hasRating.constructor === Array && (1 > obj.hasRating.size || 1 < obj.hasRating.size || !checkAllOf(obj.hasRating, "string"))
				|| (typeof obj.hasRating !== "string" && obj.hasRating.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Producer_1: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Budget" in obj) || !(typeof obj.Budget === "number") || (obj.Budget % 1 === 0))
			return false;
		if (!("hasStudio" in obj) || (typeof obj.hasStudio === "object" && !(obj.hasStudio instanceof Array) && (!this.isOfExactType_Studio_1(obj.hasStudio)))
				|| (obj.hasStudio.constructor === Array && (1 > obj.hasStudio.size || !checkAllOf(obj.hasStudio, "object")
				|| obj.hasStudio[0] == null || !this.isOfExactType_Studio_1(obj.hasStudio[0])
				)) || (typeof obj.hasStudio !== "object" && obj.hasStudio.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Producer"))
			return false;
		if ("Budget_per_year" in obj && !(!(typeof obj.Budget_per_year === "number") || (obj.Budget_per_year % 1 === 0)))
			return false;
		if ("Movies_per_year" in obj && !(!(typeof obj.Movies_per_year === "number") || !(obj.Movies_per_year % 1 === 0)))
			return false;
		if ("hasStudio" in obj && !((typeof obj.hasStudio === "object" && !(obj.hasStudio instanceof Array) && (!this.isOfExactType_Studio_2(obj.hasStudio)))
				|| (obj.hasStudio.constructor === Array && (1 > obj.hasStudio.size || !checkAllOf(obj.hasStudio, "object")
				|| obj.hasStudio[0] == null || !this.isOfExactType_Studio_2(obj.hasStudio[0])
				)) || (typeof obj.hasStudio !== "object" && obj.hasStudio.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Producer_2: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Budget_per_year" in obj) || !(typeof obj.Budget_per_year === "number") || (obj.Budget_per_year % 1 === 0))
			return false;
		if (!("Movies_per_year" in obj) || !(typeof obj.Movies_per_year === "number") || !(obj.Movies_per_year % 1 === 0))
			return false;
		if (!("hasStudio" in obj) || (typeof obj.hasStudio === "object" && !(obj.hasStudio instanceof Array) && (!this.isOfExactType_Studio_2(obj.hasStudio)))
				|| (obj.hasStudio.constructor === Array && (1 > obj.hasStudio.size || !checkAllOf(obj.hasStudio, "object")
				|| obj.hasStudio[0] == null || !this.isOfExactType_Studio_2(obj.hasStudio[0])
				)) || (typeof obj.hasStudio !== "object" && obj.hasStudio.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Producer"))
			return false;
		if ("Budget" in obj && !(!(typeof obj.Budget === "number") || (obj.Budget % 1 === 0)))
			return false;
		if ("hasStudio" in obj && !((typeof obj.hasStudio === "object" && !(obj.hasStudio instanceof Array) && (!this.isOfExactType_Studio_1(obj.hasStudio)))
				|| (obj.hasStudio.constructor === Array && (1 > obj.hasStudio.size || !checkAllOf(obj.hasStudio, "object")
				|| obj.hasStudio[0] == null || !this.isOfExactType_Studio_1(obj.hasStudio[0])
				)) || (typeof obj.hasStudio !== "object" && obj.hasStudio.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Rating_1: function (obj)
	{
		if (!("Rating" in obj) || !(typeof obj.Rating === "number") || (obj.Rating % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Rating"))
			return false;
		if ("Votes" in obj && !(!(typeof obj.Votes === "number") || !(obj.Votes % 1 === 0)))
			return false;

		return true;
	},

	isOfExactType_Rating_2: function (obj)
	{
		if (!("Rating" in obj) || !(typeof obj.Rating === "number") || (obj.Rating % 1 === 0))
			return false;
		if (!("Votes" in obj) || !(typeof obj.Votes === "number") || !(obj.Votes % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Rating"))
			return false;

		return true;
	},

	isOfExactType_Studio_1: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Location" in obj) || !(typeof obj.Location === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "string"))
			return false;
		if (!("Website" in obj) || !(typeof obj.Website === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Studio"))
			return false;
		if ("Year" in obj && !(!(typeof obj.Year === "number") || !(obj.Year % 1 === 0)))
			return false;
		if ("Founder" in obj && !(!(typeof obj.Founder === "string")))
			return false;

		return true;
	},

	isOfExactType_Studio_2: function (obj)
	{
		if (!("Name" in obj) || !(typeof obj.Name === "string"))
			return false;
		if (!("Location" in obj) || !(typeof obj.Location === "string"))
			return false;
		if (!("Year" in obj) || !(typeof obj.Year === "number") || !(obj.Year % 1 === 0))
			return false;
		if (!("Website" in obj) || !(typeof obj.Website === "string"))
			return false;
		if (!("Founder" in obj) || !(typeof obj.Founder === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Studio"))
			return false;
		if ("Year" in obj && !(!(typeof obj.Year === "string")))
			return false;

		return true;
	}

};

