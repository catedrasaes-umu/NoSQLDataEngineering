//Movie Ver 1 Schema
var mongoose = require('mongoose');
var urlDatabase='mongodb://localhost/dbmongoose'
mongoose.connect(urlDatabase, function(error){
	if(error){
  	    throw error;
  }
  else{
  	console.log('Conectado a MongoDB');
  }
});

var criticismsObj=	{
		color:	{type: String, required: true},
		journalist:	{type: String, required: true},
		media:	{type: String, required: true},
		url:	String,
} 

var prizesObj=	{
		event:	{type: String, required: true},
		names:	{type: [String], required: true},
		year:	{type: Number, required: true},
} 
  
var movieSchema = new mongoose.Schema({
  	title:	{type:String, required:true},
  	_id:	{type:String, required:true},
  	year:	{type:Number, required:true},
  	type:	{type:String, required:true},
  	director_id:	{type: String, required: true, ref:'Director'},
  	genres:	[String],
  	writers:	[String],
  	genre:	{type:String, required:true},
  	criticisms:	{type:criticismsObj, required:true},
  	prizes:	{type:prizesObj, required:true},
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

//Director Ver 1 Schema

var directorSchema = new mongoose.Schema({
  	_id:	{type:String, required:true},
  	name:	{type:String, required:true},
  	type:	{type:String, required:true},
  	actor_movies:	{[type:[String], required:true], ref:'Movie']},
  	directed_movies:	{[type:[String], required:true, , ref:'Movie']},
  
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);





