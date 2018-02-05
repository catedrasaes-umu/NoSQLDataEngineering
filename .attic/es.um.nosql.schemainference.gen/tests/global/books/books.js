
//File Book1
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});

authorObj=	{
		_id:	String,
		name:	String,
	company:	{
			country:	String,
			name:	String,
	} 
} 
contentObj=	{
		chapters:	Number,
		pages:	Number,
} 
authorsObj=	{
		_id:	String,
		name:	String,
		country:	String,
	company:	{
			country:	String,
			name:	String,
	} 
		company:	String,
} 
  
var bookSchema = new mongoose.Schema({
  	title:	{type:String, requerid:true},
  	_id:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	publisher_id:	{type: String, requerid: true}
  	year:	Number,
  	author:	authorObj,
  	content:	contentObj,
  	authors:	authorsObj,
  
},{collection:'book'});

bookSchema.path('year').required()
bookSchema.path('content').required()
bookSchema.path('authors').required()

var Book = mongoose.model('Book',bookSchema);

//File Book2
var mongoose = require('mongoose');
var assert=require('assert');
var url='mongodb://localhost/dbmongoose'
mongoose.connect(url, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});

authorObj=	{
		_id:	String,
		name:	String,
	company:	{
			country:	String,
			name:	String,
	} 
} 
contentObj=	{
		chapters:	Number,
		pages:	Number,
} 
authorsObj=	{
		_id:	String,
		name:	String,
		country:	String,
	company:	{
			country:	String,
			name:	String,
	} 
		company:	String,
} 
  
var bookSchema = new mongoose.Schema({
  	title:	{type:String, requerid:true},
  	_id:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	publisher_id:	{type: String, requerid: true}
  	year:	Number,
  	author:	authorObj,
  	content:	contentObj,
  	authors:	authorsObj,
  
},{collection:'book'});

bookSchema.path('author').required()

var Book = mongoose.model('Book',bookSchema);



//File Journal1
var mongoose = require('mongoose');
var assert=require('assert');
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
  	_id:	{type:String, requerid:true},
  	issn:	{type:[], required:true},
  	name:	{type:String, requerid:true},
  	discipline:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	number:	Number,
  
},{collection:'journal'});


var Journal = mongoose.model('Journal',journalSchema);

//File Journal2
var mongoose = require('mongoose');
var assert=require('assert');
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
  	_id:	{type:String, requerid:true},
  	issn:	{type:[], required:true},
  	name:	{type:String, requerid:true},
  	discipline:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	number:	Number,
  
},{collection:'journal'});

journalSchema.path('number').required()

var Journal = mongoose.model('Journal',journalSchema);



//File Publisher1
var mongoose = require('mongoose');
var assert=require('assert');
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
  	_id:	{type:String, requerid:true},
  	name:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	city:	String,
  	journals:	{},
  
},{collection:'publisher'});

publisherSchema.path('city').required()

var Publisher = mongoose.model('Publisher',publisherSchema);

//File Publisher2
var mongoose = require('mongoose');
var assert=require('assert');
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
  	_id:	{type:String, requerid:true},
  	name:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	city:	String,
  	journals:	{},
  
},{collection:'publisher'});


var Publisher = mongoose.model('Publisher',publisherSchema);

//File Publisher3
var mongoose = require('mongoose');
var assert=require('assert');
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
  	_id:	{type:String, requerid:true},
  	name:	{type:String, requerid:true},
  	type:	{type:String, requerid:true},
  	city:	String,
  	journals:	{},
  
},{collection:'publisher'});

publisherSchema.path('journals').required()

var Publisher = mongoose.model('Publisher',publisherSchema);



