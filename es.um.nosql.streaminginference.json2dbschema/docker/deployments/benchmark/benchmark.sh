#!/bin/bash

# https://stackoverflow.com/questions/4774054/reliable-way-for-a-bash-script-to-get-the-full-path-to-itself
SCRIPTPATH="$( cd "$(dirname "$0")" ; pwd -P )"
cd $SCRIPTPATH

# Set datasets to download & run
STATIONS_DS=true # ~ 85 kB
AIRPORTS_DS=true # ~ 50 MB
MOVIES_DS=true # ~ 3 MB
LICENSES_DS=true # ~ 50 kB
METEORITES_DS=true # ~ 250 kB
NOBEL_DS=true # ~ 650 kB
STACKOVERFLOWES_DS=false # ~ 183 MB
LASTFM_SAMPLE_DS=false # ~ 34 MB
LASTFM_TEST_DS=false # 390 MB
LASTFM_TRAIN_DS=false # ~ 2.6 GB

echo "Mongo database cleaning ... "
mongo airport --eval "db.dropDatabase()"  > /dev/null
mongo stations --eval "db.dropDatabase()"  > /dev/null
mongo moviesds --eval "db.dropDatabase()"  > /dev/null
mongo licenses --eval "db.dropDatabase()"  > /dev/null
mongo nasa --eval "db.dropDatabase()"  > /dev/null
mongo nobel --eval "db.dropDatabase()"  > /dev/null
mongo stackoverflow --eval "db.dropDatabase()"  > /dev/null
mongo lastfm --eval "db.dropDatabase()"  > /dev/null

if $STATIONS_DS
then
	echo "Getting Philadelphia Bike Share Stations dataset (85 kB approx) ..."
	test -e stations/bikes.json || wget -O stations/bikes.json https://www.rideindego.com/stations/json/ 
	echo "Importing data to Mongo ..."
	sed -e 's/{"features"://g; s/,"type":"FeatureCollection"}$//g' stations/bikes.json | mongoimport --host=127.0.0.1 --db stations --collection bikes --jsonArray
fi

if $AIRPORTS_DS
then
	echo "Getting airports-related datasets (50 MB approx) ..."
	#test -e airlines/airfields.json || wget https://think.cs.vt.edu/corgis/json/airlines/airfields.json -O airlines/airfields.json
	test -e airlines/airlines.json || wget https://think.cs.vt.edu/corgis/json/airlines/airlines.json -O airlines/airlines.json
	test -e airlines/airports.json || wget https://gist.githubusercontent.com/tdreyno/4278655/raw/7b0762c09b519f40397e4c3e100b097d861f5588/airports.json -O airlines/airports.json
	echo "Importing data to Mongo ..."
	#mongoimport --host=127.0.0.1 --db airport --collection airfields --jsonArray --file airlines/airfields.json
	mongoimport --host=127.0.0.1 --db airport --collection airlines --jsonArray --file airlines/airlines.json
	mongoimport --host=127.0.0.1 --db airport --collection airports --jsonArray --file airlines/airports.json
fi

if $MOVIES_DS
then
	echo "Getting movies dataset (3 MB approx) ..."
	test -e movies/movies.json || wget https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json -O movies/movies.json
	echo "Importing data to Mongo ..."
	mongoimport --host=127.0.0.1 --db moviesds --collection movies --jsonArray --file movies/movies.json
fi

if $LICENSES_DS
then
	echo "Getting licenses dataset (50 kB approx) ..."
	test -e licenses/licenses.json || wget -O licenses/licenses.json https://api.opensource.org/licenses/
	echo "Importing data to Mongo ..."
	mongoimport --host=127.0.0.1 --db licenses --collection licenses --jsonArray --file licenses/licenses.json
fi

if $METEORITES_DS
then
	echo "Getting NASA meteorites datasets (250 kB approx) ..."
	test -e nasa/meteories.json || wget https://data.nasa.gov/resource/y77d-th95.json -O nasa/meteories.json
	test -e nasa/asteroids.json || wget https://data.nasa.gov/resource/2vr3-k9wn.json -O nasa/asteroids.json
	echo "Importing data to Mongo ..."
	mongoimport --host=127.0.0.1 --db nasa --collection meteorites --jsonArray --file nasa/meteories.json
	mongoimport --host=127.0.0.1 --db nasa --collection asteroids --jsonArray --file nasa/asteroids.json
fi

