# Deployment of a Hadoop cluster with Spark

Build Mongo Dockerfile, which will have wget and unzip installed for benchmarking:

```
sudo docker build -f mongo-dockerfile -t pablodms/mongo-benchmark:latest .
```

## Set up Mongo tests

Edit deployments/benchmark.sh to specify the datasets to run

## Building and running Schema Inference on Spark Streaming


### File mode

```
./start.sh
su - hadoop # By now we will be logged into namenode container
hdfs dfs -mkdir -p input
hdfs dfs -mkdir -p output
spark-submit --master yarn --deploy-mode client /deployments/es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar --mode file --input input --output output &
hdfs dfs -put /deployments/movies_1.json input
hdfs dfs -touchz output/_OUTPUT # Make Spark output xmi files 
hdfs dfs -cat output/movies.xmi
hdfs dfs -touchz output/_OUTPUT # Finish Streaming application
```

### Mongo mode

```
./start.sh
su - hadoop # By now we will be logged into namenode container
hdfs dfs -mkdir -p output
spark-submit --conf spark.yarn.am.waitTime=110s --driver-memory 3G --executor-memory 3G --master yarn --deploy-mode client /deployments/es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar --mode mongo --database "airport,stations,moviesds,licenses,nasa,nobel,stackoverflow,lastfm" --host mongo --port 27017 --output output &
# We can make changes now in mongo container: "docker exec -it mongodb mongo"
hdfs dfs -touchz output/_OUTPUT # Make Spark output xmi files 
hdfs dfs -ls output
hdfs dfs -touchz output/_OUTPUT # Finish Streaming application
```

### Couch mode:

```
./start.sh
su - hadoop # By now we will be logged into namenode container
hdfs dfs -mkdir -p output
spark-submit --master yarn --deploy-mode client /deployments/es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar --mode couch --host couch --port 5984 --output output &
# We can make changes now in http://127.0.0.1:5984/_utils/
hdfs dfs -touchz output/_OUTPUT # Make Spark output xmi files 
hdfs dfs -cat output/couch.xmi
hdfs dfs -touchz output/_OUTPUT # Finish Streaming application
```
