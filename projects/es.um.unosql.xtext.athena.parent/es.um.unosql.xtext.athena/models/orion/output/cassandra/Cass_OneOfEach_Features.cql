CREATE KEYSPACE IF NOT EXISTS Cass_OneOfEach_Features WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
USE Cass_OneOfEach_Features ;

CREATE TABLE IF NOT EXISTS EntityToRemove
(
  id uuid,
  attrToDel text,
  attrToDelStr text,
  attrToDelNum varint,
  attrToDelDouble double,
  attrToDelBool boolean,
  attrToRen list<text>,
  attrToRenStr text,
  attrToRenNum varint,
  attrToRenDouble double,
  attrToRenBool boolean,
  PRIMARY KEY (id)
);

ALTER TABLE EntityToRemove DROP ( attrToDel );

ALTER TABLE EntityToRemove DROP ( attrToDelStr );

ALTER TABLE EntityToRemove DROP ( attrToDelNum );

ALTER TABLE EntityToRemove DROP ( attrToDelDouble );

ALTER TABLE EntityToRemove DROP ( attrToDelBool );

COPY EntityToRemove ( id, attrToRen ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToRemove DROP ( attrToRen );
ALTER TABLE EntityToRemove ADD attrTrue1 list<text>;
COPY EntityToRemove ( id, attrTrue1 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToRemove ( id, attrToRenStr ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToRemove DROP ( attrToRenStr );
ALTER TABLE EntityToRemove ADD attrTrue2 text;
COPY EntityToRemove ( id, attrTrue2 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToRemove ( id, attrToRenNum ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToRemove DROP ( attrToRenNum );
ALTER TABLE EntityToRemove ADD attrTrue3 varint;
COPY EntityToRemove ( id, attrTrue3 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToRemove ( id, attrToRenDouble ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToRemove DROP ( attrToRenDouble );
ALTER TABLE EntityToRemove ADD attrTrue4 double;
COPY EntityToRemove ( id, attrTrue4 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToRemove ( id, attrToRenBool ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToRemove DROP ( attrToRenBool );
ALTER TABLE EntityToRemove ADD attrTrue5 boolean;
COPY EntityToRemove ( id, attrTrue5 ) FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TABLE IF NOT EXISTS EntityToCopySource1
(
  id text,
  aRef text,
  valueToCopy1 text,
  valueToCopy2 list<text>,
  valueToCopy3 map<text, boolean>,
  PRIMARY KEY (id)
);
INSERT INTO EntityToCopySource1 (id, aRef, valueToCopy1) VALUES ('id1', 'idRef', 'COPY_THIS');

CREATE TABLE IF NOT EXISTS EntityToCopySource2
(
  id text,
  aRef text,
  valueToCopy4 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityToCopySource2 (id, aRef, valueToCopy4) VALUES ('id2', 'idRef', 'COPY_THAT');

CREATE TABLE IF NOT EXISTS EntityToCopyTarget
(
  id text,
  PRIMARY KEY (id)
);
INSERT INTO EntityToCopyTarget (id) VALUES ('idRef');

COPY EntityToCopySource1 ( aRef, valueToCopy1 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToCopyTarget ADD copy1 text;
COPY EntityToCopyTarget ( id, copy1 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToCopySource1 ( aRef, valueToCopy2 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToCopyTarget ADD copy2 list<text>;
COPY EntityToCopyTarget ( id, copy2 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToCopySource1 ( aRef, valueToCopy3 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToCopyTarget ADD copy3 map<text, boolean>;
COPY EntityToCopyTarget ( id, copy3 ) FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToCopySource2 ( aRef, valueToCopy4 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToCopyTarget ADD valueToCopy4 text;
COPY EntityToCopyTarget ( id, valueToCopy4 ) FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TABLE IF NOT EXISTS EntityToMoveSource1
(
  id text,
  aRef text,
  valueToMove1 text,
  valueToMove2 list<text>,
  valueToMove3 map<text, boolean>,
  PRIMARY KEY (id)
);
INSERT INTO EntityToMoveSource1 (id, aRef, valueToMove1) VALUES ('id1', 'idRef', 'MOVE_THIS');

CREATE TABLE IF NOT EXISTS EntityToMoveSource2
(
  id text,
  aRef text,
  valueToMove4 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityToMoveSource2 (id, aRef, valueToMove4) VALUES ('id2', 'idRef', 'MOVE_THAT');

CREATE TABLE IF NOT EXISTS EntityToMoveTarget
(
  id text,
  PRIMARY KEY (id)
);

COPY EntityToMoveSource1 ( aRef, valueToMove1 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveTarget ADD move1 text;
COPY EntityToMoveTarget ( id, move1 ) FROM 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveSource1 DROP ( valueToMove1 );

COPY EntityToMoveSource1 ( aRef, valueToMove2 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveTarget ADD move2 list<text>;
COPY EntityToMoveTarget ( id, move2 ) FROM 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveSource1 DROP ( valueToMove2 );

COPY EntityToMoveSource1 ( aRef, valueToMove3 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveTarget ADD move3 map<text, boolean>;
COPY EntityToMoveTarget ( id, move3 ) FROM 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveSource1 DROP ( valueToMove3 );

COPY EntityToMoveSource2 ( aRef, valueToMove4 ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveTarget ADD move4 text;
COPY EntityToMoveTarget ( id, move4 ) FROM 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE EntityToMoveSource2 DROP ( valueToMove4 );

CREATE TABLE IF NOT EXISTS EntityToNest
(
  id text,
  name text,
  age int,
  street text,
  postalCode text,
  city text,
  aList list<text>,
  aMap map<text, boolean>,
  PRIMARY KEY (id)
);

CREATE TYPE IF NOT EXISTS NestAggrNest1
(
  nest1 double, 
  nest2 text, 
  nest3 boolean
);
ALTER TABLE EntityToNest ADD nestAggrNest1 NestAggrNest1;

CREATE TYPE IF NOT EXISTS NestAggrNest2
(
  nest4 double, 
  nest5 text, 
  nest6 boolean
);
ALTER TABLE EntityToNest ADD nestAggrNest2 list<frozen<NestAggrNest2>>;

CREATE TYPE IF NOT EXISTS NestedAggr
(
  postalCode text, 
  city text, 
  nestAggrNest1 frozen<NestAggrNest1>, 
  nestAggrNest2 frozen<list<frozen<NestAggrNest2>>>
);
ALTER TABLE EntityToNest ADD nestedAggr NestedAggr;
ALTER TABLE EntityToNest DROP ( postalCode, city, nestAggrNest1, nestAggrNest2 );

CREATE TABLE IF NOT EXISTS EntityToUnnest
(
  id text,
  name text,
  age int,
  PRIMARY KEY (id)
);

CREATE TYPE IF NOT EXISTS UnnestAggrNest1
(
  nest1 double, 
  nest2 text, 
  nest3 boolean, 
  street text, 
  postalCode text, 
  city text, 
  aList frozen<list<text>>, 
  aMap frozen<map<text, boolean>>
);
ALTER TABLE EntityToUnnest ADD unnestAggrNest1 UnnestAggrNest1;

CREATE TYPE IF NOT EXISTS UnnestAggrNest2
(
  nest4 double, 
  nest5 text, 
  nest6 boolean
);
ALTER TABLE EntityToUnnest ADD unnestAggrNest2 UnnestAggrNest2;

ALTER TABLE EntityToUnnest ADD
(
  street text, 
  postalCode text, 
  city text, 
  aList list<text>, 
  aMap map<text, boolean>, 
  nest4 double
);