if $NOBEL_DS
then
	echo "Getting Nobel prizes datasets (650 kB approx) ..."
	test -e nobel/country.json || wget http://api.nobelprize.org/v1/country.json -O nobel/country.json
	test -e nobel/laureate.json || wget http://api.nobelprize.org/v1/laureate.json -O nobel/laureate.json
	test -e nobel/prize.json || wget http://api.nobelprize.org/v1/prize.json -O nobel/prize.json
	echo "Importing data to Mongo ..."
	sed -e 's/{"countries"://g; s/}$//g' nobel/country.json | mongoimport --host=127.0.0.1 --db nobel --collection countries --jsonArray
	sed -e 's/{"laureates"://g; s/}$//g' nobel/laureate.json | mongoimport --host=127.0.0.1 --db nobel --collection laureates --jsonArray
	sed -e 's/{"prizes"://g; s/}$//g' nobel/prize.json | mongoimport --host=127.0.0.1 --db nobel --collection prizes --jsonArray
fi

if $STACKOVERFLOWES_DS
then
	echo "Getting StackOverflow ES dataset (183 MB approx) ... "
	cd stackoverflow
	file=Posts.csv
	test -e $file || wget http://neuromancer.inf.um.es:8080/es.stackoverflow/`basename ${file}`.gz -O - 2>/dev/null | gunzip > $file
	file=Users.csv
	test -e $file || wget http://neuromancer.inf.um.es:8080/es.stackoverflow/`basename ${file}`.gz -O - 2>/dev/null | gunzip > $file
	file=Tags.csv
	test -e $file || wget http://neuromancer.inf.um.es:8080/es.stackoverflow/`basename ${file}`.gz -O - 2>/dev/null | gunzip > $file
	file=Comments.csv
	test -e $file || wget http://neuromancer.inf.um.es:8080/es.stackoverflow/`basename ${file}`.gz -O - 2>/dev/null | gunzip > $file
	file=Votes.csv
	test -e $file || wget http://neuromancer.inf.um.es:8080/es.stackoverflow/`basename ${file}`.gz -O - 2>/dev/null | gunzip > $file
	echo "Importing data to Mongo ..."
	mongoimport --host=127.0.0.1 --db stackoverflow --collection posts --type csv --headerline --file Posts.csv
	mongoimport --host=127.0.0.1 --db stackoverflow --collection users --type csv --headerline --file Users.csv
	mongoimport --host=127.0.0.1 --db stackoverflow --collection tags --type csv --headerline --file Tags.csv
	mongoimport --host=127.0.0.1 --db stackoverflow --collection comments --type csv --headerline --file Comments.csv
	mongoimport --host=127.0.0.1 --db stackoverflow --collection votes --type csv --headerline --file Votes.csv
	cd -
fi


if $LASTFM_SAMPLE_DS
then
	echo "Getting lastFM songs SAMPLE dataset (34 MB approx) ... "
	wget -nc http://labrosa.ee.columbia.edu/millionsong/sites/default/files/lastfm/lastfm_subset.zip -O lastfm/lastfm_subset.zip 2> /dev/null
	test -e lastfm/lastfm_subset || unzip lastfm/lastfm_subset.zip -d lastfm
	echo "Importing data to Mongo ..."
	find lastfm/lastfm_subset -name "*.json" | xargs cat | mongoimport --host=127.0.0.1 --db lastfm --collection artists
fi

if $LASTFM_TEST_DS
then
	echo "Getting lastFM songs TEST dataset (390 MB approx) ... "
	wget -nc http://labrosa.ee.columbia.edu/millionsong/sites/default/files/lastfm/lastfm_test.zip -O lastfm/lastfm_test.zip 2> /dev/null
	test -e lastfm/lastfm_test || unzip lastfm/lastfm_test.zip -d lastfm
	echo "Importing data to Mongo ..."
	find lastfm/lastfm_test -name "*.json" | xargs cat | mongoimport --host=127.0.0.1 --db lastfm --collection artists
fi

if $LASTFM_TRAIN_DS
then
	echo "Getting lastFM songs TRAIN dataset (2.6 GB approx) ... "
	wget -nc http://labrosa.ee.columbia.edu/millionsong/sites/default/files/lastfm/lastfm_train.zip -O lastfm/lastfm_train.zip 2> /dev/null
	test -e lastfm/lastfm_train || unzip lastfm/lastfm_train.zip -d lastfm
	echo "Importing data to Mongo ..."
	find lastfm/lastfm_train -name "*.json" | xargs cat | mongoimport --host=127.0.0.1 --db lastfm --collection artists
fi

echo "DONE"




