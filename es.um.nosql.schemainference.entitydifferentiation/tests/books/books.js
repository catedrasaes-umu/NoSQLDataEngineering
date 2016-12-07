'use strict'

var books = {

	name: "books",
	Company_1: {
		name: "Company_1",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("name" in obj);
			b = b && ("country" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("name" in obj);
			b = b && ("country" in obj);
	        return b;
		}
	},
	Content_1: {
		name: "Content_1",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("chapters" in obj);
			b = b && ("pages" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("chapters" in obj);
			b = b && ("pages" in obj);
	        return b;
		}
	},
	Book_1: {
		name: "Book_1",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Book/i) ? true : false);
			b = b && ("title" in obj);
			b = b && ("publisher_id" in obj);
			b = b && ("_id" in obj);
			b = b && ("year" in obj);
			b = b && ("content" in obj);
			b = b && ("authors" in obj);
			b = b && !("author" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Book/i) ? true : false);
			b = b && ("title" in obj);
			b = b && ("publisher_id" in obj);
			b = b && ("_id" in obj);
			b = b && ("year" in obj);
			b = b && ("content" in obj);
			b = b && ("authors" in obj);
	        return b;
		}
	},
	Book_2: {
		name: "Book_2",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Book/i) ? true : false);
			b = b && ("title" in obj);
			b = b && ("publisher_id" in obj);
			b = b && ("_id" in obj);
			b = b && ("author" in obj);
			b = b && !("year" in obj);
			b = b && !("content" in obj);
			b = b && !("authors" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Book/i) ? true : false);
			b = b && ("title" in obj);
			b = b && ("publisher_id" in obj);
			b = b && ("_id" in obj);
			b = b && ("author" in obj);
	        return b;
		}
	},
	Author_1: {
		name: "Author_1",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("company" in obj) && (typeof obj.company === 'object') && !(obj.company.constructor === Array)
			    && books.Company_1.isOfExactType(obj.company)
			;
			b = b && !("country" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("company" in obj) && (typeof obj.company === 'object') && !(obj.company.constructor === Array)
			    && books.Company_1.isOfExactType(obj.company)
			;
	        return b;
		}
	},
	Author_2: {
		name: "Author_2",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("country" in obj);
			b = b && ("company" in obj) && ((typeof obj.company === "number") || (typeof obj.company === "string"))
			;
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("country" in obj);
			b = b && ("company" in obj) && ((typeof obj.company === "number") || (typeof obj.company === "string"))
			;
	        return b;
		}
	},
	Journal_1: {
		name: "Journal_1",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Journal/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("issn" in obj);
			b = b && ("name" in obj);
			b = b && ("discipline" in obj);
			b = b && !("number" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Journal/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("issn" in obj);
			b = b && ("name" in obj);
			b = b && ("discipline" in obj);
	        return b;
		}
	},
	Journal_2: {
		name: "Journal_2",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Journal/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("issn" in obj);
			b = b && ("name" in obj);
			b = b && ("discipline" in obj);
			b = b && ("number" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Journal/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("issn" in obj);
			b = b && ("name" in obj);
			b = b && ("discipline" in obj);
			b = b && ("number" in obj);
	        return b;
		}
	},
	Publisher_1: {
		name: "Publisher_1",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Publisher/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("city" in obj);
			b = b && !("journals" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Publisher/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("city" in obj);
	        return b;
		}
	},
	Publisher_2: {
		name: "Publisher_2",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Publisher/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && !("city" in obj);
			b = b && !("journals" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Publisher/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
	        return b;
		}
	},
	Publisher_3: {
		name: "Publisher_3",
		isOfExactType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Publisher/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("journals" in obj);
			b = b && !("city" in obj);
			return b;
		},
		isOfType: function (obj)
		{
		    var b = true;
			b = b && ("type" in obj) && (obj.type.match(/Publisher/i) ? true : false);
			b = b && ("_id" in obj);
			b = b && ("name" in obj);
			b = b && ("journals" in obj);
	        return b;
		}
	}
}

module.exports = books;

