
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
//Annotations class
@Entity(movie)
class Movie{

//Common Properties	
	@, maxlength: 40Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (IndexDirection.
	private int year;
	private String type;

	@Reference
	private Director director;

//add required for Movie1 entity version
	@, enum: [drama, comedy, children]
	private String genre;
	@Embedded
	private ArrayList<Criticism1> criticisms1;
	@Embedded
	private ArrayList<Prize1> prizes1;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize3> prizes3;

public Movie{
}

//Root Entity Code
//Common Properties

	public String getTitle(){
	  return title;
	}
 
  	public void setTitle(String title){
      this.title = title;
  	}

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public List<Director> getDirector(){
	  return director;
	}

	public void setDirector(Director director){
      this.director = director;
  	}

// add required for Movie1 entity version

	public String getGenre(){
	  return genre;
	}
 
  	public void setGenre(String genre){
      this.genre = genre;
  	}

	public List<Criticism1> getCriticisms1(){
      return criticisms1;
	  }

	public void setCriticisms1(List<Criticism1> criticisms1){
      this.criticisms1 = criticisms1;
	}

	public List<Prize1> getPrizes1(){
      return prizes1;
	  }

	public void setPrizes1(List<Prize1> prizes1){
      this.prizes1 = prizes1;
	}

// Not Common Properties 

	public String[] getGenres(){
      return genres;
	}
  
  	public void setGenres(String[] genres){
      this.genres = genres;
  	}

	public String[] getWriters(){
      return writers;
	}
  
  	public void setWriters(String[] writers){
      this.writers = writers;
  	}

	public List<Criticism4> getCriticisms4(){
	  return criticisms4;
	}

	public void setCriticisms4(List<Criticism4> criticisms4){
      this.criticisms4 = criticisms4;
	}

	public List<Prize3> getPrizes3(){
	  return prizes3;
	}

	public void setPrizes3(List<Prize3> prizes3){
      this.prizes3 = prizes3;
	}
}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 

function movie_Updating(query , fieldsToUpdate) {
Movie.findOne (
query ,
function (err , movie) {
if (! err ) {
movie.genre = aGenre ;
moviemovie . save (function (err , user ) {movie.title = aGenre ;
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
//Annotations class
@Entity(movie)
class Movie{

//Common Properties	
	@, maxlength: 40Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (IndexDirection.
	private int year;
	private String type;

	@Reference
	private Director director;

//add required for Movie2 entity version
	@, enum: [drama, comedy, children]
	private String genre;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	@Embedded
	private List<Criticism1> criticisms1;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize1> prizes1;
	@Embedded
	private List<Prize3> prizes3;

public Movie{
}

//Root Entity Code
//Common Properties

	public String getTitle(){
	  return title;
	}
 
  	public void setTitle(String title){
      this.title = title;
  	}

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public List<Director> getDirector(){
	  return director;
	}

	public void setDirector(Director director){
      this.director = director;
  	}

// add required for Movie2 entity version

	public String getGenre(){
	  return genre;
	}
 
  	public void setGenre(String genre){
      this.genre = genre;
  	}

// Not Common Properties 

	public String[] getGenres(){
      return genres;
	}
  
  	public void setGenres(String[] genres){
      this.genres = genres;
  	}

	public String[] getWriters(){
      return writers;
	}
  
  	public void setWriters(String[] writers){
      this.writers = writers;
  	}

	public List<Criticism1> getCriticisms1(){
	  return criticisms1;
	}

	public void setCriticisms1(List<Criticism1> criticisms1){
      this.criticisms1 = criticisms1;
	}

	public List<Criticism4> getCriticisms4(){
	  return criticisms4;
	}

	public void setCriticisms4(List<Criticism4> criticisms4){
      this.criticisms4 = criticisms4;
	}

	public List<Prize1> getPrizes1(){
	  return prizes1;
	}

	public void setPrizes1(List<Prize1> prizes1){
      this.prizes1 = prizes1;
	}

	public List<Prize3> getPrizes3(){
	  return prizes3;
	}

	public void setPrizes3(List<Prize3> prizes3){
      this.prizes3 = prizes3;
	}
}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 

function movie_Updating(query , fieldsToUpdate) {
Movie.findOne (
query ,
function (err , movie) {
if (! err ) {
movie.genre = aGenre ;
moviemovie . save (function (err , user ) {movie.title = aGenre ;
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
//Annotations class
@Entity(movie)
class Movie{

//Common Properties	
	@, maxlength: 40Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (IndexDirection.
	private int year;
	private String type;

	@Reference
	private Director director;

//add required for Movie3 entity version
	@, enum: [drama, comedy, children]
	private String genre;
	@Embedded
	private ArrayList<Prize3> prizes3;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	@Embedded
	private List<Criticism1> criticisms1;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize1> prizes1;

public Movie{
}

//Root Entity Code
//Common Properties

	public String getTitle(){
	  return title;
	}
 
  	public void setTitle(String title){
      this.title = title;
  	}

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public List<Director> getDirector(){
	  return director;
	}

	public void setDirector(Director director){
      this.director = director;
  	}

// add required for Movie3 entity version

	public String getGenre(){
	  return genre;
	}
 
  	public void setGenre(String genre){
      this.genre = genre;
  	}

	public List<Prize3> getPrizes3(){
      return prizes3;
	  }

	public void setPrizes3(List<Prize3> prizes3){
      this.prizes3 = prizes3;
	}

// Not Common Properties 

	public String[] getGenres(){
      return genres;
	}
  
  	public void setGenres(String[] genres){
      this.genres = genres;
  	}

	public String[] getWriters(){
      return writers;
	}
  
  	public void setWriters(String[] writers){
      this.writers = writers;
  	}

	public List<Criticism1> getCriticisms1(){
	  return criticisms1;
	}

	public void setCriticisms1(List<Criticism1> criticisms1){
      this.criticisms1 = criticisms1;
	}

	public List<Criticism4> getCriticisms4(){
	  return criticisms4;
	}

	public void setCriticisms4(List<Criticism4> criticisms4){
      this.criticisms4 = criticisms4;
	}

	public List<Prize1> getPrizes1(){
	  return prizes1;
	}

	public void setPrizes1(List<Prize1> prizes1){
      this.prizes1 = prizes1;
	}
}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 

function movie_Updating(query , fieldsToUpdate) {
Movie.findOne (
query ,
function (err , movie) {
if (! err ) {
movie.genre = aGenre ;
moviemovie . save (function (err , user ) {movie.title = aGenre ;
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
//Annotations class
@Entity(movie)
class Movie{

//Common Properties	
	@, maxlength: 40Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (IndexDirection.
	private int year;
	private String type;

	@Reference
	private Director director;

//add required for Movie4 entity version
	@, enum: [drama, comedy, children]
	private String genre;
	@Embedded
	private ArrayList<Criticism4> criticisms4;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	@Embedded
	private List<Criticism1> criticisms1;
	@Embedded
	private List<Prize1> prizes1;
	@Embedded
	private List<Prize3> prizes3;

public Movie{
}

//Root Entity Code
//Common Properties

	public String getTitle(){
	  return title;
	}
 
  	public void setTitle(String title){
      this.title = title;
  	}

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public List<Director> getDirector(){
	  return director;
	}

	public void setDirector(Director director){
      this.director = director;
  	}

// add required for Movie4 entity version

	public String getGenre(){
	  return genre;
	}
 
  	public void setGenre(String genre){
      this.genre = genre;
  	}

	public List<Criticism4> getCriticisms4(){
      return criticisms4;
	  }

	public void setCriticisms4(List<Criticism4> criticisms4){
      this.criticisms4 = criticisms4;
	}

// Not Common Properties 

	public String[] getGenres(){
      return genres;
	}
  
  	public void setGenres(String[] genres){
      this.genres = genres;
  	}

	public String[] getWriters(){
      return writers;
	}
  
  	public void setWriters(String[] writers){
      this.writers = writers;
  	}

	public List<Criticism1> getCriticisms1(){
	  return criticisms1;
	}

	public void setCriticisms1(List<Criticism1> criticisms1){
      this.criticisms1 = criticisms1;
	}

	public List<Prize1> getPrizes1(){
	  return prizes1;
	}

	public void setPrizes1(List<Prize1> prizes1){
      this.prizes1 = prizes1;
	}

	public List<Prize3> getPrizes3(){
	  return prizes3;
	}

	public void setPrizes3(List<Prize3> prizes3){
      this.prizes3 = prizes3;
	}
}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 

function movie_Updating(query , fieldsToUpdate) {
Movie.findOne (
query ,
function (err , movie) {
if (! err ) {
movie.genre = aGenre ;
moviemovie . save (function (err , user ) {movie.title = aGenre ;
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
//Annotations class
@Entity(movie)
class Movie{

//Common Properties	
	@, maxlength: 40Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (IndexDirection.
	private int year;
	private String type;

	@Reference
	private Director director;

//add required for Movie5 entity version
	private String[] genres;
	private String[] writers;

// Not Common Properties 
	private String genre;
	@Embedded
	private List<Criticism1> criticisms1;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize1> prizes1;
	@Embedded
	private List<Prize3> prizes3;

public Movie{
}

//Root Entity Code
//Common Properties

	public String getTitle(){
	  return title;
	}
 
  	public void setTitle(String title){
      this.title = title;
  	}

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public List<Director> getDirector(){
	  return director;
	}

	public void setDirector(Director director){
      this.director = director;
  	}

// add required for Movie5 entity version

	public String[] getGenres(){
      return genres;
	}

	public void setGenres(String[] genres){
      this.genres = genres;
  	}

	public String[] getWriters(){
      return writers;
	}

	public void setWriters(String[] writers){
      this.writers = writers;
  	}

// Not Common Properties 

	public String getGenre(){
	  return genre;
	}

	public void setGenre(String genre){
      this.genre = genre;
	}

	public List<Criticism1> getCriticisms1(){
	  return criticisms1;
	}

	public void setCriticisms1(List<Criticism1> criticisms1){
      this.criticisms1 = criticisms1;
	}

	public List<Criticism4> getCriticisms4(){
	  return criticisms4;
	}

	public void setCriticisms4(List<Criticism4> criticisms4){
      this.criticisms4 = criticisms4;
	}

	public List<Prize1> getPrizes1(){
	  return prizes1;
	}

	public void setPrizes1(List<Prize1> prizes1){
      this.prizes1 = prizes1;
	}

	public List<Prize3> getPrizes3(){
	  return prizes3;
	}

	public void setPrizes3(List<Prize3> prizes3){
      this.prizes3 = prizes3;
	}
}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@545997b1 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@4cf4d528 (fieldName: title)] 

function movie_Updating(query , fieldsToUpdate) {
Movie.findOne (
query ,
function (err , movie) {
if (! err ) {
movie.genre = aGenre ;
moviemovie . save (function (err , user ) {movie.title = aGenre ;
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
//Annotations class
@Entity(movietheater)
class Movietheater{

//Common Properties	
	private String _id;
	private String name;
	private String type;
	private String city;
	private String country;

//add required for Movietheater1 entity version

// Not Common Properties 
	private int roomNumbers;

public Movietheater{
}

//Root Entity Code
//Common Properties

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public String getName(){
	  return name;
	}
 
  	public void setName(String name){
      this.name = name;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public String getCity(){
	  return city;
	}
 
  	public void setCity(String city){
      this.city = city;
  	}

	public String getCountry(){
	  return country;
	}
 
  	public void setCountry(String country){
      this.country = country;
  	}

// add required for Movietheater1 entity version

// Not Common Properties 

	public int getRoomNumbers(){
	  return roomNumbers;
	}

	public void setRoomNumbers(int roomNumbers){
      this.roomNumbers = roomNumbers;
	}
}//end Class


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
//Annotations class
@Entity(movietheater)
class Movietheater{

//Common Properties	
	private String _id;
	private String name;
	private String type;
	private String city;
	private String country;

//add required for Movietheater2 entity version
	private int roomNumbers;

// Not Common Properties 

public Movietheater{
}

//Root Entity Code
//Common Properties

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public String getName(){
	  return name;
	}
 
  	public void setName(String name){
      this.name = name;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

	public String getCity(){
	  return city;
	}
 
  	public void setCity(String city){
      this.city = city;
  	}

	public String getCountry(){
	  return country;
	}
 
  	public void setCountry(String country){
      this.country = country;
  	}

// add required for Movietheater2 entity version

	public int getRoomNumbers(){
	  return roomNumbers;
	}
 
  	public void setRoomNumbers(int roomNumbers){
      this.roomNumbers = roomNumbers;
  	}

// Not Common Properties 
}//end Class


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
//Annotations class
@Entity(director)
class Director{

//Common Properties	
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (unique=true)
	private String name;
	private String type;

//add required for Director1 entity version
	private String[] actor_movies;
	private String[] directed_movies;

// Not Common Properties 

public Director{
}

//Root Entity Code
//Common Properties

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public String getName(){
	  return name;
	}
 
  	public void setName(String name){
      this.name = name;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

// add required for Director1 entity version

	public String[] getActor_movies(){
      return actor_movies;
	}

	public void setActor_movies(String[] actor_movies){
      this.actor_movies = actor_movies;
  	}

	public String[] getDirected_movies(){
      return directed_movies;
	}

	public void setDirected_movies(String[] directed_movies){
      this.directed_movies = directed_movies;
  	}

// Not Common Properties 
}//end Class


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
//Annotations class
@Entity(director)
class Director{

//Common Properties	
	@Indexed (IndexDirection.
	private String _id;
	@Indexed (unique=true)
	private String name;
	private String type;

//add required for Director2 entity version
	private String[] directed_movies;

// Not Common Properties 
	private String[] actor_movies;

public Director{
}

//Root Entity Code
//Common Properties

	public String get_id(){
	  return _id;
	}
 
  	public void set_id(String _id){
      this._id = _id;
  	}

	public String getName(){
	  return name;
	}
 
  	public void setName(String name){
      this.name = name;
  	}

	public String getType(){
	  return type;
	}
 
  	public void setType(String type){
      this.type = type;
  	}

// add required for Director2 entity version

	public String[] getDirected_movies(){
      return directed_movies;
	}

	public void setDirected_movies(String[] directed_movies){
      this.directed_movies = directed_movies;
  	}

// Not Common Properties 

	public String[] getActor_movies(){
      return actor_movies;
	}
  
  	public void setActor_movies(String[] actor_movies){
      this.actor_movies = actor_movies;
  	}
}//end Class


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
//File Media1.java
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;

//Annotations Embedded Class
@Embedded
class Media1{

// Common Properties	
	private String name;
	private String url;

//add required for Media1 entity version

// Not Common Properties 

public Media1{
}

//Embedded Entity Code

//Common Properties	

	public String getName(){
	  return name;
	}
 
  	public void setName(String name){
      this.name = name;
  	}

	public String getUrl(){
	  return url;
	}
 
  	public void setUrl(String url){
      this.url = url;
  	}

// add required for Media1 entity version

// Not Common Properties 
}//end Class


// Update



//Embedded Entity
//File Criticism1.java
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;

//Annotations Embedded Class
@Embedded
class Criticism1{

// Common Properties	
	@, enum: [green, red, yellow]
	private String color;
	@Indexed (unique=true)
	private String journalist;

//add required for Criticism1 entity version
  	@Embedded

	private Media1 media1;

// Not Common Properties 
	private String media;

public Criticism1{
}

//Embedded Entity Code

//Common Properties	

	public String getColor(){
	  return color;
	}
 
  	public void setColor(String color){
      this.color = color;
  	}

	public String getJournalist(){
	  return journalist;
	}
 
  	public void setJournalist(String journalist){
      this.journalist = journalist;
  	}

// add required for Criticism1 entity version

	public Media1 getMedia1(){
	  return media1;
	}
  
	public void setMedia1(Media1 media1){
	  this.media1 = media1;
	}

// Not Common Properties 

	public String getMedia(){
	  return media;
	}

	public void setMedia(String media){
      this.media = media;
	}
}//end Class


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
//File Criticism2.java
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;

//Annotations Embedded Class
@Embedded
class Criticism2{

// Common Properties	
	@, enum: [green, red, yellow]
	private String color;
	@Indexed (unique=true)
	private String journalist;

//add required for Criticism2 entity version
	private String media;

// Not Common Properties 
	@Embedded
	private Media1 media1;

public Criticism2{
}

//Embedded Entity Code

//Common Properties	

	public String getColor(){
	  return color;
	}
 
  	public void setColor(String color){
      this.color = color;
  	}

	public String getJournalist(){
	  return journalist;
	}
 
  	public void setJournalist(String journalist){
      this.journalist = journalist;
  	}

// add required for Criticism2 entity version

	public String getMedia(){
	  return media;
	}
 
  	public void setMedia(String media){
      this.media = media;
  	}

// Not Common Properties 

	public Media1 getMedia1() {
      return media1;
	}

	public void setMedia1(Media1 media1) {
      this.media1 = media1;
	}

}//end Class


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
//File Prize1.java
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;

//Annotations Embedded Class
@Embedded
class Prize1{

// Common Properties	
	private String event;
	private int year;

//add required for Prize1 entity version
	private String[] names;

// Not Common Properties 
	private String name;

public Prize1{
}

//Embedded Entity Code

//Common Properties	

	public String getEvent(){
	  return event;
	}
 
  	public void setEvent(String event){
      this.event = event;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

// add required for Prize1 entity version

	public String[] getNames(){
      return names;
	}

	public void setNames(String[] names){
      this.names = names;
  	}

// Not Common Properties 

	public String getName(){
	  return name;
	}

	public void setName(String name){
      this.name = name;
	}
}//end Class


// Update



//Embedded Entity
//File Prize2.java
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;

//Annotations Embedded Class
@Embedded
class Prize2{

// Common Properties	
	private String event;
	private int year;

//add required for Prize2 entity version
	private String name;

// Not Common Properties 
	private String[] names;

public Prize2{
}

//Embedded Entity Code

//Common Properties	

	public String getEvent(){
	  return event;
	}
 
  	public void setEvent(String event){
      this.event = event;
  	}

	public int getYear(){
	  return year;
	}
 
  	public void setYear(int year){
      this.year = year;
  	}

// add required for Prize2 entity version

	public String getName(){
	  return name;
	}
 
  	public void setName(String name){
      this.name = name;
  	}

// Not Common Properties 

	public String[] getNames(){
      return names;
	}
  
  	public void setNames(String[] names){
      this.names = names;
  	}
}//end Class


// Update



