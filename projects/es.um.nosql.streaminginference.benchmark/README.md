# Simple Collection Generator

Simple program for generating test collections to measure the performance of Streaming Inference project.

## Parameters

These parameters are for general purpose:

* elements: number of elements to generate (default 120).
* entities: number of entities found in generated elements (default 6).
* versions: number of versions per generated entity (default 2).
* depth: depth of nested aggregates per element (default 3).
* fields: number of fields per generated entity (default 2).
* flow: stress|stream controls how data will be generated (default "stress")
	* stress: output all elements at once
	* stream: output all elements in batches of *batch* size and *delay* interval.
* mode: file|mongo controls how data will be outputted (default "file")	

### Streaming parameters

* batch: size of generated batches (default 10)
* delay: time to wait to next batch

### File mode parameters

* output: path of generated file(s) (default "collection.json").

### Mongo mode parameters

* host: sets host to connect (default: "localhost").
* port: sets port to connect (default: 27017).
* user: (optional) username to access MongoDB.
* password: (optional) password to access MongoDB.
* database: sets database to output data (default "benchmark").
	* **WARNING** it will truncate collections contained in the specified database before writing

## Example usage

Generates one collection of 1200 elements, a total of 6 entities and 2 versions per entity in a file called "out.json".

```
java -jar es.um.nosql.streaminginference.benchmark-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
    --elements 1200 \
    --entities 6 \
    --versions 2 \
    --depth 2 \
    --fields 3 \
    --mode file \
	--output out.json
```

Generates one collection each second up to a total of 3 collections of 400 elements each one called "out_1.json", "out_2.json" and "out_3.json".

```
java -jar es.um.nosql.streaminginference.benchmark-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
    --elements 1200 \
    --entities 6 \
    --versions 2 \
    --depth 2 \
    --fields 3 \
    --mode file \
	--output out.json \
	--flow stream \
	--batch 400 \
	--delay 1000
```

Writes into "benchmark" mongo database located at "mongodb://localhost:27017" a set of 600 elements which will have 3 entities and 2 versions per entity:

```
java -jar es.um.nosql.streaminginference.benchmark-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
    --elements 600 \
    --entities 3 \
    --versions 2 \
    --depth 2 \
    --fields 2 \
    --mode mongo \
    --host localhost \
    --port 27017 \
    --database benchmark    
```