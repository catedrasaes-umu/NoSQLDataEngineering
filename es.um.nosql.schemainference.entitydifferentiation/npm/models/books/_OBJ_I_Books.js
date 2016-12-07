var DiffMethodsInclusive =
{
	isOfExactType_Publisher_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("city" in obj) || !(typeof obj.city === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;

		return true;
	},

	isOfExactType_Publisher_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;

		return true;
	},

	isOfExactType_Publisher_3: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("journal" in obj) || (typeof obj.journal === "string" && (1 > 1))
				|| (obj.journal.constructor === Array && (1 > obj.journal.size || !checkAllOf(obj.journal, "string"))
				|| (typeof obj.journal !== "string" && obj.journal.constructor !== Array)))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;

		return true;
	},

	isOfExactType_Journal_1: function (obj)
	{
		if (!("issn" in obj) || !(obj.issn.constructor === Array) || (!checkAllOf(obj.issn, "String")))
			return false;
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("discipline" in obj) || !(typeof obj.discipline === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;

		return true;
	},

	isOfExactType_Journal_2: function (obj)
	{
		if (!("issn" in obj) || !(obj.issn.constructor === Array) || (!checkAllOf(obj.issn, "String")))
			return false;
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("discipline" in obj) || !(typeof obj.discipline === "string"))
			return false;
		if (!("number" in obj) || !(typeof obj.number === "number") || !(obj.number % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;

		return true;
	},

	isOfExactType_Book_1: function (obj)
	{
		if (!("title" in obj) || !(typeof obj.title === "string"))
			return false;
		if (!("year" in obj) || !(typeof obj.year === "number") || !(obj.year % 1 === 0))
			return false;
		if (!("publisher" in obj) || (typeof obj.publisher === "string" && (1 > 1 || 1 < 1))
				|| (obj.publisher.constructor === Array && (1 > obj.publisher.size || 1 < obj.publisher.size || !checkAllOf(obj.publisher, "string"))
				|| (typeof obj.publisher !== "string" && obj.publisher.constructor !== Array)))
			return false;
		if (!("content" in obj) || (typeof obj.content === "object"  && !(obj.content instanceof Array) && (1 > 1 || 1 < 1 || !this.isOfExactType_Content_1(obj.content)))
				|| (obj.content.constructor === Array && (1 > obj.content.size || 1 < obj.content.size || !checkAllOf(obj.content, "object")
				|| obj.content[0] == null || !this.isOfExactType_Content_1(obj.content[0])
				)) || (typeof obj.content !== "object" && obj.content.constructor !== Array))
			return false;
		if (!("author" in obj) || (typeof obj.author === "object"  && !(obj.author instanceof Array) && (1 > 1 || !this.isOfExactType_Author_1(obj.author)))
				|| (obj.author.constructor === Array && (1 > obj.author.size || !checkAllOf(obj.author, "object")
				|| obj.author[0] == null || !this.isOfExactType_Author_1(obj.author[0])
				)) || (typeof obj.author !== "object" && obj.author.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Book"))
			return false;

		return true;
	},

	isOfExactType_Book_2: function (obj)
	{
		if (!("title" in obj) || !(typeof obj.title === "string"))
			return false;
		if (!("publisher" in obj) || (typeof obj.publisher === "string" && (1 > 1 || 1 < 1))
				|| (obj.publisher.constructor === Array && (1 > obj.publisher.size || 1 < obj.publisher.size || !checkAllOf(obj.publisher, "string"))
				|| (typeof obj.publisher !== "string" && obj.publisher.constructor !== Array)))
			return false;
		if (!("author" in obj) || (typeof obj.author === "object"  && !(obj.author instanceof Array) && (1 > 1 || 1 < 1 || !this.isOfExactType_Author_1(obj.author)))
				|| (obj.author.constructor === Array && (1 > obj.author.size || 1 < obj.author.size || !checkAllOf(obj.author, "object")
				|| obj.author[0] == null || !this.isOfExactType_Author_1(obj.author[0])
				)) || (typeof obj.author !== "object" && obj.author.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Book"))
			return false;

		return true;
	},

	isOfExactType_Author_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("company" in obj) || (typeof obj.company === "object"  && !(obj.company instanceof Array) && (1 > 1 || 1 < 1 || !this.isOfExactType_Company_1(obj.company)))
				|| (obj.company.constructor === Array && (1 > obj.company.size || 1 < obj.company.size || !checkAllOf(obj.company, "object")
				|| obj.company[0] == null || !this.isOfExactType_Company_1(obj.company[0])
				)) || (typeof obj.company !== "object" && obj.company.constructor !== Array))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;

		return true;
	},

	isOfExactType_Author_2: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("company" in obj) || !(typeof obj.company === "string"))
			return false;
		if (!("country" in obj) || !(typeof obj.country === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;

		return true;
	},

	isOfExactType_Company_1: function (obj)
	{
		if (!("name" in obj) || !(typeof obj.name === "string"))
			return false;
		if (!("country" in obj) || !(typeof obj.country === "string"))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Company"))
			return false;

		return true;
	},

	isOfExactType_Content_1: function (obj)
	{
		if (!("chapters" in obj) || !(typeof obj.chapters === "number") || !(obj.chapters % 1 === 0))
			return false;
		if (!("pages" in obj) || !(typeof obj.pages === "number") || !(obj.pages % 1 === 0))
			return false;
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Content"))
			return false;

		return true;
	}

};

