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

declare -a ELEMENTS
INDEX=0
ENTITIES=6
VERSIONS=2
EXECUTORS=2
CORES=1
# now enjoy the options in order and nicely split until we see --
while [[ $# -ne 0 ]]; do
    case "$1" in
	-b|--batch)
	    BATCH=$2
	    shift 2
	    ;;
	-c|--cores)
	    CORES=$2
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
	--)
            shift
            ;;
        *)
	    ELEMENTS[$INDEX]=$1
            let INDEX=$INDEX+1
            shift 1
            ;;
    esac
done

echo "ELEMENTS: ${ELEMENTS[*]}"
echo "cores: $CORES, entities: $ENTITIES, versions: $VERSIONS, batch: $BATCH, executors: $EXECUTORS"
for ELEMENT in "${ELEMENTS[@]}"
do
  echo "$ELEMENT"
  let BATCH_SIZE=$ELEMENT/$BATCH
  echo "BATCH SIZE: $BATCH_SIZE"
  ./cesga.sh -e $ELEMENT -t $ENTITIES -v $VERSIONS -b $BATCH_SIZE -x $EXECUTORS -c $CORES
done





