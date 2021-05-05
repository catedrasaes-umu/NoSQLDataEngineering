CREATE KEYSPACE IF NOT EXISTS Cass_OneOfEach_References WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
USE Cass_OneOfEach_References ;

CREATE TABLE IF NOT EXISTS EntityDummy
(
  id text,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EntityRefs
(
  id uuid,
  PRIMARY KEY (id)
);

ALTER TABLE EntityRefs ADD nullRefSimple text;

ALTER TABLE EntityRefs ADD nullRefArray list<text>;

ALTER TABLE EntityRefs ADD strRefSimple text;

ALTER TABLE EntityRefs ADD strRefArray list<text>;

ALTER TABLE EntityRefs ADD numRefSimple varint;

ALTER TABLE EntityRefs ADD numRefArray list<varint>;

ALTER TABLE EntityRefs ADD nullRefSimpleChange text;

ALTER TABLE EntityRefs ADD nullRefArrayChange list<text>;

ALTER TABLE EntityRefs ADD strRefSimpleChange text;

ALTER TABLE EntityRefs ADD strRefArrayChange list<text>;

ALTER TABLE EntityRefs ADD numRefSimpleChange varint;

ALTER TABLE EntityRefs ADD numRefArrayChange list<varint>;

COPY EntityRefs TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityRefs;
CREATE TABLE EntityRefs
(
  id uuid,
  nullRefSimple text,
  nullRefArray list<text>,
  strRefSimple text,
  strRefArray list<text>,
  numRefSimple varint,
  numRefArray list<varint>,
  nullRefArrayChange list<text>,
  strRefSimpleChange text,
  strRefArrayChange list<text>,
  numRefSimpleChange varint,
  numRefArrayChange list<varint>,
  nullRefSimpleChange list<text>,
  PRIMARY KEY (id)
);
COPY EntityRefs FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityRefs TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityRefs;
CREATE TABLE EntityRefs
(
  id uuid,
  nullRefSimple text,
  nullRefArray list<text>,
  strRefSimple text,
  strRefArray list<text>,
  numRefSimple varint,
  numRefArray list<varint>,
  nullRefSimpleChange list<text>,
  strRefSimpleChange text,
  strRefArrayChange list<text>,
  numRefSimpleChange varint,
  numRefArrayChange list<varint>,
  nullRefArrayChange text,
  PRIMARY KEY (id)
);
COPY EntityRefs FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityRefs TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityRefs;
CREATE TABLE EntityRefs
(
  id uuid,
  nullRefSimple text,
  nullRefArray list<text>,
  strRefSimple text,
  strRefArray list<text>,
  numRefSimple varint,
  numRefArray list<varint>,
  nullRefSimpleChange list<text>,
  nullRefArrayChange text,
  strRefArrayChange list<text>,
  numRefSimpleChange varint,
  numRefArrayChange list<varint>,
  strRefSimpleChange list<text>,
  PRIMARY KEY (id)
);
COPY EntityRefs FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityRefs TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityRefs;
CREATE TABLE EntityRefs
(
  id uuid,
  nullRefSimple text,
  nullRefArray list<text>,
  strRefSimple text,
  strRefArray list<text>,
  numRefSimple varint,
  numRefArray list<varint>,
  nullRefSimpleChange list<text>,
  nullRefArrayChange text,
  strRefSimpleChange list<text>,
  numRefSimpleChange varint,
  numRefArrayChange list<varint>,
  strRefArrayChange text,
  PRIMARY KEY (id)
);
COPY EntityRefs FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityRefs TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityRefs;
CREATE TABLE EntityRefs
(
  id uuid,
  nullRefSimple text,
  nullRefArray list<text>,
  strRefSimple text,
  strRefArray list<text>,
  numRefSimple varint,
  numRefArray list<varint>,
  nullRefSimpleChange list<text>,
  nullRefArrayChange text,
  strRefSimpleChange list<text>,
  strRefArrayChange text,
  numRefArrayChange list<varint>,
  numRefSimpleChange list<varint>,
  PRIMARY KEY (id)
);
COPY EntityRefs FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityRefs TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityRefs;
CREATE TABLE EntityRefs
(
  id uuid,
  nullRefSimple text,
  nullRefArray list<text>,
  strRefSimple text,
  strRefArray list<text>,
  numRefSimple varint,
  numRefArray list<varint>,
  nullRefSimpleChange list<text>,
  nullRefArrayChange text,
  strRefSimpleChange list<text>,
  strRefArrayChange text,
  numRefSimpleChange list<varint>,
  numRefArrayChange varint,
  PRIMARY KEY (id)
);
COPY EntityRefs FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TABLE IF NOT EXISTS EntityRef1
(
  id text,
  value1 text,
  value2 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityRef1 (id, value1, value2) VALUES ('refId1', 'value1', 'value2');

CREATE TABLE IF NOT EXISTS EntityRef2
(
  id text,
  value3 text,
  value4 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityRef2 (id, value3, value4) VALUES ('refId2', 'value3', 'value4');

CREATE TABLE IF NOT EXISTS EntityRef3
(
  id text,
  value5 text,
  value6 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityRef3 (id, value5, value6) VALUES ('refId3', 'value6', 'value6');

CREATE TABLE IF NOT EXISTS EntityRef4
(
  id text,
  value7 text,
  value8 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityRef4 (id, value7, value8) VALUES ('refId4', 'value7', 'value8');

CREATE TABLE IF NOT EXISTS EntityMorphRef
(
  id text,
  PRIMARY KEY (id)
);

ALTER TABLE EntityMorphRef ADD refToEntity1 text;

ALTER TABLE EntityMorphRef ADD refToEntity2 text;

ALTER TABLE EntityMorphRef ADD refToEntity3 list<text>;

ALTER TABLE EntityMorphRef ADD refToEntity4 list<text>;

CREATE TYPE IF NOT EXISTS RefToEntity1
(
  id text,
  value1 text,
  value2 text,
);
ALTER TABLE EntityMorphRef ADD aggrEntity1 RefToEntity1;

CREATE TYPE IF NOT EXISTS RefToEntity2
(
  id text,
  value3 text,
  value4 text,
);
ALTER TABLE EntityMorphRef ADD aggrEntity2 RefToEntity2;
DROP TABLE IF EXISTS EntityRef2;

CREATE TYPE IF NOT EXISTS RefToEntity3
(
  id text,
  value5 text,
  value6 text,
);
ALTER TABLE EntityMorphRef ADD aggrEntity3 list<frozen<RefToEntity3>>;

CREATE TYPE IF NOT EXISTS RefToEntity4
(
  id text,
  value7 text,
  value8 text,
);
ALTER TABLE EntityMorphRef ADD aggrEntity4 list<frozen<RefToEntity4>>;
DROP TABLE IF EXISTS EntityRef4;
