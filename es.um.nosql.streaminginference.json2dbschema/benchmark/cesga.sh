#!/bin/bash

#Based on https://stackoverflow.com/a/29754866

getopt --test > /dev/null
if [[ $? -ne 4 ]]; then
    echo "I’m sorry, `getopt --test` failed in this environment."
    exit 1
fi

OPTIONS=e:v:t:b:x:c:
LONGOPTIONS=elements:,versions:,entities:,batch:,executors:,cores:

# -temporarily store output to be able to check for errors
# -e.g. use “--options” parameter by name to activate quoting/enhanced mode
# -pass arguments only via   -- "$@"   to separate them correctly
PARSED=$(getopt --options=$OPTIONS --longoptions=$LONGOPTIONS --name "$0" -- "$@")
if [[ $? -ne 0 ]]; then
    # e.g. $? == 1
    #  then getopt has complained about wrong arguments to stdout
    echo "WRONG ARGUMENTS"
    exit 2
fi
# read getopt’s output this way to handle the quoting right:
eval set -- "$PARSED"

ELEMENTS=10000
ENTITIES=6
VERSIONS=2
EXECUTORS=2
CORES=1
# now enjoy the options in order and nicely split until we see --
while true; do
    case "$1" in
	-b|--batch)
	    BATCH=$2
	    shift 2
	    ;;
	-c|--cores)
	    CORES=$2
	    shift 2
	    ;;
        -e|--elements)
            ELEMENTS=$2
            shift 2
            ;;
        -t|--entities)
            ENTITIES=$2
            shift 2
            ;;
        -v|--versions)
            VERSIONS=$2
            shift 2
            ;;
	-x|--executors)
            EXECUTORS=$2
            shift 2
            ;;
        *)
            break
            ;;
    esac
done

echo "cores: $CORES, elements: $ELEMENTS, entities: $ENTITIES, versions: $VERSIONS, batch: $BATCH, executors: $EXECUTORS"

rm -rf input
mkdir -p input

java -jar es.um.nosql.streaminginference.benchmark-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
    --elements $ELEMENTS \
    --entities $ENTITIES \
    --versions $VERSIONS \
    --depth 2 \
    --fields 2 \
    --mode file \
    --flow stream \
    --batch $BATCH \
    --output input/collection.json \
    --delay 10

hdfs dfs -rm -r -f output
hdfs dfs -mkdir -p output
hdfs dfs -rm -r -f input
hdfs dfs -mkdir -p input
hdfs dfs -put input/* input

for i in `seq 1 10`;
do
  spark-submit \
    --conf "spark.dynamicAllocation.enabled=false" \
    --driver-memory 8g \
    --master yarn \
    --num-executors $EXECUTORS \
    --executor-cores $CORES \
    --deploy-mode client es.um.nosql.streaminginference.json2dbschema-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
    --mode file \
    --input input \
    --benchmark true \
    --interval 100000 \
    --kryo true
done    

hdfs dfs -copyToLocal output/stats.csv results-$ELEMENTS-$ENTITIES-$VERSIONS-$BATCH-$EXECUTORS-$CORES.csv

echo "DONE"


