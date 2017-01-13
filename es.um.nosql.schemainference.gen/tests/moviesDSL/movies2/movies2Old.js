
//File Movie1
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
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	url:	String,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	name:	[],
    } 
var Movieprizes3=	{
	event:	String,
	name:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type: String, required:true, maxlength: 40, unique: true},
  	_id:	{type: String, required:true, index: Hashed},
  	year:	{type: Number, required:true, index: true},
  	type:	{type: String, required: true},
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie1 entity version
  	genre:	{type: String, required: true},
  PrimitiveType
	criticisms:	{type:Moviecriticisms1, required:true},
	prizes:	{type:Movieprizes1, required:true},
// Not Common Properties 
	genre:	{type: String, enum: [drama, comedy, children]},
		genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

//File Movie2
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
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	url:	String,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	name:	[],
    } 
var Movieprizes3=	{
	event:	String,
	name:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type: String, required:true, maxlength: 40, unique: true},
  	_id:	{type: String, required:true, index: Hashed},
  	year:	{type: Number, required:true, index: true},
  	type:	{type: String, required: true},
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie2 entity version
  	genre:	{type: String, required: true},
  PrimitiveType
// Not Common Properties 
	genre:	{type: String, enum: [drama, comedy, children]},
		genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

//File Movie3
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
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	url:	String,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	name:	[],
    } 
var Movieprizes3=	{
	event:	String,
	name:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type: String, required:true, maxlength: 40, unique: true},
  	_id:	{type: String, required:true, index: Hashed},
  	year:	{type: Number, required:true, index: true},
  	type:	{type: String, required: true},
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie3 entity version
  	genre:	{type: String, required: true},
  PrimitiveType
	prizes:	{type:Movieprizes3, required:true},
// Not Common Properties 
	genre:	{type: String, enum: [drama, comedy, children]},
		genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

//File Movie4
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
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	url:	String,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	name:	[],
    } 
var Movieprizes3=	{
	event:	String,
	name:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type: String, required:true, maxlength: 40, unique: true},
  	_id:	{type: String, required:true, index: Hashed},
  	year:	{type: Number, required:true, index: true},
  	type:	{type: String, required: true},
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie4 entity version
  	genre:	{type: String, required: true},
  PrimitiveType
	criticisms:	{type:Moviecriticisms4, required:true},
// Not Common Properties 
	genre:	{type: String, enum: [drama, comedy, children]},
		genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

//File Movie5
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
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	url:	String,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	name:	[],
    } 
var Movieprizes3=	{
	event:	String,
	name:	String,
	year:	Number,
    } 
var movieSchema = new mongoose.Schema({
// Common Properties	
  	title:	{type: String, required:true, maxlength: 40, unique: true},
  	_id:	{type: String, required:true, index: Hashed},
  	year:	{type: Number, required:true, index: true},
  	type:	{type: String, required: true},
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie5 entity version
  	genres:	{type:[], required:true},
  Tuple
  	writers:	{type:[], required:true},
  Tuple
// Not Common Properties 
	genre:	{type: String, enum: [drama, comedy, children]},
		genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);


//File Director1
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
var directorSchema = new mongoose.Schema({
// Common Properties	
  	_id:	{type: String, required:true, index: Hashed},
  	name:	{type: String, required:true, unique: true},
  	type:	{type: String, required: true},
  
// add required for Director1 entity version
  	actor_movies:	{type:[], required:true},
  Tuple
  	directed_movies:	{type:[], required:true},
  Tuple
// Not Common Properties 
	directed_movies:	[],
	actor_movies:	[],
	directed_movies:	[],
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);

//File Director2
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
var directorSchema = new mongoose.Schema({
// Common Properties	
  	_id:	{type: String, required:true, index: Hashed},
  	name:	{type: String, required:true, unique: true},
  	type:	{type: String, required: true},
  
// add required for Director2 entity version
  	directed_movies:	{type:[], required:true},
  Tuple
// Not Common Properties 
	directed_movies:	[],
	actor_movies:	[],
	directed_movies:	[],
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);


