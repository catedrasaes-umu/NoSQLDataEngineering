CREATE KEYSPACE IF NOT EXISTS Cass_OneOfEach_Entities WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
USE Cass_OneOfEach_Entities ;

CREATE TABLE IF NOT EXISTS EntityAdded1
(
  id uuid,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EntityAdded2
(
  id uuid,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EntityAdded3
(
  id uuid,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS EntityAdded2;

COPY EntityAdded3 TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityAdded3;
CREATE TABLE EntityRenamed
(
  id uuid,
  PRIMARY KEY (id)
);
COPY EntityRenamed FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TABLE IF NOT EXISTS EntityToExtract
(
  id text,
  feat1 text,
  feat2 text,
  feat3 text,
  feat4 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityToExtract (id, feat1, feat2, feat3, feat4) VALUES ('id', 'feat1', 'feat2', 'feat3', 'feat4');

COPY EntityToExtract ( id, feat2, feat4 ) TO 'tmp.csv' WITH HEADER = TRUE;
CREATE TABLE EntityExtracted
(
  id text,
  feat2 text,
  feat4 text,
  PRIMARY KEY (id)
);
COPY EntityExtracted FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TABLE IF NOT EXISTS EntityToSplit
(
  id text,
  attr1 text,
  attr2 text,
  attr3 text,
  attr4 text,
  attr5 text,
  attr6 text,
  PRIMARY KEY (id)
);
INSERT INTO EntityToSplit (id, attr1, attr2, attr3, attr4, attr5, attr6) VALUES ('id', 'attr1', 'attr2', 'attr3', 'attr4', 'attr5', 'attr6');

COPY EntityToSplit ( id, attr1, attr2, attr3 ) TO 'tmp.csv' WITH HEADER = TRUE;
CREATE TABLE EntitySplit1
(
  id text,
  attr1 text,
  attr2 text,
  attr3 text,
  PRIMARY KEY (id)
);
COPY EntitySplit1 FROM 'tmp.csv' WITH HEADER = TRUE;
COPY EntityToSplit ( id, attr1, attr5, attr6 )  TO 'tmp.csv' WITH HEADER = TRUE;
CREATE TABLE EntitySplit2
(
  id text,
  attr1 text,
  attr5 text,
  attr6 text,
  PRIMARY KEY (id)
);
COPY EntitySplit2 FROM 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToSplit;

CREATE TABLE IF NOT EXISTS EntityToMerge1
(
  id text,
  a text,
  b varint,
  PRIMARY KEY (id)
);
INSERT INTO EntityToMerge1 (id, a, b) VALUES ('id', 'a', 33);

CREATE TABLE IF NOT EXISTS EntityToMerge2
(
  id text,
  c list<text>,
  b text,
  PRIMARY KEY (id)
);
INSERT INTO EntityToMerge2 (id, b) VALUES ('id', 'b');

CREATE TABLE EntityMerged
(
  id text,
  a text,
  b varint,
  c list<text>,
  PRIMARY KEY (id)
);
COPY EntityToMerge2 TO 'tmp.csv' WITH HEADER = TRUE;
COPY EntityMerged ( id, c, b ) FROM 'tmp.csv' WITH HEADER = TRUE;
COPY EntityToMerge1 TO 'tmp.csv' WITH HEADER = TRUE;
COPY EntityMerged ( id, a, b ) FROM 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToMerge2;
DROP TABLE EntityToMerge1;
