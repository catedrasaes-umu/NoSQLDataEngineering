
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
class Movie1{

//Common Properties	
	@Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.ASC)
	private String _id;
	@Indexed (IndexDirection.DESC)
	private int year;
	private String type;
	@Reference
	private Director director;

//add required for Movie1 entity version
	private String genre;
	@Embedded
	private ArrayList<Criticism1> criticisms1;
	@Embedded
	private ArrayList<Prize1> prizes1;

// Not Common Properties 
	private int running_time;
	private String[] genres;
	private String[] writers;
	@Embedded
	private Rating4 rating4;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize3> prizes3;

public Movie1{
}

public Movie1(String title,String _id,int year,String type,Director director,String genre,Number running_time,String[] genres,String[] writers,){
	this.title=title;
	this._id=_id;
	this.year=year;
	this.type=type;
	this.director=director;
	this.genre=genre;
	this.running_time=running_time;
	this.genres=genres;
	this.writers=writers;
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

	public int getRunning_time(){
	  return running_time;
	}

	public void setRunning_time(int running_time){
      this.running_time = running_time;
	}

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

	public Rating4 getRating4(){
      return rating4;
	}

	public void setRating4(Rating4 rating4){
      this.rating4 = rating4;
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

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@27d415d9 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@5c18298f (fieldName: title)] 

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
class Movie2{

//Common Properties	
	@Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.ASC)
	private String _id;
	@Indexed (IndexDirection.DESC)
	private int year;
	private String type;
	@Reference
	private Director director;

//add required for Movie2 entity version
	private String genre;
	private int running_time;

// Not Common Properties 
	private String[] genres;
	private String[] writers;
	@Embedded
	private Rating4 rating4;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize1> prizes1;
	@Embedded
	private List<Prize3> prizes3;

public Movie2{
}

public Movie2(String title,String _id,int year,String type,Director director,String genre,int running_time,String[] genres,String[] writers,){
	this.title=title;
	this._id=_id;
	this.year=year;
	this.type=type;
	this.director=director;
	this.genre=genre;
	this.running_time=running_time;
	this.genres=genres;
	this.writers=writers;
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

	public int getRunning_time(){
	  return running_time;
	}
 
  	public void setRunning_time(int running_time){
      this.running_time = running_time;
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

	public Rating4 getRating4(){
      return rating4;
	}

	public void setRating4(Rating4 rating4){
      this.rating4 = rating4;
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

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@27d415d9 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@5c18298f (fieldName: title)] 

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
class Movie3{

//Common Properties	
	@Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.ASC)
	private String _id;
	@Indexed (IndexDirection.DESC)
	private int year;
	private String type;
	@Reference
	private Director director;

//add required for Movie3 entity version
	private String genre;
	@Embedded
	private ArrayList<Prize3> prizes3;

// Not Common Properties 
	private int running_time;
	private String[] genres;
	private String[] writers;
	@Embedded
	private Rating4 rating4;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize1> prizes1;

public Movie3{
}

public Movie3(String title,String _id,int year,String type,Director director,String genre,int running_time,String[] genres,String[] writers,){
	this.title=title;
	this._id=_id;
	this.year=year;
	this.type=type;
	this.director=director;
	this.genre=genre;
	this.running_time=running_time;
	this.genres=genres;
	this.writers=writers;
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

	public int getRunning_time(){
	  return running_time;
	}

	public void setRunning_time(int running_time){
      this.running_time = running_time;
	}

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

	public Rating4 getRating4(){
      return rating4;
	}

	public void setRating4(Rating4 rating4){
      this.rating4 = rating4;
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

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@27d415d9 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@5c18298f (fieldName: title)] 

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
class Movie4{

//Common Properties	
	@Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.ASC)
	private String _id;
	@Indexed (IndexDirection.DESC)
	private int year;
	private String type;
	@Reference
	private Director director;

//add required for Movie4 entity version
	private String genre;
	@Embedded
	private Rating4 rating4;
	@Embedded
	private ArrayList<Criticism4> criticisms4;

// Not Common Properties 
	private int running_time;
	private String[] genres;
	private String[] writers;
	@Embedded
	private List<Prize1> prizes1;
	@Embedded
	private List<Prize3> prizes3;

public Movie4{
}

public Movie4(String title,String _id,int year,String type,Director director,String genre,int running_time,String[] genres,String[] writers,){
	this.title=title;
	this._id=_id;
	this.year=year;
	this.type=type;
	this.director=director;
	this.genre=genre;
	this.running_time=running_time;
	this.genres=genres;
	this.writers=writers;
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

	public Rating4 getRating4(){
      return rating4;
	}
  
	public void setRating4(Rating4 rating4){
      this.rating4 = rating4;
	}

	public List<Criticism4> getCriticisms4(){
      return criticisms4;
	  }

	public void setCriticisms4(List<Criticism4> criticisms4){
      this.criticisms4 = criticisms4;
	}

// Not Common Properties 

	public int getRunning_time(){
	  return running_time;
	}

	public void setRunning_time(int running_time){
      this.running_time = running_time;
	}

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

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@27d415d9 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@5c18298f (fieldName: title)] 

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
class Movie5{

//Common Properties	
	@Indexed (unique=true)
	private String title;
	@Indexed (IndexDirection.ASC)
	private String _id;
	@Indexed (IndexDirection.DESC)
	private int year;
	private String type;
	@Reference
	private Director director;

//add required for Movie5 entity version
	private String[] genres;
	private String[] writers;

// Not Common Properties 
	private String genre;
	private int running_time;
	@Embedded
	private Rating4 rating4;
	@Embedded
	private List<Criticism4> criticisms4;
	@Embedded
	private List<Prize1> prizes1;
	@Embedded
	private List<Prize3> prizes3;

public Movie5{
}

public Movie5(String title,String _id,int year,String type,Director director,String genres,String writers,String genre,int running_time,){
	this.title=title;
	this._id=_id;
	this.year=year;
	this.type=type;
	this.director=director;
	this.genres=genres;
	this.writers=writers;
	this.genre=genre;
	this.running_time=running_time;
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

	public int getRunning_time(){
	  return running_time;
	}

	public void setRunning_time(int running_time){
      this.running_time = running_time;
	}

	public Rating4 getRating4(){
      return rating4;
	}

	public void setRating4(Rating4 rating4){
      this.rating4 = rating4;
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

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@27d415d9 (fieldName: genre), es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@5c18298f (fieldName: title)] 

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
class Movietheater1{

//Common Properties	
	private String _id;
	private String name;
	private String type;
	private String city;
	private String country;

//add required for Movietheater1 entity version

// Not Common Properties 
	private int noOfRooms;

public Movietheater1{
}

public Movietheater1(String _id,String name,String type,String city,String country,Number noOfRooms,){
	this._id=_id;
	this.name=name;
	this.type=type;
	this.city=city;
	this.country=country;
	this.noOfRooms=noOfRooms;
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
	  return noOfRooms;
	}

	public void setRoomNumbers(int noOfRooms){
      this.noOfRooms = noOfRooms;
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
class Movietheater2{

//Common Properties	
	private String _id;
	private String name;
	private String type;
	private String city;
	private String country;

//add required for Movietheater2 entity version
	private int noOfRooms;

// Not Common Properties 

public Movietheater2{
}

public Movietheater2(String _id,String name,String type,String city,String country,int noOfRooms,){
	this._id=_id;
	this.name=name;
	this.type=type;
	this.city=city;
	this.country=country;
	this.noOfRooms=noOfRooms;
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
	  return noOfRooms;
	}
 
  	public void setRoomNumbers(int noOfRooms){
      this.noOfRooms = noOfRooms;
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
class Director1{

//Common Properties	
	@Indexed (IndexDirection.DESC)
	private String _id;
	@Indexed (unique=true)
	private String name;
	private String type;
	@Reference
	private List<Movie> directed_movies;

//add required for Director1 entity version
	@Reference
	private List<Movie> actor_movies;

// Not Common Properties 

public Director1{
}

public Director1(String _id,String name,String type,ListMovie directed_movies,ListMovie actor_movies,){
	this._id=_id;
	this.name=name;
	this.type=type;
	this.directed_movies=directed_movies;
	this.actor_movies=actor_movies;
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

	public List<Movie> getDirected_movies(){
      return directed_movies;
	}

  	public void setDirected_movies(List<Movie> directed_movies){
      this.directed_movies = directed_movies;
  	}


// add required for Director1 entity version

	public List<Movie> getActor_movies(){
      return actor_movies;
	}

  	public void setActor_movies(List<Movie> actor_movies){
      this.actor_movies = actor_movies;
  	}


// Not Common Properties 

	public List<Movie> getActor_movies(){
      return actor_movies;
	}

  	public void setActor_movies(List<Movie> actor_movies){
      this.actor_movies = actor_movies;
  	}

}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@31f924f5 (fieldName: name)] 

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
class Director2{

//Common Properties	
	@Indexed (IndexDirection.DESC)
	private String _id;
	@Indexed (unique=true)
	private String name;
	private String type;
	@Reference
	private List<Movie> directed_movies;

//add required for Director2 entity version

// Not Common Properties 
	@Reference
	private List<Movie> actor_movies;

public Director2{
}

public Director2(String _id,String name,String type,ListMovie directed_movies,ListMovie actor_movies,){
	this._id=_id;
	this.name=name;
	this.type=type;
	this.directed_movies=directed_movies;
	this.actor_movies=actor_movies;
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

	public List<Movie> getDirected_movies(){
      return directed_movies;
	}

  	public void setDirected_movies(List<Movie> directed_movies){
      this.directed_movies = directed_movies;
  	}


// add required for Director2 entity version

// Not Common Properties 
}//end Class


// Update

[es.um.nosql.schemainference.dsl4mongoose.impl.UpdateImpl@31f924f5 (fieldName: name)] 

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
//File Rating1.java
package mongoMovies3.morphiaMapper;
import org.mongodb.morphia.annotations.Embedded;

//Annotations Embedded Class
@Embedded
class Rating1{

// Common Properties	
	private int score;
	private int voters;

//add required for Rating1 entity version

// Not Common Properties 

public Rating1{
}

//Embedded Entity Code

//Common Properties	

	public int getScore(){
	  return score;
	}
 
  	public void setScore(int score){
      this.score = score;
  	}

	public int getVoters(){
	  return voters;
	}
 
  	public void setVoters(int voters){
      this.voters = voters;
  	}

// add required for Rating1 entity version

// Not Common Properties 
}//end Class


// Update



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



