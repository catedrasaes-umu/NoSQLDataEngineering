var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
         if(error){
	        throw error;
	     }
	     //else{
	        //console.log('Conectado a MongoDB');
	     //}
});

var bookSchema = new mongoose.Schema({	
	  	 
	_id: String,
	      authors: {
	  	  
	      	_id: String,
	      	      company: {
	      	  	  
	      	      	country: String,
	      	      	name: String,
	      },
	      	name: String,

	      	_id: String,
	      	company: String,
	      	country: String,
	      	name: String,
},
	      content: {
	  	  
	      	chapters: Number,
	      	pages: Number,
},
	publisher_id : String,
	title: String,
	type: String,
	year: Number,
 
	      author: {
	  	  
	      	_id: String,
	      	      company: {
	      	  	  
	      	      	country: String,
	      	      	name: String,
	      },
	      	name: String,
},
},
{collection: 'book'});
var Book = mongoose.model('Book',bookSchema);

Book.find(function(err,book){
	if (err) return console.error(err);
	var stringVar="\nBook:";
	console.log(stringVar);
	console.log(book);
});

var journalSchema = new mongoose.Schema({	
	  	 
	_id: String,
	discipline: String,
	issn: [],
	name: String,
	type: String,
 
	number: Number,
},
{collection: 'journal'});
var Journal = mongoose.model('Journal',journalSchema);

Journal.find(function(err,journal){
	if (err) return console.error(err);
	var stringVar="\nJournal:";
	console.log(stringVar);
	console.log(journal);
});

var publisherSchema = new mongoose.Schema({	
	  	 
	_id: String,
	city: String,
	name: String,
	type: String,
 
 
	journals : {},
},
{collection: 'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);

Publisher.find(function(err,publisher){
	if (err) return console.error(err);
	var stringVar="\nPublisher:";
	console.log(stringVar);
	console.log(publisher);
	mongoose.disconnect();
});
