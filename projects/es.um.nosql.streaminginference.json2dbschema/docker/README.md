# Deployment of a Hadoop cluster with Spark

Build Mongo Dockerfile, which will have wget and unzip installed for benchmarking:

```
sudo docker build -f mongo-dockerfile -t pablodms/mongo-benchmark:latest .
```

## Set up Mongo tests

Edit deployments/benchmark/benchmark.sh to specify the datasets to be run

## Building and running Schema Inference on Spark Streaming


### File mode

```
./start.sh
su hadoop # By now we will be logged into namenode container
cd ~
hdfs dfs -mkdir -p input
hdfs dfs -mkdir -p output
hdfs dfs -mkdir -p temp
spark-submit --conf "spark.yarn.am.memory=1g" --driver-memory 1G --executor-memory 2G --num-executors 2 --master yarn --deploy-mode client /deployments/es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar --mode file --input input --output output > /dev/null 2>&1 &
hdfs dfs -put /deployments/movies_1.json temp
hdfs dfs -mv temp/movies_1.json input
hdfs dfs -ls output
hdfs dfs -touchz output/_DONE # Finish Streaming application
```

### Mongo mode

```
./start.sh
su hadoop # By now we will be logged into namenode container
cd ~
hdfs dfs -mkdir -p output
spark-submit --conf "spark.yarn.am.memory=1g" --driver-memory 1G --executor-memory 2G --num-executors 2 --master yarn --deploy-mode client /deployments/es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar --mode mongo --database "airport,stations,moviesds,licenses,nasa,nobel,stackoverflow,lastfm" --host mongo --port 27017 --output output > /dev/null 2>&1 &
# We can make changes now in mongo container: "docker exec -it mongodb mongo"
hdfs dfs -ls output
hdfs dfs -touchz output/_DONE # Finish Streaming application
```

### Couch mode:

```
./start.sh
su hadoop # By now we will be logged into namenode container
cd ~
hdfs dfs -mkdir -p output
spark-submit --conf "spark.yarn.am.memory=1g" --driver-memory 1G --executor-memory 2G --num-executors 2 --master yarn --deploy-mode client /deployments/es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar --mode couch --output output --host couch --port 5984 > /dev/null 2>&1 &
# We can make changes now in http://127.0.0.1:5984/_utils/ 
hdfs dfs -ls output
hdfs dfs -touchz output/_DONE # Finish Streaming application
```
