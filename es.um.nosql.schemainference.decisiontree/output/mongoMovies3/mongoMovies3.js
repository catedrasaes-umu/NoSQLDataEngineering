'use strict'

var mongoMovies3 = {

	name: "mongoMovies3",
	Movie: {
		name: "Movie",
		entityVersionForObject: function (obj)
		{
			if (("criticisms" in obj))
			{
				if ((("prizes" in obj) && (obj.prizes.constructor === Array) &&
				    obj.prizes.every(function(e)
				        { return (typeof e === 'object') && !(e.constructor === Array)
				            && (
				            mongoMovies3.Prize_1.isOfExactType(e) || 
				            mongoMovies3.Prize_2.isOfExactType(e)
				            );
				        })
				))
				{
					return "Movie_1";
				} else {
					return "Movie_4";
				}
			} else {
				if (("genre" in obj))
				{
					if (("running_time" in obj))
					{
						return "Movie_2";
					} else {
						return "Movie_3";
					}
				} else {
					return "Movie_5";
				}
			}
		}
		checkEV_Movie_1: function (obj)
		{
		if ((!("criticisms" in obj)))
			return false;
		if ((!("prizes" in obj) || !((obj.prizes.constructor === Array) &&
		    obj.prizes.every(function(e)
		        { return (typeof e === 'object') && !(e.constructor === Array)
		            && (
		            mongoMovies3.Prize_1.isOfExactType(e) || 
		            mongoMovies3.Prize_2.isOfExactType(e)
		            );
		        })
		)))
			return false;
		
			return true;
		},
		checkEV_Movie_2: function (obj)
		{
		if (("criticisms" in obj))
			return false;
		if ((!("genre" in obj)))
			return false;
		if ((!("running_time" in obj)))
			return false;
		
			return true;
		},
		checkEV_Movie_3: function (obj)
		{
		if (("criticisms" in obj))
			return false;
		if ((!("genre" in obj)))
			return false;
		if (("running_time" in obj))
			return false;
		
			return true;
		},
		checkEV_Movie_4: function (obj)
		{
		if ((!("criticisms" in obj)))
			return false;
		if ((("prizes" in obj) && (obj.prizes.constructor === Array) &&
		    obj.prizes.every(function(e)
		        { return (typeof e === 'object') && !(e.constructor === Array)
		            && (
		            mongoMovies3.Prize_1.isOfExactType(e) || 
		            mongoMovies3.Prize_2.isOfExactType(e)
		            );
		        })
		))
			return false;
		
			return true;
		},
		checkEV_Movie_5: function (obj)
		{
		if (("criticisms" in obj))
			return false;
		if (("genre" in obj))
			return false;
		
			return true;
		}
	}
	,,
	Movietheater: {
		name: "Movietheater",
		entityVersionForObject: function (obj)
		{
			if (("noOfRooms" in obj))
			{
				return "Movietheater_2";
			} else {
				return "Movietheater_1";
			}
		}
		checkEV_Movietheater_1: function (obj)
		{
		if (("noOfRooms" in obj))
			return false;
		
			return true;
		},
		checkEV_Movietheater_2: function (obj)
		{
		if ((!("noOfRooms" in obj)))
			return false;
		
			return true;
		}
	}
	,,
	Director: {
		name: "Director",
		entityVersionForObject: function (obj)
		{
			if (("actor_movies" in obj))
			{
				return "Director_1";
			} else {
				return "Director_2";
			}
		}
		checkEV_Director_1: function (obj)
		{
		if ((!("actor_movies" in obj)))
			return false;
		
			return true;
		},
		checkEV_Director_2: function (obj)
		{
		if (("actor_movies" in obj))
			return false;
		
			return true;
		}
	}
	,,
	Criticism: {
		name: "Criticism",
		entityVersionForObject: function (obj)
		{
			if ((("media" in obj) && (typeof obj.media === 'object') && !(obj.media.constructor === Array)
			    && mongoMovies3.Media_1.isOfExactType(obj.media)
			))
			{
				return "Criticism_1";
			} else {
				return "Criticism_2";
			}
		}
		checkEV_Criticism_1: function (obj)
		{
		if ((!("media" in obj) || !((typeof obj.media === 'object') && !(obj.media.constructor === Array)
		    && mongoMovies3.Media_1.isOfExactType(obj.media)
		)))
			return false;
		
			return true;
		},
		checkEV_Criticism_2: function (obj)
		{
		if ((("media" in obj) && (typeof obj.media === 'object') && !(obj.media.constructor === Array)
		    && mongoMovies3.Media_1.isOfExactType(obj.media)
		))
			return false;
		
			return true;
		}
	}
	,,
	Prize: {
		name: "Prize",
		entityVersionForObject: function (obj)
		{
			if (("names" in obj))
			{
				return "Prize_1";
			} else {
				return "Prize_2";
			}
		}
		checkEV_Prize_1: function (obj)
		{
		if ((!("names" in obj)))
			return false;
		
			return true;
		},
		checkEV_Prize_2: function (obj)
		{
		if (("names" in obj))
			return false;
		
			return true;
		}
	}
	,
}

module.exports = mongoMovies3;

