var DiffMethodsInclusive =
{
	isOfExactType_ArtisticType_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("authorsNumber" in obj) || !(typeof obj.authorsNumber === "number") || !(obj.authorsNumber % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "ArtisticType"))
			return false;

		return true;
	},

	isOfExactType_ArtisticType_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("popularity" in obj) || !(typeof obj.popularity === "number") || (obj.popularity % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "ArtisticType"))
			return false;

		return true;
	},

	isOfExactType_Style_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("period" in obj) || !(obj.period.constructor === Array) || (!checkAllOf(obj.period, "int")))
			return false;
		if (!("featured_authors" in obj) || !(obj.featured_authors.constructor === Array) || (!checkAllOf(obj.featured_authors, "string")))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Style"))
			return false;

		return true;
	},

	isOfExactType_Piece_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("author" in obj) || !(typeof obj.author === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Piece"))
			return false;

		return true;
	},

	isOfExactType_Piece_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("author" in obj) || !(typeof obj.author === "string"))
			return false;
		if (!("material" in obj) || !(typeof obj.material === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Piece"))
			return false;

		return true;
	},

	isOfExactType_Piece_3: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("author" in obj) || !(typeof obj.author === "string"))
			return false;
		if (!("material" in obj) || !(typeof obj.material === "string"))
			return false;
		if (!("artisticType" in obj) || !(typeof obj.artisticType === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Piece"))
			return false;

		return true;
	},

	isOfExactType_Piece_4: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("author" in obj) || !(typeof obj.author === "string"))
			return false;
		if (!("material" in obj) || !(typeof obj.material === "string"))
			return false;
		if (!("artisticType" in obj) || !(typeof obj.artisticType === "string"))
			return false;
		if (!("style" in obj) || !(typeof obj.style === "string"))
			return false;
		if (!("size" in obj) || !(obj.size.constructor === Array) || (!checkAllOf(obj.size, "float")))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Piece"))
			return false;

		return true;
	},

	isOfExactType_Piece_5: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("author" in obj) || !(typeof obj.author === "string"))
			return false;
		if (!("material" in obj) || !(typeof obj.material === "string"))
			return false;
		if (!("artisticType" in obj) || !(typeof obj.artisticType === "string"))
			return false;
		if (!("size" in obj) || !(obj.size.constructor === Array) || (!checkAllOf(obj.size, "float")))
			return false;
		if (!("hasStyle" in obj) || (typeof obj.hasStyle === "object" && !(obj.hasStyle instanceof Array) && (!this.isOfExactType_Style_1(obj.hasStyle)))
				|| (obj.hasStyle.constructor === Array && (1 > obj.hasStyle.size || !checkAllOf(obj.hasStyle, "object")
				|| obj.hasStyle[0] == null || !this.isOfExactType_Style_1(obj.hasStyle[0])
				)) || (typeof obj.hasStyle !== "object" && obj.hasStyle.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Piece"))
			return false;

		return true;
	},

	isOfExactType_Piece_6: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("author" in obj) || !(typeof obj.author === "string"))
			return false;
		if (!("material" in obj) || !(typeof obj.material === "string"))
			return false;
		if (!("size" in obj) || !(obj.size.constructor === Array) || (!checkAllOf(obj.size, "float")))
			return false;
		if (!("hasStyle" in obj) || (typeof obj.hasStyle === "object" && !(obj.hasStyle instanceof Array) && (!this.isOfExactType_Style_1(obj.hasStyle)))
				|| (obj.hasStyle.constructor === Array && (1 > obj.hasStyle.size || !checkAllOf(obj.hasStyle, "object")
				|| obj.hasStyle[0] == null || !this.isOfExactType_Style_1(obj.hasStyle[0])
				)) || (typeof obj.hasStyle !== "object" && obj.hasStyle.constructor !== Array))
			return false;
		if (!("hasArtisticType" in obj) || (typeof obj.hasArtisticType === "string" && false)
				|| (obj.hasArtisticType.constructor === Array && (1 > obj.hasArtisticType.size || 1 < obj.hasArtisticType.size || !checkAllOf(obj.hasArtisticType, "string"))
				|| (typeof obj.hasArtisticType !== "string" && obj.hasArtisticType.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Piece"))
			return false;

		return true;
	},

	isOfExactType_Exposing_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("date" in obj) || !(obj.date.constructor === Array) || (!checkAllOf(obj.date, "string")))
			return false;
		if (!("hasPieces" in obj) || (typeof obj.hasPieces === "string" && false)
				|| (obj.hasPieces.constructor === Array && (1 > obj.hasPieces.size || !checkAllOf(obj.hasPieces, "string"))
				|| (typeof obj.hasPieces !== "string" && obj.hasPieces.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Exposing"))
			return false;

		return true;
	},

	isOfExactType_Exposing_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("date" in obj) || !(obj.date.constructor === Array) || (!checkAllOf(obj.date, "string")))
			return false;
		if (!("hasPieces" in obj) || (typeof obj.hasPieces === "string" && false)
				|| (obj.hasPieces.constructor === Array && (1 > obj.hasPieces.size || !checkAllOf(obj.hasPieces, "string"))
				|| (typeof obj.hasPieces !== "string" && obj.hasPieces.constructor !== Array)))
			return false;
		if (!("isItinerant" in obj) || !(typeof obj.isItinerant === "boolean") || ((obj.isItinerant !== true) && (obj.isItinerant !== false)))
			return false;
		if (!("isTemporary" in obj) || !(typeof obj.isTemporary === "boolean") || ((obj.isTemporary !== true) && (obj.isTemporary !== false)))
			return false;
		if (!("isPermanent" in obj) || !(typeof obj.isPermanent === "boolean") || ((obj.isPermanent !== true) && (obj.isPermanent !== false)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Exposing"))
			return false;

		return true;
	},

	isOfExactType_Restorer_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("hours_per_week" in obj) || !(typeof obj.hours_per_week === "number") || !(obj.hours_per_week % 1 === 0))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Restorer"))
			return false;

		return true;
	},

	isOfExactType_Restorer_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("tools" in obj) || !(obj.tools.constructor === Array) || (!checkAllOf(obj.tools, "string")))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Restorer"))
			return false;

		return true;
	},

	isOfExactType_Guide_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("showsExposings" in obj) || (typeof obj.showsExposings === "string" && false)
				|| (obj.showsExposings.constructor === Array && (0 > obj.showsExposings.size || !checkAllOf(obj.showsExposings, "string"))
				|| (typeof obj.showsExposings !== "string" && obj.showsExposings.constructor !== Array)))
			return false;
		if (!("showsPieces" in obj) || (typeof obj.showsPieces === "string" && false)
				|| (obj.showsPieces.constructor === Array && (0 > obj.showsPieces.size || !checkAllOf(obj.showsPieces, "string"))
				|| (typeof obj.showsPieces !== "string" && obj.showsPieces.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Guide"))
			return false;

		return true;
	},

	isOfExactType_Guide_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("showsExposings" in obj) || (typeof obj.showsExposings === "string" && false)
				|| (obj.showsExposings.constructor === Array && (0 > obj.showsExposings.size || !checkAllOf(obj.showsExposings, "string"))
				|| (typeof obj.showsExposings !== "string" && obj.showsExposings.constructor !== Array)))
			return false;
		if (!("showsPieces" in obj) || (typeof obj.showsPieces === "string" && false)
				|| (obj.showsPieces.constructor === Array && (0 > obj.showsPieces.size || !checkAllOf(obj.showsPieces, "string"))
				|| (typeof obj.showsPieces !== "string" && obj.showsPieces.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Guide"))
			return false;

		return true;
	},

	isOfExactType_Guide_3: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("languages" in obj) || !(obj.languages.constructor === Array) || (!checkAllOf(obj.languages, "string")))
			return false;
		if (!("showsPieces" in obj) || (typeof obj.showsPieces === "string" && false)
				|| (obj.showsPieces.constructor === Array && (0 > obj.showsPieces.size || !checkAllOf(obj.showsPieces, "string"))
				|| (typeof obj.showsPieces !== "string" && obj.showsPieces.constructor !== Array)))
			return false;
		if (!("showsExposings" in obj) || (typeof obj.showsExposings === "object" && !(obj.showsExposings instanceof Array) && (!this.isOfExactType_Exposing_2(obj.showsExposings)))
				|| (obj.showsExposings.constructor === Array && (0 > obj.showsExposings.size || !checkAllOf(obj.showsExposings, "object")
				|| obj.showsExposings[0] == null || !this.isOfExactType_Exposing_2(obj.showsExposings[0])
				)) || (typeof obj.showsExposings !== "object" && obj.showsExposings.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Guide"))
			return false;

		return true;
	},

	isOfExactType_Director_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("hours_per_week" in obj) || !(typeof obj.hours_per_week === "number") || !(obj.hours_per_week % 1 === 0))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Director"))
			return false;

		return true;
	},

	isOfExactType_Museum_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("location" in obj) || !(typeof obj.location === "string"))
			return false;
		if (!("hasPieces" in obj) || (typeof obj.hasPieces === "string" && false)
				|| (obj.hasPieces.constructor === Array && (1 > obj.hasPieces.size || !checkAllOf(obj.hasPieces, "string"))
				|| (typeof obj.hasPieces !== "string" && obj.hasPieces.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Museum"))
			return false;

		return true;
	},

	isOfExactType_Museum_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("location" in obj) || !(typeof obj.location === "string"))
			return false;
		if (!("foundationYear" in obj) || !(typeof obj.foundationYear === "number") || !(obj.foundationYear % 1 === 0))
			return false;
		if (!("hasPieces" in obj) || (typeof obj.hasPieces === "string" && false)
				|| (obj.hasPieces.constructor === Array && (1 > obj.hasPieces.size || !checkAllOf(obj.hasPieces, "string"))
				|| (typeof obj.hasPieces !== "string" && obj.hasPieces.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Museum"))
			return false;

		return true;
	},

	isOfExactType_Museum_3: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("location" in obj) || !(typeof obj.location === "string"))
			return false;
		if (!("foundationYear" in obj) || !(typeof obj.foundationYear === "number") || !(obj.foundationYear % 1 === 0))
			return false;
		if (!("hasPieces" in obj) || (typeof obj.hasPieces === "string" && false)
				|| (obj.hasPieces.constructor === Array && (1 > obj.hasPieces.size || !checkAllOf(obj.hasPieces, "string"))
				|| (typeof obj.hasPieces !== "string" && obj.hasPieces.constructor !== Array)))
			return false;
		if (!("schedule" in obj) || !(obj.schedule.constructor === Array) || !(obj.schedule.length === 2)
			|| !(obj.schedule[0].constructor === Array) || !(obj.schedule[0].length === 3)
				|| !(typeof obj.schedule[0][0] === "string")
				|| !(typeof obj.schedule[0][1] === "number") || !(obj.schedule[0][1] % 1 === 0)
				|| !(typeof obj.schedule[0][2] === "number") || !(obj.schedule[0][2] % 1 === 0)
			|| !(obj.schedule[1].constructor === Array) || !(obj.schedule[1].length === 3)
				|| !(typeof obj.schedule[1][0] === "string")
				|| !(typeof obj.schedule[1][1] === "number") || !(obj.schedule[1][1] % 1 === 0)
				|| !(typeof obj.schedule[1][2] === "number") || !(obj.schedule[1][2] % 1 === 0)
		)
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Museum"))
			return false;

		return true;
	},

	isOfExactType_Museum_4: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("location" in obj) || !(typeof obj.location === "string"))
			return false;
		if (!("foundationYear" in obj) || !(typeof obj.foundationYear === "number") || !(obj.foundationYear % 1 === 0))
			return false;
		if (!("hasPieces" in obj) || (typeof obj.hasPieces === "string" && false)
				|| (obj.hasPieces.constructor === Array && (1 > obj.hasPieces.size || !checkAllOf(obj.hasPieces, "string"))
				|| (typeof obj.hasPieces !== "string" && obj.hasPieces.constructor !== Array)))
			return false;
		if (!("schedule" in obj) || !(obj.schedule.constructor === Array) || !(obj.schedule.length === 2)
			|| !(obj.schedule[0].constructor === Array) || !(obj.schedule[0].length === 3)
				|| !(typeof obj.schedule[0][0] === "string")
				|| !(typeof obj.schedule[0][1] === "number") || !(obj.schedule[0][1] % 1 === 0)
				|| !(typeof obj.schedule[0][2] === "number") || !(obj.schedule[0][2] % 1 === 0)
			|| !(obj.schedule[1].constructor === Array) || !(obj.schedule[1].length === 3)
				|| !(typeof obj.schedule[1][0] === "string")
				|| !(typeof obj.schedule[1][1] === "number") || !(obj.schedule[1][1] % 1 === 0)
				|| !(typeof obj.schedule[1][2] === "number") || !(obj.schedule[1][2] % 1 === 0)
		)
			return false;
		if (!("hasExposings" in obj) || (typeof obj.hasExposings === "string" && false)
				|| (obj.hasExposings.constructor === Array && (0 > obj.hasExposings.size || !checkAllOf(obj.hasExposings, "string"))
				|| (typeof obj.hasExposings !== "string" && obj.hasExposings.constructor !== Array)))
			return false;
		if (!("hasDirector" in obj) || (typeof obj.hasDirector === "object" && !(obj.hasDirector instanceof Array) && (!this.isOfExactType_Director_1(obj.hasDirector)))
				|| (obj.hasDirector.constructor === Array && (1 > obj.hasDirector.size || 1 < obj.hasDirector.size || !checkAllOf(obj.hasDirector, "object")
				|| obj.hasDirector[0] == null || !this.isOfExactType_Director_1(obj.hasDirector[0])
				)) || (typeof obj.hasDirector !== "object" && obj.hasDirector.constructor !== Array))
			return false;
		if (!("hasGuides" in obj) || (typeof obj.hasGuides === "object" && !(obj.hasGuides instanceof Array) && (!this.isOfExactType_Guide_3(obj.hasGuides)))
				|| (obj.hasGuides.constructor === Array && (0 > obj.hasGuides.size || !checkAllOf(obj.hasGuides, "object")
				|| obj.hasGuides[0] == null || !this.isOfExactType_Guide_3(obj.hasGuides[0])
				)) || (typeof obj.hasGuides !== "object" && obj.hasGuides.constructor !== Array))
			return false;
		if (!("hasGuards" in obj) || (typeof obj.hasGuards === "object" && !(obj.hasGuards instanceof Array) && (!this.isOfExactType_Guard_1(obj.hasGuards)))
				|| (obj.hasGuards.constructor === Array && (0 > obj.hasGuards.size || !checkAllOf(obj.hasGuards, "object")
				|| obj.hasGuards[0] == null || !this.isOfExactType_Guard_1(obj.hasGuards[0])
				)) || (typeof obj.hasGuards !== "object" && obj.hasGuards.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Museum"))
			return false;

		return true;
	},

	isOfExactType_Guard_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("hours_per_week" in obj) || !(typeof obj.hours_per_week === "number") || !(obj.hours_per_week % 1 === 0))
			return false;
		if (!("salary" in obj) || !(typeof obj.salary === "number") || (obj.salary % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Guard"))
			return false;

		return true;
	}

};

