  

//File Book1
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var bookSchema = new mongoose.Schema({
title:	String,
_id:	String,
type:	String,
publisher_id:	String,
year:	Number,
content:	{
authors:	{
	chapters:	Number,
	pages:	Number,
} 
	_id:	String,
	name:	String,
	country:	String,
	company:	{
		country:	String,
		name:	String,
	} 
	company:	String,
} 

var companySchema = new mongoose.Schema({
	country:	String,
	name:	String,
},
{collection:'company'});
var Company = mongoose.model('Company',companySchema);
        
Company.find(function(err,company){
	if (err) return console.error(err);
	var stringVar="\nCompany:";
	console.log(stringVar);
	console.log(company);
});
},

{collection:'book'});
var Book = mongoose.model('Book',bookSchema);


//File Book2
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var bookSchema = new mongoose.Schema({
title:	String,
_id:	String,
type:	String,
publisher_id:	String,
author:	{
	chapters:	Number,
	pages:	Number,
} 
	_id:	String,
	name:	String,
	country:	String,
	company:	{
		country:	String,
		name:	String,
	} 
	company:	String,
} 

var companySchema = new mongoose.Schema({
	country:	String,
	name:	String,
},
{collection:'company'});
var Company = mongoose.model('Company',companySchema);
        
Company.find(function(err,company){
	if (err) return console.error(err);
	var stringVar="\nCompany:";
	console.log(stringVar);
	console.log(company);
});
	_id:	String,
	name:	String,
	company:	{
		country:	String,
		name:	String,
	} 
} 
},

{collection:'book'});
var Book = mongoose.model('Book',bookSchema);

  

//File Journal1
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var journalSchema = new mongoose.Schema({
_id:	String,
issn:	[],
name:	String,
discipline:	String,
type:	String,
},

{collection:'journal'});
var Journal = mongoose.model('Journal',journalSchema);


//File Journal2
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var journalSchema = new mongoose.Schema({
_id:	String,
issn:	[],
name:	String,
discipline:	String,
type:	String,
number:	Number,
},

{collection:'journal'});
var Journal = mongoose.model('Journal',journalSchema);

  

//File Publisher1
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({
_id:	String,
name:	String,
type:	String,
city:	String,
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);


//File Publisher2
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({
_id:	String,
name:	String,
type:	String,
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);


//File Publisher3
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({
_id:	String,
name:	String,
type:	String,
journals:	{},
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);


var journalSchema = new mongoose.Schema({
	_id:	String,
	discipline:	String,
	issn:	[],
	name:	String,
	type:	String,
	number:	Number,
},
{collection:'journal'});
var Journal = mongoose.model('Journal',journalSchema);
        
Journal.find(function(err,journal){
	if (err) return console.error(err);
	var stringVar="\nJournal:";
	console.log(stringVar);
	console.log(journal);
});

//File Publisher4
var mongoose = require('mongoose');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({
_id:	String,
name:	String,
type:	String,
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);


var journalSchema = new mongoose.Schema({
	_id:	String,
	discipline:	String,
	issn:	[],
	name:	String,
	type:	String,
	number:	Number,
},
{collection:'journal'});
var Journal = mongoose.model('Journal',journalSchema);
        
Journal.find(function(err,journal){
	if (err) return console.error(err);
	var stringVar="\nJournal:";
	console.log(stringVar);
	console.log(journal);
});

