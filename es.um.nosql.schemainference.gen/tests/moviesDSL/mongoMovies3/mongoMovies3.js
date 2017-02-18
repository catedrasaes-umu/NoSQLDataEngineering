
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
var Movierating4=	{
	score:	Number,
	voters:	Number,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	names:	[],
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
  
// add required for Movie1 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
    director_id:	{type: String, required: true, ref: Director},
	criticisms:	{type:Moviecriticisms1, required:true},
	prizes:	{type:Movieprizes1, required:true},

// Not Common Properties 
	running_time:	Number,
	genres:	[],
	writers:	[],
  	director_id:	{type:String, ref: Director},
	rating:	Movierating4,
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
var Movierating4=	{
	score:	Number,
	voters:	Number,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	names:	[],
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
  
// add required for Movie2 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
  	running_time:	{type: Number, required: true},
  director_id:	{type: String, required: true, ref: Director},

// Not Common Properties 
	genres:	[],
	writers:	[],
  	director_id:	{type:String, ref: Director},
	rating:	Movierating4,
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
var Movierating4=	{
	score:	Number,
	voters:	Number,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	names:	[],
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
  
// add required for Movie3 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
    director_id:	{type: String, required: true, ref: Director},
	prizes:	{type:Movieprizes3, required:true},

// Not Common Properties 
	running_time:	Number,
	genres:	[],
	writers:	[],
  	director_id:	{type:String, ref: Director},
	rating:	Movierating4,
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
var Movierating4=	{
	score:	Number,
	voters:	Number,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	names:	[],
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
  
// add required for Movie4 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
    director_id:	{type: String, required: true, ref: Director},
	rating:	{type:Movierating4, required:true},
	criticisms:	{type:Moviecriticisms4, required:true},

// Not Common Properties 
	running_time:	Number,
	genres:	[],
	writers:	[],
  	director_id:	{type:String, ref: Director},
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
var Movierating4=	{
	score:	Number,
	voters:	Number,
    } 
var Moviecriticisms4=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
    } 
var Movieprizes1=	{
	event:	String,
	year:	Number,
	name:	String,
	names:	[],
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
  
// add required for Movie5 entity version
  	genres:	{type:[], required:true},
  	writers:	{type:[], required:true},
  director_id:	{type: String, required: true, ref: Director},

// Not Common Properties 
	genre:	String,
	running_time:	Number,
  	director_id:	{type:String, ref: Director},
	rating:	Movierating4,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);



//File Movietheater1
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
var movietheaterSchema = new mongoose.Schema({

// Common Properties	
  	_id:	{type: String, required: true},
  	name:	{type: String, required: true},
  	type:	{type: String, required: true},
  	city:	{type: String, required: true},
  	country:	{type: String, required: true},
  
// add required for Movietheater1 entity version

// Not Common Properties 
	roomNumbers:	Number,
},{collection:'movietheater'});

var Movietheater = mongoose.model('Movietheater',movietheaterSchema);


//File Movietheater2
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
var movietheaterSchema = new mongoose.Schema({

// Common Properties	
  	_id:	{type: String, required: true},
  	name:	{type: String, required: true},
  	type:	{type: String, required: true},
  	city:	{type: String, required: true},
  	country:	{type: String, required: true},
  
// add required for Movietheater2 entity version
  	roomNumbers:	{type: Number, required: true},

// Not Common Properties 
},{collection:'movietheater'});

var Movietheater = mongoose.model('Movietheater',movietheaterSchema);



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
  actor_movies:	{type: {}, required: true, ref: Movie},
  directed_movies:	{type: {}, required: true, ref: Movie},

// Not Common Properties 
  	directed_movies:	{type:String, ref: Movie},
  	actor_movies:	{type:{}, ref: Movie},
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
  directed_movies:	{type: String, required: true, ref: Movie},

// Not Common Properties 
  	directed_movies:	{type:String, ref: Movie},
  	actor_movies:	{type:{}, ref: Movie},
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);



