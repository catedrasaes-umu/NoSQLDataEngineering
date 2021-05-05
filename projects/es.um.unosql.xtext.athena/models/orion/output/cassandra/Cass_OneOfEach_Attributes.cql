CREATE KEYSPACE IF NOT EXISTS Cass_OneOfEach_Attributes WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
USE Cass_OneOfEach_Attributes ;

CREATE TABLE IF NOT EXISTS EntityToAdd
(
  id text,
  PRIMARY KEY (id)
);

ALTER TABLE EntityToAdd ADD addAttrStr text;

ALTER TABLE EntityToAdd ADD addAttrNum varint;

ALTER TABLE EntityToAdd ADD addAttrDouble double;

ALTER TABLE EntityToAdd ADD addAttrBool boolean;

ALTER TABLE EntityToAdd ADD addAttrSet set<text>;

ALTER TABLE EntityToAdd ADD addAttrList list<text>;

ALTER TABLE EntityToAdd ADD addAttrTuple tuple<text,int>;

ALTER TABLE EntityToAdd ADD addAttrMap map<text, text>;

CREATE TABLE IF NOT EXISTS EntityToCast
(
  id uuid,
  attrToCastStr boolean,
  attrToCastNum text,
  attrToCastDouble varint,
  attrToCastBool double,
  PRIMARY KEY (id)
);

COPY EntityToCast TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToCast;
CREATE TABLE EntityToCast
(
  id uuid,
  attrToCastStr boolean,
  attrToCastNum text,
  attrToCastDouble varint,
  attrToCastBool boolean,
  PRIMARY KEY (id)
);
COPY EntityToCast FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToCast TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToCast;
CREATE TABLE EntityToCast
(
  id uuid,
  attrToCastStr boolean,
  attrToCastNum text,
  attrToCastBool boolean,
  attrToCastDouble double,
  PRIMARY KEY (id)
);
COPY EntityToCast FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToCast TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToCast;
CREATE TABLE EntityToCast
(
  id uuid,
  attrToCastStr boolean,
  attrToCastDouble double,
  attrToCastBool boolean,
  attrToCastNum varint,
  PRIMARY KEY (id)
);
COPY EntityToCast FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToCast TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToCast;
CREATE TABLE EntityToCast
(
  id uuid,
  attrToCastNum varint,
  attrToCastDouble double,
  attrToCastBool boolean,
  attrToCastStr text,
  PRIMARY KEY (id)
);
COPY EntityToCast FROM 'tmp.csv' WITH HEADER = TRUE;
