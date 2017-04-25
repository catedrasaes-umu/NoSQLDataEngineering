# NoSQLDataEngineering
NoSQL Data Engineering

# Table of contents

- [Inference process](#inference-process)
- [NoSQLSchema metamodel](#nosql-schema-metamodel)
- [Database import](#database-import)
- [NoSQL import](#nosql-import)
- [Json to DBSchema](#json-to-dbschema)
- [NoSQL Schema visualization](#nosql-schema-visualization)
- [Data visualization](#data-visualization)
- [Entity differentiation](#entity-differentiation)
- [Decision tree](#decision-tree)
- [DSL4Mongoose](#dsl-for-mongoose)
- [DSLParameter](#dsl-parameter)
- [PlantUML](#plantuml)

***

# Inference process

***

# NoSQL Schema metamodel

The projects involved in the NoSQL Schema metamodel definition are the following ones:

* `es.um.nosql.schemainference`: This projects stores the metamodel definition in an ecore file, stored in model/nosqlschema.ecore. It also contains the generated java classes for this metamodel, as well as some utilities such as a Model loader used to load model definitions in a XMI file.
* `es.um.nosql.schemainference.edit` and `es.um.nosql.schemainference.editor`: These projects are used to define a generated editor in order to manipulate NoSQL schema models with the Ecore interface.

The most important elements of this projects are, apart from the metamodel definition, the Model loader, which will be used in several other projects to start some processes from a model file located somewhere in the file system. It also contains a PrettyPrinter class which can take a NoSQL schema model and print it to the standard input in a human-readable way.

## The NoSQL schema metamodel

<figure>
    <img src="figures/nosqlschema_metamodel.png" align="center"/>
</figure>
<br/>

* The NoSQLSchema metamodel has 'NoSQLSchema' as a root element with a certain name and containing several Entities.
* An 'Entity' describes an object of the real world such as 'Movie', 'Prize', 'Director' or 'Criticism'. It contains, at the same time, several version definitions.
* An 'EntityVersion' is a certain version of an 'Entity', with an 'identifier', a 'count' attribute (indicating how many objects in the bd correspond to this version) and a boolean 'root' attribute, indicating if this 'EntityVersion' is a root element or is embedded into some other object. An 'EntityVersion' may contain several properties.
* An 'Attribute' is a kind of 'Property' with a 'name' and an associated 'type'. This 'type' may be at the same time a 'Primitive type' such as a 'String', 'Number' or 'Boolean', or a 'Tuple' containing several 'Tuples' and 'Primitive types' inside of it.
* A 'Reference' is a kind of 'Property' with a cardinality, a 'name' and is associated with an 'Entity'. This way an object inside a database may be associated with another object by pointing to its 'id' on this field.
* An 'Aggregate' is a kind of 'Property' with a cardinality, a 'name' and potentially several associated 'EntityVersions'. This way an object may embed several other objects inside of it.

<figure>
    <img src="figures/nosqlschema_example.png" align="center"/>
</figure>
<br/>

In the image above there is an example of a NoSQLSchema model based on a 'Movies' domain. It is divided into several parts:

* Entity 'Movie': With three 'EntityVersions', each one of them containing several 'Attributes', 'References' and 'Aggregates'. Some of these references are associated with 'Director', and some 'Aggregations' embed other entity versions such as 'Criticism'.
* Entity 'Director': Divided in two versions with references to 'Movie'.
* Entity 'Criticism': Divided in two versions with several simple 'Attributes' of 'Primitive Type'.
* Entity 'Rating': With just one entity version and a couple of string 'Attributes'.
* Entity 'Prize': With another entity version, and a couple 'Attributes', being one of them a 'Tuple'.

A NoSQLSchema model may be obtained by aplying the inference process described here(#inference-process). Once a NoSQLSchema model is obtained, it may be used for several things:

* It can be visualized by using the NoSQL Schema visualization tool ([link](#nosql-schema-visualization))
* It can be used to generate a Decision tree to effectively classify objects of this database. Projects related are 'Entity Differentiation'([link](#entity-differentiation)) and 'Decision tree'([link](#decision-tree))
* ...

***

# Database import

***

# NoSQL import

***

# Json to DBSchema

***

# NoSQL Schema visualization

***

# Data visualization

***

# Entity differentiation

***

# Decision tree

***

# DSL for Mongoose

***

# DSL Parameter

***

# PlantUML

***