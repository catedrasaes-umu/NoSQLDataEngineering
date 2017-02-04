
//File Movie1
package movie.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
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
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie1 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
	criticisms:	{type:Moviecriticisms1, required:true},
	prizes:	{type:Movieprizes1, required:true},

// Not Common Properties 
	genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@481a15ff (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@78186a70 (fieldName: title)] 
 
  function movie_Updating(query , fieldsToUpdate) {
  Movie.findOne (
  query ,
  function (err , movie) {
  if (! err ) {
  movie.genre = aGenre ;
  moviemovie . save (function (err , user ) {  movie.title = aGenre ;
  moviemovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});


//File Movie2
package movie.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
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
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie2 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},

// Not Common Properties 
	genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@481a15ff (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@78186a70 (fieldName: title)] 
 
  function movie_Updating(query , fieldsToUpdate) {
  Movie.findOne (
  query ,
  function (err , movie) {
  if (! err ) {
  movie.genre = aGenre ;
  moviemovie . save (function (err , user ) {  movie.title = aGenre ;
  moviemovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});


//File Movie3
package movie.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
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
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie3 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
	prizes:	{type:Movieprizes3, required:true},

// Not Common Properties 
	genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@481a15ff (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@78186a70 (fieldName: title)] 
 
  function movie_Updating(query , fieldsToUpdate) {
  Movie.findOne (
  query ,
  function (err , movie) {
  if (! err ) {
  movie.genre = aGenre ;
  moviemovie . save (function (err , user ) {  movie.title = aGenre ;
  moviemovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});


//File Movie4
package movie.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
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
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie4 entity version
  	genre:	{type: String, required:true, enum: [drama, comedy, children]},
	criticisms:	{type:Moviecriticisms4, required:true},

// Not Common Properties 
	genres:	[],
	writers:	[],
	criticisms:	Moviecriticisms1,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@481a15ff (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@78186a70 (fieldName: title)] 
 
  function movie_Updating(query , fieldsToUpdate) {
  Movie.findOne (
  query ,
  function (err , movie) {
  if (! err ) {
  movie.genre = aGenre ;
  moviemovie . save (function (err , user ) {  movie.title = aGenre ;
  moviemovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});


//File Movie5
package movie.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var Moviecriticisms1=	{
	color:	{type: String, enum: [green, red, yellow]},
	journalist:	{type: String, unique: true},
	media:	String,
	media:	{
		name:	String,
		url:	String,
	    } 
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
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie5 entity version
  	genres:	{type:[], required:true},
  	writers:	{type:[], required:true},

// Not Common Properties 
	genre:	String,
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
},{collection:'movie'});

var Movie = mongoose.model('Movie',movieSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@481a15ff (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@78186a70 (fieldName: title)] 
 
  function movie_Updating(query , fieldsToUpdate) {
  Movie.findOne (
  query ,
  function (err , movie) {
  if (! err ) {
  movie.genre = aGenre ;
  moviemovie . save (function (err , user ) {  movie.title = aGenre ;
  moviemovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});



//File Movietheater1
package movietheater.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
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

// Update



//File Movietheater2
package movietheater.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
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

// Update




//File Director1
package director.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var directorSchema = new mongoose.Schema({

// Common Properties	
  	_id:	{type: String, required:true, index: Hashed},
  	name:	{type: String, required:true, unique: true},
  	type:	{type: String, required: true},
  
// add required for Director1 entity version
  	actor_movies:	{type:[], required:true},
  	directed_movies:	{type:[], required:true},

// Not Common Properties 
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@306279ee (fieldName: name)] 
 
  function director_Updating(query , fieldsToUpdate) {
  Director.findOne (
  query ,
  function (err , director) {
  if (! err ) {
  director.name = aGenre ;
  directormovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});


//File Director2
package director.morphia;
import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.IndexOptions; 
import org.mongodb.morphia.utils.IndexDirection;
import org.mongodb.morphia.utils.IndexType;
import static org.mongodb.morphia.utils.IndexType.TEXT;
import static org.mongodb.morphia.utils.IndexType.HASHED;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
var directorSchema = new mongoose.Schema({

// Common Properties	
  	_id:	{type: String, required:true, index: Hashed},
  	name:	{type: String, required:true, unique: true},
  	type:	{type: String, required: true},
  
// add required for Director2 entity version
  	directed_movies:	{type:[], required:true},

// Not Common Properties 
	actor_movies:	[],
},{collection:'director'});

var Director = mongoose.model('Director',directorSchema);

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@306279ee (fieldName: name)] 
 
  function director_Updating(query , fieldsToUpdate) {
  Director.findOne (
  query ,
  function (err , director) {
  if (! err ) {
  director.name = aGenre ;
  directormovie . save (function (err , user ) {
  console . log ( ’ Movie saved : ’, movie );
  }) ;
  }
  }
  );
  }  
Book.findOneAndUpdate({_id:bookId},{$set:{"name": name},$set:{"genre": genre},$set:{"author": author},$set:{"similar": similar}}).exec(function(err, book){
       if(err){
           console.log(err);
           res.status(500).send(err);
       } else {
            res.status(200).send(book);
       }
});



