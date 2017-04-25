# NoSQLDataEngineering

This repository stores the last versions of all the projects available to infer a schema from an Aggregate-oriented NoSQL database. It uses a model-driven engineering approach based on Eclipse Modeling Framework. It also contains several utility projects used to visualize, classify and generate code from a NoSQL schema model.

A NoSQL schema is a schema used to define which data and in which format is being stored in a NoSQL database. This schema is not explicitly defined but inferred by a data-oriented infererence process.

# Table of contents

- [Inference process](#inference-process)
- [NoSQLSchema metamodel](#nosql-schema-metamodel)
- [Database import](#database-import)
- [NoSQL import](#nosql-import)
- [Json to DBSchema](#json-to-dbschema)
***
- [NoSQL Schema visualization](#nosql-schema-visualization)
- [Data visualization](#data-visualization)
***
- [Entity differentiation](#entity-differentiation)
- [Decision tree](#decision-tree)
***
- [DSL4Mongoose](#dsl-for-mongoose)
- [DSLParameter](#dsl-parameter)
- [PlantUML](#plantuml)
***

# Inference process

The inference process assumes the following initial scenario:

* An aggregate-oriented NoSQL database is running. At the moment supported databases are **MongoDB** and **CouchDB**. A database must be running, containing some information

***

# NoSQL Schema metamodel

The projects involved in the NoSQL Schema metamodel definition are the following ones:

* `es.um.nosql.schemainference`: This projects stores the metamodel definition in an ecore file, stored in model/nosqlschema.ecore. It also contains the generated java classes for this metamodel, as well as some utilities such as a **Model loader** used to load model definitions in a XMI file.
* `es.um.nosql.schemainference.edit` and `es.um.nosql.schemainference.editor`: These projects are used to define a generated editor in order to manipulate NoSQL schema models with the Ecore interface.

The most important elements of this projects are, apart from the metamodel definition, the Model loader, which will be used in several other projects to start some processes from a model file located somewhere in the file system. It also contains a PrettyPrinter class which can take a NoSQL schema model and print it to the standard input in a human-readable way.

## The NoSQL schema metamodel

<figure>
    <img src="figures/nosqlschema_metamodel.png" align="center"/>
</figure>
<br/>

* The NoSQLSchema metamodel has **NoSQLSchema** as a root element with a certain name and containing several Entities.
* An **Entity** describes an object of the real world such as _Movie_, _Prize_, _Director_ or _Criticism_. It contains, at the same time, several version definitions.
* An **EntityVersion** is a certain version of an _Entity_, with an _identifier_, a _count_ attribute (indicating how many objects in the bd correspond to this version) and a boolean _root_ attribute, indicating if this version is a root element or is embedded into some other object. An **EntityVersion** may contain several properties.
* An **Attribute** is a kind of **Property** with a _name_ and an associated _type_. This _type_ may be at the same time a **Primitive type** such as a _String_, _Number_ or _Boolean_, or a _Tuple_ containing several _Tuples_ and _Primitive types_ inside of it.
* A **Reference** is a kind of **Property** with a cardinality, a _name_ and is associated with an _Entity_. This way an object inside a database may be associated with another object by pointing to its _id_ on this field.
* An **Aggregate** is a kind of **Property** with a cardinality, a _name_ and potentially several associated _EntityVersions_. This way an object may embed several other objects inside of it.

<figure>
    <img src="figures/nosqlschema_example.png" align="center"/>
</figure>
<br/>

In the image above there is an example of a NoSQLSchema model based on a 'Movies' domain. It is divided into several parts:

* Entity _Movie_: With three **EntityVersions**, each one of them containing several **Attributes**, **References** and **Aggregates**. Some of these _references_ are associated with _Director_, and some _aggregations_ embed other entity versions such as _Criticism_.
* Entity _Director_: Divided in two versions with references to _Movie_.
* Entity _Criticism_: Divided in two versions with several simple **Attributes** of **Primitive Type**.
* Entity _Rating_: With just one entity version and a couple of string **Attributes**.
* Entity _Prize_: With another entity version, and a couple **Attributes**, being one of them a **Tuple**.

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

This tool is designed to represent schemas and schema versions in a friendly way using Sirius (https://eclipse.org/sirius/). Sirius is a modeling tool used to generate graphical DSLs in an easy way, but also to define a suitable representation for a given model. As a result of the inference process described above ([link](#inference-process)) a NoSQL\_Schema model is given, and this model is the input for the visualization process.

At the moment there are defined the following viewpoints:

* A _tree viewpoint_ in which all schema versions are listed as well as the entity versions (ordered alphabetically). This tree representation is divided into three branches, each one of them showing a certain aspect: A **Schema**branch in which _schema versions_ are shown ordered by the **Entity** to which its root **EntityVersion** belongs, an **Inverted Index** in which all the **EntityVersions** are listed as well as the _schema versions_ in which they are involved, and finally a **Dictionary** branch in which each **Entity** is displayed with its **EntityVersions** and **Properties**.

<figure>
    <img src="figures/vis_treeview.png" align="center">
</figure>
<br/>

* A _global schema diagram representation_ to visualize the **Entities**, **EntityVersions** and **Properties**. Each one of the elements shows a defined style. This diagram shows, in a friendly way, the same information as the last branch of the _tree viewpoint_.

<figure>
    <img src="figures/vis_globalview.png" align="center">
</figure>
<br/>

* Two diagram representations associated with each _schema version_.

* A diagram representation associated to each **Entity**, as well as each element associated, in some way, with the **EntityVersions** of that **Entity**.

<figure>
    <img src="figures/vis_entityview.png" align="center">
</figure>
<br/>

The projects involved in this visualization tool are the following ones:

* `es.um.nosql.schemainference.design`: The Sirius workbench project in which the viewpoints are defined.
* `es.um.nosql.schemainference.visualization.sdk`: The feature project in which the Sirius visualization plugin is stored.

**However**: Please keep in mind this section was developed a year ago from a M.Sc.Thesis (found [here](https://github.com/Soltari/NoSQLVisualizationTools/)) and since then several changes have been performed on the inference process. Because of that we have been wanting to redefine all the viewpoints available, since we now think the available viewpoints are not fully efficient on showing the information we want. For example, the viewpoints do not satisfy us when we use as an input really big models (hundreds of **EntityVersions**). That's why we will be redefining the implemented viewpoints and you should expect drastic changes on the viewpoints available soon(**tm**). 

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