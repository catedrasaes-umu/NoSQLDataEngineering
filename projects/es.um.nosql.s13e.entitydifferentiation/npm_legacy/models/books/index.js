'use strict'
var Books =
{
    modelname: "Books",

    Author_1: {
        name: "Author_1",
        isOfType: function (obj)
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

        isOfExactType: function (obj)
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
	    if ("company" in obj && !(!(typeof obj.company === "string")))
		return false;
	    if ("country" in obj && !(!(typeof obj.country === "string")))
		return false;

	    return true;
	}
    }

    Author_2: {

    }

    entities: {
        "Author": [

              ,
              [ "Author_2" ],
              "Book", [ "Book_1", "Book_2" ],
              "Company", [ "Company_1" ],
              "Content", [ "Content_1" ],
              "Journal", [ "Journal_1" "Journal_2" ],
	      "Publisher", [ "Publisher_1", "Publisher_2", "Publisher_3" ]
          ]
};

module.exports = Books;
