var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
         if(error){
	        throw error;
	     }
	     //else{
	        //console.log('Conectado a MongoDB');
	     //}
});

var movieSchema = new mongoose.Schema({	
	  	 
	_id: String,
	      criticisms: {
	  	  
	      	color: String,
	      	journalist: String,
	      	media: String,
	      	url: String,

	      	color: String,
	      	journalist: String,
	      	media: String,
},
	director_id : String,
	genre: String,
	      prizes: {
	  	  
	      	event: String,
	      	names: [],
	      	year: Number,
},
	title: String,
	type: String,
	year: Number,
 
 
	      prizes: {
	  	  
	      	event: String,
	      	names: [],
	      	year: Number,
},
 
	      criticisms: {
	  	  
	      	color: String,
	      	journalist: String,
	      	media: String,
	      	url: String,

	      	color: String,
	      	journalist: String,
	      	media: String,
},
 
	genres: [],
	writers: [],
},
{collection: 'movie'});
var Movie = mongoose.model('Movie',movieSchema);

Movie.find(function(err,movie){
	if (err) return console.error(err);
	var stringVar="\nMovie:";
	console.log(stringVar);
	console.log(movie);
});

var directorSchema = new mongoose.Schema({	
	  	 
	_id: String,
	actor_movies: [],
	directed_movies: [],
	name: String,
	type: String,
 
},
{collection: 'director'});
var Director = mongoose.model('Director',directorSchema);

Director.find(function(err,director){
	if (err) return console.error(err);
	var stringVar="\nDirector:";
	console.log(stringVar);
	console.log(director);
	mongoose.disconnect();
});
