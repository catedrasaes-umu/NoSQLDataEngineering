var DiffMethodsExclusive =
{
	isOfExactType_Book_1: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Book"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
		)
			return false;
		if (!("atTuple_1" in obj) || !(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "number") || (obj.atTuple_1[0] % 1 === 0)
			|| !(typeof obj.atTuple_1[1] === "number") || (obj.atTuple_1[1] % 1 === 0)
			|| !(typeof obj.atTuple_1[2] === "string")
		)
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Book_2: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Book"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "number") || (obj.atTuple_1[0] % 1 === 0)
			|| !(typeof obj.atTuple_1[1] === "number") || (obj.atTuple_1[1] % 1 === 0)
			|| !(typeof obj.atTuple_1[2] === "string")
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Book_3: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Book"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "number") || (obj.atTuple_1[0] % 1 === 0)
			|| !(typeof obj.atTuple_1[1] === "number") || (obj.atTuple_1[1] % 1 === 0)
			|| !(typeof obj.atTuple_1[2] === "string")
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Book_4: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Book"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("Book_id_reference" in obj) || (typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array)))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "number") || (obj.atTuple_1[0] % 1 === 0)
			|| !(typeof obj.atTuple_1[1] === "number") || (obj.atTuple_1[1] % 1 === 0)
			|| !(typeof obj.atTuple_1[2] === "string")
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Author_1: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string"))))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (0 > obj.Publisher_id_reference.size || 2 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Author_2: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		)
			return false;
		if (!("atTuple_1" in obj) || !(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		)
			return false;
		if (!("Book_id_reference" in obj) || (typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array)))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string"))))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (0 > obj.Publisher_id_reference.size || 2 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Author_3: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		)
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string"))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Author_4: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atFloat_1" in obj) || !(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		)
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if ("atStr_1" in obj && !(!(typeof obj.atStr_1 === "string")))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string"))))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (0 > obj.Publisher_id_reference.size || 2 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Author_5: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atStr_3" in obj) || !(typeof obj.atStr_3 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atFloat_1" in obj) || !(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		)
			return false;
		if (!("atTuple_1" in obj) || !(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string")))
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (0 > obj.Publisher_id_reference.size || 2 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Author_6: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atStr_1" in obj && !(!(typeof obj.atStr_1 === "string")))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string"))))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (0 > obj.Publisher_id_reference.size || 2 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Author_7: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Author"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atStr_3" in obj) || !(typeof obj.atStr_3 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		)
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if (!("aggregates_0" in obj) || (typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array))
			return false;
		if (!("aggregates_2" in obj) || (typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_3(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (1 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_3(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "boolean") || ((obj.atTuple_0[2] !== true) && (obj.atTuple_0[2] !== false))
		))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 4)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 2)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 2)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[1][1] === "number") || !(obj.atTuple_0[1][1] % 1 === 0)
			|| !(typeof obj.atTuple_0[2] === "string")
			|| !(typeof obj.atTuple_0[3] === "boolean") || ((obj.atTuple_0[3] !== true) && (obj.atTuple_0[3] !== false))
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "string"))))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (0 > obj.Publisher_id_reference.size || 2 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(obj.atTuple_0[1].constructor === Array) || !(obj.atTuple_0[1].length === 1)
				|| !(typeof obj.atTuple_0[1][0] === "number") || !(obj.atTuple_0[1][0] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "boolean") || ((obj.atTuple_1[1] !== true) && (obj.atTuple_1[1] !== false))
			|| !(obj.atTuple_1[2].constructor === Array) || !(obj.atTuple_1[2].length === 2)
				|| !(typeof obj.atTuple_1[2][0] === "number") || !(obj.atTuple_1[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_1[2][1] === "number") || !(obj.atTuple_1[2][1] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "number") || !(obj.atTuple_0[1] % 1 === 0)
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 3)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][1] === "number") || !(obj.atTuple_0[2][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[2][2] === "number") || !(obj.atTuple_0[2][2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Journal_1: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 3)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][2] === "number") || !(obj.atTuple_0[0][2] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		)
			return false;
		if (!("atTuple_1" in obj) || !(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "int")))
			return false;
		if (!("Book_id_reference" in obj) || (typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array)))
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if (!("aggregates_0" in obj) || (typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Book_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Book_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Journal_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (1 > obj.aggregates_1.size || 3 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Journal_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "float"))))
			return false;
		if ("Content_id_reference" in obj && !((typeof obj.Content_id_reference === "string" && false)
				|| (obj.Content_id_reference.constructor === Array && (1 > obj.Content_id_reference.size || 1 < obj.Content_id_reference.size || !checkAllOf(obj.Content_id_reference, "string"))
				|| (typeof obj.Content_id_reference !== "string" && obj.Content_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "int"))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (0 > obj.aggregates_1.size || 2 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Author_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (0 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_1(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Journal_2: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "float")))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if (!("Content_id_reference" in obj) || (typeof obj.Content_id_reference === "string" && false)
				|| (obj.Content_id_reference.constructor === Array && (1 > obj.Content_id_reference.size || 1 < obj.Content_id_reference.size || !checkAllOf(obj.Content_id_reference, "string"))
				|| (typeof obj.Content_id_reference !== "string" && obj.Content_id_reference.constructor !== Array)))
			return false;
		if ("atStr_1" in obj && !(!(typeof obj.atStr_1 === "string")))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 3)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][2] === "number") || !(obj.atTuple_0[0][2] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "int"))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Book_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Book_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Journal_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (1 > obj.aggregates_1.size || 3 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Journal_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "int"))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (0 > obj.aggregates_1.size || 2 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Author_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (0 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_1(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Journal_3: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atStr_3" in obj) || !(typeof obj.atStr_3 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (0 > obj.Author_id_reference.size || 2 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if (!("aggregates_1" in obj) || (typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Journal_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (1 > obj.aggregates_1.size || 3 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Journal_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 3)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][2] === "number") || !(obj.atTuple_0[0][2] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "int"))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Book_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Book_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "float"))))
			return false;
		if ("Content_id_reference" in obj && !((typeof obj.Content_id_reference === "string" && false)
				|| (obj.Content_id_reference.constructor === Array && (1 > obj.Content_id_reference.size || 1 < obj.Content_id_reference.size || !checkAllOf(obj.Content_id_reference, "string"))
				|| (typeof obj.Content_id_reference !== "string" && obj.Content_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "int"))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (0 > obj.aggregates_1.size || 2 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Author_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (0 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_1(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Journal_4: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atStr_3" in obj) || !(typeof obj.atStr_3 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("aggregates_1" in obj) || (typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (0 > obj.aggregates_1.size || 2 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Author_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array))
			return false;
		if (!("aggregates_2" in obj) || (typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (0 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_1(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 3)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][2] === "number") || !(obj.atTuple_0[0][2] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "int"))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Book_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Book_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Journal_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (1 > obj.aggregates_1.size || 3 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Journal_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "float"))))
			return false;
		if ("Content_id_reference" in obj && !((typeof obj.Content_id_reference === "string" && false)
				|| (obj.Content_id_reference.constructor === Array && (1 > obj.Content_id_reference.size || 1 < obj.Content_id_reference.size || !checkAllOf(obj.Content_id_reference, "string"))
				|| (typeof obj.Content_id_reference !== "string" && obj.Content_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "int"))))
			return false;

		return true;
	},

	isOfExactType_Journal_5: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("atInt_2" in obj) || !(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 3)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][2] === "number") || !(obj.atTuple_0[0][2] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "int"))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Book_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Book_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Journal_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (1 > obj.aggregates_1.size || 3 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Journal_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "float"))))
			return false;
		if ("Content_id_reference" in obj && !((typeof obj.Content_id_reference === "string" && false)
				|| (obj.Content_id_reference.constructor === Array && (1 > obj.Content_id_reference.size || 1 < obj.Content_id_reference.size || !checkAllOf(obj.Content_id_reference, "string"))
				|| (typeof obj.Content_id_reference !== "string" && obj.Content_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "int"))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (0 > obj.aggregates_1.size || 2 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Author_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (0 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_1(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Journal_6: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Journal"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "int")))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 3)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][1] === "number") || !(obj.atTuple_0[0][1] % 1 === 0)
				|| !(typeof obj.atTuple_0[0][2] === "number") || !(obj.atTuple_0[0][2] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || (!checkAllOf(obj.atTuple_1, "int"))))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Book_1(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Book_1(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Journal_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (1 > obj.aggregates_1.size || 3 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Journal_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || (!checkAllOf(obj.atTuple_0, "float"))))
			return false;
		if ("Content_id_reference" in obj && !((typeof obj.Content_id_reference === "string" && false)
				|| (obj.Content_id_reference.constructor === Array && (1 > obj.Content_id_reference.size || 1 < obj.Content_id_reference.size || !checkAllOf(obj.Content_id_reference, "string"))
				|| (typeof obj.Content_id_reference !== "string" && obj.Content_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_1" in obj && !((typeof obj.aggregates_1 === "object" && !(obj.aggregates_1 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_1)))
				|| (obj.aggregates_1.constructor === Array && (0 > obj.aggregates_1.size || 2 < obj.aggregates_1.size || !checkAllOf(obj.aggregates_1, "object")
				|| obj.aggregates_1[0] == null || !this.isOfExactType_Author_1(obj.aggregates_1[0])
				)) || (typeof obj.aggregates_1 !== "object" && obj.aggregates_1.constructor !== Array)))
			return false;
		if ("aggregates_2" in obj && !((typeof obj.aggregates_2 === "object" && !(obj.aggregates_2 instanceof Array) && (!this.isOfExactType_Author_1(obj.aggregates_2)))
				|| (obj.aggregates_2.constructor === Array && (0 > obj.aggregates_2.size || 3 < obj.aggregates_2.size || !checkAllOf(obj.aggregates_2, "object")
				|| obj.aggregates_2[0] == null || !this.isOfExactType_Author_1(obj.aggregates_2[0])
				)) || (typeof obj.aggregates_2 !== "object" && obj.aggregates_2.constructor !== Array)))
			return false;

		return true;
	},

	isOfExactType_Publisher_1: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atBool_1" in obj) || !(typeof obj.atBool_1 === "boolean") || ((obj.atBool_1 !== true) && (obj.atBool_1 !== false)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atStr_4" in obj && !(!(typeof obj.atStr_4 === "string")))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_6(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_6(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || !(obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(typeof obj.atTuple_0[2] === "string")
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "string")
			|| !(typeof obj.atTuple_1[2] === "number") || (obj.atTuple_1[2] % 1 === 0)
		))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || !(obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Publisher_2: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || !(obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(typeof obj.atTuple_0[2] === "string")
		)
			return false;
		if (!("atTuple_1" in obj) || !(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "string")
			|| !(typeof obj.atTuple_1[2] === "number") || (obj.atTuple_1[2] % 1 === 0)
		)
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atStr_4" in obj && !(!(typeof obj.atStr_4 === "string")))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atBool_1" in obj && !(!(typeof obj.atBool_1 === "boolean") || ((obj.atBool_1 !== true) && (obj.atBool_1 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_6(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_6(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || !(obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Publisher_3: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atStr_3" in obj) || !(typeof obj.atStr_3 === "string"))
			return false;
		if (!("atStr_4" in obj) || !(typeof obj.atStr_4 === "string"))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atFloat_1" in obj) || !(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atBool_1" in obj) || !(typeof obj.atBool_1 === "boolean") || ((obj.atBool_1 !== true) && (obj.atBool_1 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		)
			return false;
		if (!("Book_id_reference" in obj) || (typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array)))
			return false;
		if (!("aggregates_0" in obj) || (typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_6(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_6(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || !(obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(typeof obj.atTuple_0[2] === "string")
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "string")
			|| !(typeof obj.atTuple_1[2] === "number") || (obj.atTuple_1[2] % 1 === 0)
		))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || !(obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Publisher_4: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atFloat_1" in obj) || !(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atBool_1" in obj) || !(typeof obj.atBool_1 === "boolean") || ((obj.atBool_1 !== true) && (obj.atBool_1 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || !(obj.atTuple_0[2] % 1 === 0)
		)
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atStr_4" in obj && !(!(typeof obj.atStr_4 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_6(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_6(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || !(obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(typeof obj.atTuple_0[2] === "string")
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "string")
			|| !(typeof obj.atTuple_1[2] === "number") || (obj.atTuple_1[2] % 1 === 0)
		))
			return false;

		return true;
	},

	isOfExactType_Publisher_5: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		)
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atStr_4" in obj && !(!(typeof obj.atStr_4 === "string")))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atBool_1" in obj && !(!(typeof obj.atBool_1 === "boolean") || ((obj.atBool_1 !== true) && (obj.atBool_1 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_6(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_6(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || !(obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(typeof obj.atTuple_0[2] === "string")
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "string")
			|| !(typeof obj.atTuple_1[2] === "number") || (obj.atTuple_1[2] % 1 === 0)
		))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || !(obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Publisher_6: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Publisher"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 2 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if ("atStr_1" in obj && !(!(typeof obj.atStr_1 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "number") || (obj.atTuple_0[1] % 1 === 0)
		))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atStr_4" in obj && !(!(typeof obj.atStr_4 === "string")))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atFloat_1" in obj && !(!(typeof obj.atFloat_1 === "number") || (obj.atFloat_1 % 1 === 0)))
			return false;
		if ("atBool_1" in obj && !(!(typeof obj.atBool_1 === "boolean") || ((obj.atBool_1 !== true) && (obj.atBool_1 !== false))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(obj.atTuple_0[0].constructor === Array) || !(obj.atTuple_0[0].length === 1)
				|| !(typeof obj.atTuple_0[0][0] === "number") || !(obj.atTuple_0[0][0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || (obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (1 > obj.Book_id_reference.size || 1 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("aggregates_0" in obj && !((typeof obj.aggregates_0 === "object" && !(obj.aggregates_0 instanceof Array) && (!this.isOfExactType_Author_6(obj.aggregates_0)))
				|| (obj.aggregates_0.constructor === Array && (1 > obj.aggregates_0.size || 2 < obj.aggregates_0.size || !checkAllOf(obj.aggregates_0, "object")
				|| obj.aggregates_0[0] == null || !this.isOfExactType_Author_6(obj.aggregates_0[0])
				)) || (typeof obj.aggregates_0 !== "object" && obj.aggregates_0.constructor !== Array)))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || !(obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(typeof obj.atTuple_0[2] === "string")
		))
			return false;
		if ("atTuple_1" in obj && !(!(obj.atTuple_1.constructor === Array) || !(obj.atTuple_1.length === 3)
			|| !(typeof obj.atTuple_1[0] === "boolean") || ((obj.atTuple_1[0] !== true) && (obj.atTuple_1[0] !== false))
			|| !(typeof obj.atTuple_1[1] === "string")
			|| !(typeof obj.atTuple_1[2] === "number") || (obj.atTuple_1[2] % 1 === 0)
		))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(typeof obj.atTuple_0[2] === "number") || !(obj.atTuple_0[2] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Content_1: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Content"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atInt_1" in obj) || !(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0))
			return false;
		if (!("atInt_2" in obj) || !(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		)
			return false;
		if (!("Publisher_id_reference" in obj) || (typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array)))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (1 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || (obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Content_2: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Content"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
		)
			return false;
		if (!("Book_id_reference" in obj) || (typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array)))
			return false;
		if ("Author_id_reference" in obj && !((typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array))))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || (obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Content_3: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Content"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atFloat_0" in obj) || !(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0))
			return false;
		if (!("atBool_0" in obj) || !(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false)))
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if (!("Journal_id_reference" in obj) || (typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array)))
			return false;
		if ("atStr_2" in obj && !(!(typeof obj.atStr_2 === "string")))
			return false;
		if ("atStr_3" in obj && !(!(typeof obj.atStr_3 === "string")))
			return false;
		if ("atInt_0" in obj && !(!(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || (obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	},

	isOfExactType_Content_4: function (obj)
	{
		if (!("type" in obj) || !(typeof obj.type === "string") || (obj.type !== "Content"))
			return false;
		if (!("atStr_0" in obj) || !(typeof obj.atStr_0 === "string"))
			return false;
		if (!("atStr_1" in obj) || !(typeof obj.atStr_1 === "string"))
			return false;
		if (!("atStr_2" in obj) || !(typeof obj.atStr_2 === "string"))
			return false;
		if (!("atStr_3" in obj) || !(typeof obj.atStr_3 === "string"))
			return false;
		if (!("atInt_0" in obj) || !(typeof obj.atInt_0 === "number") || !(obj.atInt_0 % 1 === 0))
			return false;
		if (!("atTuple_0" in obj) || !(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "number") || (obj.atTuple_0[0] % 1 === 0)
			|| !(typeof obj.atTuple_0[1] === "string")
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		)
			return false;
		if (!("Author_id_reference" in obj) || (typeof obj.Author_id_reference === "string" && false)
				|| (obj.Author_id_reference.constructor === Array && (1 > obj.Author_id_reference.size || 1 < obj.Author_id_reference.size || !checkAllOf(obj.Author_id_reference, "string"))
				|| (typeof obj.Author_id_reference !== "string" && obj.Author_id_reference.constructor !== Array)))
			return false;
		if ("atFloat_0" in obj && !(!(typeof obj.atFloat_0 === "number") || (obj.atFloat_0 % 1 === 0)))
			return false;
		if ("atBool_0" in obj && !(!(typeof obj.atBool_0 === "boolean") || ((obj.atBool_0 !== true) && (obj.atBool_0 !== false))))
			return false;
		if ("Journal_id_reference" in obj && !((typeof obj.Journal_id_reference === "string" && false)
				|| (obj.Journal_id_reference.constructor === Array && (0 > obj.Journal_id_reference.size || 1 < obj.Journal_id_reference.size || !checkAllOf(obj.Journal_id_reference, "string"))
				|| (typeof obj.Journal_id_reference !== "string" && obj.Journal_id_reference.constructor !== Array))))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 2)
			|| !(typeof obj.atTuple_0[0] === "string")
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
		))
			return false;
		if ("Book_id_reference" in obj && !((typeof obj.Book_id_reference === "string" && false)
				|| (obj.Book_id_reference.constructor === Array && (0 > obj.Book_id_reference.size || 2 < obj.Book_id_reference.size || !checkAllOf(obj.Book_id_reference, "string"))
				|| (typeof obj.Book_id_reference !== "string" && obj.Book_id_reference.constructor !== Array))))
			return false;
		if ("atInt_1" in obj && !(!(typeof obj.atInt_1 === "number") || !(obj.atInt_1 % 1 === 0)))
			return false;
		if ("atInt_2" in obj && !(!(typeof obj.atInt_2 === "number") || !(obj.atInt_2 % 1 === 0)))
			return false;
		if ("atTuple_0" in obj && !(!(obj.atTuple_0.constructor === Array) || !(obj.atTuple_0.length === 3)
			|| !(typeof obj.atTuple_0[0] === "boolean") || ((obj.atTuple_0[0] !== true) && (obj.atTuple_0[0] !== false))
			|| !(typeof obj.atTuple_0[1] === "boolean") || ((obj.atTuple_0[1] !== true) && (obj.atTuple_0[1] !== false))
			|| !(obj.atTuple_0[2].constructor === Array) || !(obj.atTuple_0[2].length === 1)
				|| !(typeof obj.atTuple_0[2][0] === "number") || !(obj.atTuple_0[2][0] % 1 === 0)
		))
			return false;
		if ("Publisher_id_reference" in obj && !((typeof obj.Publisher_id_reference === "string" && false)
				|| (obj.Publisher_id_reference.constructor === Array && (1 > obj.Publisher_id_reference.size || 1 < obj.Publisher_id_reference.size || !checkAllOf(obj.Publisher_id_reference, "string"))
				|| (typeof obj.Publisher_id_reference !== "string" && obj.Publisher_id_reference.constructor !== Array))))
			return false;

		return true;
	}

};

