
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

criticismsObj=	{
		color:	String,
		journalist:	String,
		media:	String,
		url:	String,
} 
prizesObj=	{
		event:	String,
		name:	String,
		year:	Number,
} 
  
var movieSchema = new mongoose.Schema({
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  	genres:	[],
  	writers:	[],
  	genre:	String,
  	criticisms:	criticismsObj,
  	prizes:	prizesObj,
  	prizes:	prizesObj,
  	criticisms:	criticismsObj,
  
},{collection:'movie'});

movieSchema.path('prizes').required()
movieSchema.path('genre').required()
movieSchema.path('criticisms').required()

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

criticismsObj=	{
		color:	String,
		journalist:	String,
		media:	String,
		url:	String,
} 
prizesObj=	{
		event:	String,
		name:	String,
		year:	Number,
} 
  
var movieSchema = new mongoose.Schema({
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  	genres:	[],
  	writers:	[],
  	genre:	String,
  	criticisms:	criticismsObj,
  	prizes:	prizesObj,
  	prizes:	prizesObj,
  	criticisms:	criticismsObj,
  
},{collection:'movie'});

movieSchema.path('genre').required()

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

criticismsObj=	{
		color:	String,
		journalist:	String,
		media:	String,
		url:	String,
} 
prizesObj=	{
		event:	String,
		name:	String,
		year:	Number,
} 
  
var movieSchema = new mongoose.Schema({
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  	genres:	[],
  	writers:	[],
  	genre:	String,
  	criticisms:	criticismsObj,
  	prizes:	prizesObj,
  	prizes:	prizesObj,
  	criticisms:	criticismsObj,
  
},{collection:'movie'});

movieSchema.path('prizes').required()
movieSchema.path('genre').required()

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

criticismsObj=	{
		color:	String,
		journalist:	String,
		media:	String,
		url:	String,
} 
prizesObj=	{
		event:	String,
		name:	String,
		year:	Number,
} 
  
var movieSchema = new mongoose.Schema({
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  	genres:	[],
  	writers:	[],
  	genre:	String,
  	criticisms:	criticismsObj,
  	prizes:	prizesObj,
  	prizes:	prizesObj,
  	criticisms:	criticismsObj,
  
},{collection:'movie'});

movieSchema.path('genre').required()
movieSchema.path('criticisms').required()

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

criticismsObj=	{
		color:	String,
		journalist:	String,
		media:	String,
		url:	String,
} 
prizesObj=	{
		event:	String,
		name:	String,
		year:	Number,
} 
  
var movieSchema = new mongoose.Schema({
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true},
  	genres:	[],
  	writers:	[],
  	genre:	String,
  	criticisms:	criticismsObj,
  	prizes:	prizesObj,
  	prizes:	prizesObj,
  	criticisms:	criticismsObj,
  
},{collection:'movie'});

movieSchema.path('genres').required()
movieSchema.path('writers').required()

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
  	_id:	{type:String, required:true},
  	name:	{type:String, required:true},
  	type:	{type:String, required:true},
  	directed_movies:	[],
  	actor_movies:	[],
  
},{collection:'director'});

directorSchema.path('actor_movies').required()
directorSchema.path('directed_movies').required()

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
  	_id:	{type:String, required:true},
  	name:	{type:String, required:true},
  	type:	{type:String, required:true},
  	directed_movies:	[],
  	actor_movies:	[],
  
},{collection:'director'});

directorSchema.path('directed_movies').required()

var Director = mongoose.model('Director',directorSchema);



