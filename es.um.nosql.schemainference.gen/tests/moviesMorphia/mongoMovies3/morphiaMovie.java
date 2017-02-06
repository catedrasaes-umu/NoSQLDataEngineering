
//Root Entity
//File Movie1.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movie)
class Movie{

// Common Properties	
  	@, maxlength: 40Indexed (unique=true)
  	private String title;
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (IndexDirection.
  	private int year;
  	private String type;
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie1 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;
	criticisms:	{type:Moviecriticisms1, required:true},
	prizes:	{type:Movieprizes1, required:true},

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,

public Movie{
}

//Root Entity Code
class Movie{

// Common Properties	
  public String getTitle() {
    return title;
  }
  
  public void setTitle(final String title) {
   this.title = title;
  }
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public int getYear() {
    return year;
  }
  
  public void setYear(final int year) {
   this.year = year;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie1 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;
	criticisms:	{type:Moviecriticisms1, required:true},
	prizes:	{type:Movieprizes1, required:true},

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes3,
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 
 
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


//Root Entity
//File Movie2.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movie)
class Movie{

// Common Properties	
  	@, maxlength: 40Indexed (unique=true)
  	private String title;
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (IndexDirection.
  	private int year;
  	private String type;
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie2 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,

public Movie{
}

//Root Entity Code
class Movie{

// Common Properties	
  public String getTitle() {
    return title;
  }
  
  public void setTitle(final String title) {
   this.title = title;
  }
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public int getYear() {
    return year;
  }
  
  public void setYear(final int year) {
   this.year = year;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie2 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 
 
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


//Root Entity
//File Movie3.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movie)
class Movie{

// Common Properties	
  	@, maxlength: 40Indexed (unique=true)
  	private String title;
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (IndexDirection.
  	private int year;
  	private String type;
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie3 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;
	prizes:	{type:Movieprizes3, required:true},

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,

public Movie{
}

//Root Entity Code
class Movie{

// Common Properties	
  public String getTitle() {
    return title;
  }
  
  public void setTitle(final String title) {
   this.title = title;
  }
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public int getYear() {
    return year;
  }
  
  public void setYear(final int year) {
   this.year = year;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie3 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;
	prizes:	{type:Movieprizes3, required:true},

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 
 
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


//Root Entity
//File Movie4.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movie)
class Movie{

// Common Properties	
  	@, maxlength: 40Indexed (unique=true)
  	private String title;
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (IndexDirection.
  	private int year;
  	private String type;
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie4 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;
	criticisms:	{type:Moviecriticisms4, required:true},

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms1,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,

public Movie{
}

//Root Entity Code
class Movie{

// Common Properties	
  public String getTitle() {
    return title;
  }
  
  public void setTitle(final String title) {
   this.title = title;
  }
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public int getYear() {
    return year;
  }
  
  public void setYear(final int year) {
   this.year = year;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie4 entity version
  	@, enum: [drama, comedy, children]
  	private String genre;
	criticisms:	{type:Moviecriticisms4, required:true},

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	criticisms:	Moviecriticisms1,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 
 
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


//Root Entity
//File Movie5.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movie)
class Movie{

// Common Properties	
  	@, maxlength: 40Indexed (unique=true)
  	private String title;
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (IndexDirection.
  	private int year;
  	private String type;
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie5 entity version
  	private String[] genres;
  	private String[] writers;

// Not Common Properties 
	private String genre;
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,

public Movie{
}

//Root Entity Code
class Movie{

// Common Properties	
  public String getTitle() {
    return title;
  }
  
  public void setTitle(final String title) {
   this.title = title;
  }
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public int getYear() {
    return year;
  }
  
  public void setYear(final int year) {
   this.year = year;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
	director_id:	{type: String, required: true, ref: Director},
  
// add required for Movie5 entity version
  	private String[] genres;
  	private String[] writers;

// Not Common Properties 
	private String genre;
	criticisms:	Moviecriticisms1,
	criticisms:	Moviecriticisms4,
	prizes:	Movieprizes1,
	prizes:	Movieprizes3,
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 
 
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



//Root Entity
//File Movietheater1.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movietheater)
class Movietheater{

// Common Properties	
  	private String _id;
  	private String name;
  	private String type;
  	private String city;
  	private String country;
  
// add required for Movietheater1 entity version

// Not Common Properties 
	private int roomNumbers;

public Movietheater{
}

//Root Entity Code
class Movietheater{

// Common Properties	
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public String getName() {
    return name;
  }
  
  public void setName(final String name) {
   this.name = name;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
  public String getCity() {
    return city;
  }
  
  public void setCity(final String city) {
   this.city = city;
  }
  public String getCountry() {
    return country;
  }
  
  public void setCountry(final String country) {
   this.country = country;
  }
  
// add required for Movietheater1 entity version

// Not Common Properties 
	private int roomNumbers;
}
  


  

// Update



//Root Entity
//File Movietheater2.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(movietheater)
class Movietheater{

// Common Properties	
  	private String _id;
  	private String name;
  	private String type;
  	private String city;
  	private String country;
  
// add required for Movietheater2 entity version
  	private int roomNumbers;

// Not Common Properties 

public Movietheater{
}

//Root Entity Code
class Movietheater{

// Common Properties	
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public String getName() {
    return name;
  }
  
  public void setName(final String name) {
   this.name = name;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
  public String getCity() {
    return city;
  }
  
  public void setCity(final String city) {
   this.city = city;
  }
  public String getCountry() {
    return country;
  }
  
  public void setCountry(final String country) {
   this.country = country;
  }
  
// add required for Movietheater2 entity version
  	private int roomNumbers;

// Not Common Properties 
}
  


  

// Update




//Root Entity
//File Director1.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(director)
class Director{

// Common Properties	
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (unique=true)
  	private String name;
  	private String type;
  
// add required for Director1 entity version
  	private String[] actor_movies;
  	private String[] directed_movies;

// Not Common Properties 

public Director{
}

//Root Entity Code
class Director{

// Common Properties	
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public String getName() {
    return name;
  }
  
  public void setName(final String name) {
   this.name = name;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
  
// add required for Director1 entity version
  	private String[] actor_movies;
  	private String[] directed_movies;

// Not Common Properties 
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@77846d2c (fieldName: name)] 
 
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


//Root Entity
//File Director2.java
package mongoMovies3.morphiaMapper;
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
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Entity(director)
class Director{

// Common Properties	
  	@Indexed (IndexDirection.
  	private String _id;
  	@Indexed (unique=true)
  	private String name;
  	private String type;
  
// add required for Director2 entity version
  	private String[] directed_movies;

// Not Common Properties 
	private String[] actor_movies;

public Director{
}

//Root Entity Code
class Director{

// Common Properties	
  public String get_id() {
    return _id;
  }
  
  public void set_id(final String _id) {
   this._id = _id;
  }
  public String getName() {
    return name;
  }
  
  public void setName(final String name) {
   this.name = name;
  }
  public String getType() {
    return type;
  }
  
  public void setType(final String type) {
   this.type = type;
  }
  
// add required for Director2 entity version
  	private String[] directed_movies;

// Not Common Properties 
	private String[] actor_movies;
}
  


  

// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@77846d2c (fieldName: name)] 
 
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



//Embedded Entity
//File Media
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;
@Embedded
public class Media {

// Common Properties	
  	private String name;
  	private String url;
  
// add required for Media1 entity version

// Not Common Properties 
},{collection:'media'});

var Media = mongoose.model('Media',mediaSchema);


  

// Update




//Embedded Entity
//File Criticism
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;
@Embedded
public class Criticism {

// Common Properties	
  	@, enum: [green, red, yellow]
  	private String color;
  	@Indexed (unique=true)
  	private String journalist;
    
// add required for Criticism1 entity version
	media:	{type:Criticismmedia1, required:true},

// Not Common Properties 
	private String media;
},{collection:'criticism'});

var Criticism = mongoose.model('Criticism',criticismSchema);


  

// Update

[] 
 
  function criticism_Updating(query , fieldsToUpdate) {
  Criticism.findOne (
  query ,
  function (err , criticism) {
  if (! err ) {
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


//Embedded Entity
//File Criticism
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;
@Embedded
public class Criticism {

// Common Properties	
  	@, enum: [green, red, yellow]
  	private String color;
  	@Indexed (unique=true)
  	private String journalist;
    
// add required for Criticism2 entity version
  	private String media;

// Not Common Properties 
	media:	Criticismmedia1,
},{collection:'criticism'});

var Criticism = mongoose.model('Criticism',criticismSchema);


  

// Update

[] 
 
  function criticism_Updating(query , fieldsToUpdate) {
  Criticism.findOne (
  query ,
  function (err , criticism) {
  if (! err ) {
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



//Embedded Entity
//File Prize
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;
@Embedded
public class Prize {

// Common Properties	
  	private String event;
  	private int year;
  
// add required for Prize1 entity version
  	private String[] names;

// Not Common Properties 
	private String name;
},{collection:'prize'});

var Prize = mongoose.model('Prize',prizeSchema);


  

// Update



//Embedded Entity
//File Prize
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;
@Embedded
public class Prize {

// Common Properties	
  	private String event;
  	private int year;
  
// add required for Prize2 entity version
  	private String name;

// Not Common Properties 
	private String[] names;
},{collection:'prize'});

var Prize = mongoose.model('Prize',prizeSchema);


  

// Update




