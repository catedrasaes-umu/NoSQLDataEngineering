'use strict'

var mongoMovies3 = {

	name: "mongoMovies3",
	Movie: {
		name: "Movie",
		entityVersionForObject: function (obj)
		{
			if (("criticisms" in obj))
			{
				if (("prizes" in obj) && (obj.prizes.constructor === Array) &&
				    obj.prizes.every(function(e)
				        { return (typeof e === 'object') && !(e.constructor === Array)
				            && (
				            mongoMovies3.Prize_1.isOfExactType(e) || 
				            mongoMovies3.Prize_2.isOfExactType(e)
				            );
				        })
				)
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
	},
	Movietheater: {
		name: "Movietheater",
		entityVersionForObject: function (obj)
		{
			if (("roomNumbers" in obj))
			{
				return "Movietheater_2";
			} else {
				return "Movietheater_1";
			}
		}
	},
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
	},
	Criticism: {
		name: "Criticism",
		entityVersionForObject: function (obj)
		{
			if (("media" in obj) && (typeof obj.media === 'object') && !(obj.media.constructor === Array)
			    && mongoMovies3.Media_1.isOfExactType(obj.media)
			)
			{
				return "Criticism_1";
			} else {
				return "Criticism_2";
			}
		}
	},
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
	}
}

module.exports = mongoMovies3;

