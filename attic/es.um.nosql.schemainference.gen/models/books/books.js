
//File Book1
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var bookSchema = new mongoose.Schema({    	
	_id:	String,
	title:	String,
	type:	String,
	year:	Number,
	authors:	{
		_id:	String,
		name:	String,
		company:	String,
		country:	String,
		company:	{
			country:	String,
			name:	String,
		} 
	} 
	content:	{
		chapters:	Number,
		pages:	Number,
	} 
	author:	{
		_id:	String,
		name:	String,
		company:	{
			country:	String,
			name:	String,
		} 
	} 
	publisher_id:	String,
},

{collection:'book'});
var Book = mongoose.model('Book',bookSchema);
No tiene  1 props	####
Book.find(
	{author: {$exists: false}},
).exec(function(err,book){
		  if (err) return console.log(err);	
		console.log(book);
      });

var publisherSchema = new mongoose.Schema({
	_id:	String,
	city:	String,
	name:	String,
	type:	String,
	journals:	{},
},
{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);
        
Publisher.find(function(err,publisher){
	if (err) return console.error(err);
	var stringVar="\nPublisher:";
	console.log(stringVar);
	console.log(publisher);
});

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

//File Book2
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var bookSchema = new mongoose.Schema({    	
	_id:	String,
	title:	String,
	type:	String,
	year:	Number,
	authors:	{
		_id:	String,
		name:	String,
		company:	String,
		country:	String,
		company:	{
			country:	String,
			name:	String,
		} 
	} 
	content:	{
		chapters:	Number,
		pages:	Number,
	} 
	author:	{
		_id:	String,
		name:	String,
		company:	{
			country:	String,
			name:	String,
		} 
	} 
	publisher_id:	String,
},

{collection:'book'});
var Book = mongoose.model('Book',bookSchema);
No tiene  3 props	####
Book.find(
	{year: {$exists: false}},
	{content: {$exists: false}},
	{authors: {$exists: false}},
).exec(function(err,book){
		  if (err) return console.log(err);	
		console.log(book);
      });

var publisherSchema = new mongoose.Schema({
	_id:	String,
	city:	String,
	name:	String,
	type:	String,
	journals:	{},
},
{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);
        
Publisher.find(function(err,publisher){
	if (err) return console.error(err);
	var stringVar="\nPublisher:";
	console.log(stringVar);
	console.log(publisher);
});

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

//File Journal1
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

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
No tiene  1 props	####
Journal.find(
	{number: {$exists: false}},
).exec(function(err,journal){
		  if (err) return console.log(err);	
		console.log(journal);
      });

//File Journal2
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

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
No tiene  0 props	####
Journal.find(
).exec(function(err,journal){
		  if (err) return console.log(err);	
		console.log(journal);
      });

//File Publisher1
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({    	
	_id:	String,
	city:	String,
	name:	String,
	type:	String,
	journals:	{},
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);
No tiene  1 props	####
Publisher.find(
	{journals: {$exists: false}},
).exec(function(err,publisher){
		  if (err) return console.log(err);	
		console.log(publisher);
      });

//File Publisher2
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({    	
	_id:	String,
	city:	String,
	name:	String,
	type:	String,
	journals:	{},
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);
No tiene  2 props	####
Publisher.find(
	{city: {$exists: false}},
	{journals: {$exists: false}},
).exec(function(err,publisher){
		  if (err) return console.log(err);	
		console.log(publisher);
      });

//File Publisher3
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/dbmongoose', function(error){
	if(error){
	    throw error;
    }
	else{
		console.log('Conectado a MongoDB');
    }
});

var publisherSchema = new mongoose.Schema({    	
	_id:	String,
	city:	String,
	name:	String,
	type:	String,
	journals:	{},
},

{collection:'publisher'});
var Publisher = mongoose.model('Publisher',publisherSchema);
No tiene  1 props	####
Publisher.find(
	{city: {$exists: false}},
).exec(function(err,publisher){
		  if (err) return console.log(err);	
		console.log(publisher);
      });

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

