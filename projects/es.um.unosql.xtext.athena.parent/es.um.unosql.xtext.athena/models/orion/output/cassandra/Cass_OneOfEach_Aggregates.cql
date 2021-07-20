CREATE KEYSPACE IF NOT EXISTS Cass_OneOfEach_Aggregates WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
USE Cass_OneOfEach_Aggregates ;

CREATE TABLE IF NOT EXISTS EntityToAggr
(
  id uuid,
  PRIMARY KEY (id)
);

CREATE TYPE IF NOT EXISTS AggrSimple
(
  attr1 text, 
  attr2 varint, 
  attr3 frozen<map<text, int>>, 
  attr4 frozen<list<int>>
);
ALTER TABLE EntityToAggr ADD aggrSimple AggrSimple;

CREATE TYPE IF NOT EXISTS AggrList
(
  attr1 text, 
  attr2 varint, 
  attr3 frozen<map<text, int>>, 
  attr4 frozen<list<int>>
);
ALTER TABLE EntityToAggr ADD aggrList list<frozen<AggrList>>;

CREATE TYPE IF NOT EXISTS InnerAggrSimple
(
  innerAttr1 text, 
  innerAttr2 varint, 
  innerAttr3 frozen<map<text, boolean>>, 
  innerAttr4 frozen<list<text>>
);
ALTER TYPE AggrSimple ADD innerAggrSimple frozen<InnerAggrSimple>;

CREATE TYPE IF NOT EXISTS InnerAggrList
(
  innerAttr1 text, 
  innerAttr2 varint, 
  innerAttr3 frozen<map<text, text>>, 
  innerAttr4 frozen<list<text>>
);
ALTER TYPE AggrSimple ADD innerAggrList frozen<list<InnerAggrList>>;

CREATE TYPE IF NOT EXISTS StrAggrSimpleChange
(
  attr1 text, 
  attr2 int
);
ALTER TABLE EntityToAggr ADD strAggrSimpleChange StrAggrSimpleChange;

CREATE TYPE IF NOT EXISTS StrAggrArrayChange
(
  attr1 text, 
  attr2 int
);
ALTER TABLE EntityToAggr ADD strAggrArrayChange list<frozen<StrAggrArrayChange>>;

CREATE TYPE IF NOT EXISTS NumAggrSimpleChange
(
  attr1 varint, 
  attr2 int
);
ALTER TABLE EntityToAggr ADD numAggrSimpleChange NumAggrSimpleChange;

CREATE TYPE IF NOT EXISTS NumAggrArrayChange
(
  attr1 varint, 
  attr2 int
);
ALTER TABLE EntityToAggr ADD numAggrArrayChange list<frozen<NumAggrArrayChange>>;

COPY EntityToAggr TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToAggr;
CREATE TABLE EntityToAggr
(
  id uuid,
  aggrSimple AggrSimple,
  aggrList list<frozen<AggrList>>,
  strAggrArrayChange list<frozen<StrAggrArrayChange>>,
  numAggrSimpleChange NumAggrSimpleChange,
  numAggrArrayChange list<frozen<NumAggrArrayChange>>,
  strAggrSimpleChange list<frozen<StrAggrSimpleChange>>,
  PRIMARY KEY (id)
);
COPY EntityToAggr FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToAggr TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToAggr;
CREATE TABLE EntityToAggr
(
  id uuid,
  aggrSimple AggrSimple,
  aggrList list<frozen<AggrList>>,
  strAggrSimpleChange list<frozen<StrAggrSimpleChange>>,
  numAggrSimpleChange NumAggrSimpleChange,
  numAggrArrayChange list<frozen<NumAggrArrayChange>>,
  strAggrArrayChange StrAggrArrayChange,
  PRIMARY KEY (id)
);
COPY EntityToAggr FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToAggr TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToAggr;
CREATE TABLE EntityToAggr
(
  id uuid,
  aggrSimple AggrSimple,
  aggrList list<frozen<AggrList>>,
  strAggrSimpleChange list<frozen<StrAggrSimpleChange>>,
  strAggrArrayChange StrAggrArrayChange,
  numAggrArrayChange list<frozen<NumAggrArrayChange>>,
  numAggrSimpleChange list<frozen<NumAggrSimpleChange>>,
  PRIMARY KEY (id)
);
COPY EntityToAggr FROM 'tmp.csv' WITH HEADER = TRUE;

COPY EntityToAggr TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToAggr;
CREATE TABLE EntityToAggr
(
  id uuid,
  aggrSimple AggrSimple,
  aggrList list<frozen<AggrList>>,
  strAggrSimpleChange list<frozen<StrAggrSimpleChange>>,
  strAggrArrayChange StrAggrArrayChange,
  numAggrSimpleChange list<frozen<NumAggrSimpleChange>>,
  numAggrArrayChange NumAggrArrayChange,
  PRIMARY KEY (id)
);
COPY EntityToAggr FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TYPE IF NOT EXISTS AggrByHand1
(
  string1 text
);
ALTER TABLE EntityToAggr ADD aggrByHand1 AggrByHand1;

ALTER TYPE AggrByHand1 ADD nullAttrArrayChange frozen<list<int>>;

ALTER TYPE AggrByHand1 ADD strAttrSimpleChange text;

ALTER TYPE AggrByHand1 ADD strAttrArrayChange frozen<list<text>>;

CREATE TYPE IF NOT EXISTS AggrByHand2
(
  string2 text
);
ALTER TABLE EntityToAggr ADD aggrByHand2 AggrByHand2;

ALTER TYPE AggrByHand2 ADD numAttrSimpleChange varint;

ALTER TYPE AggrByHand2 ADD numAttrArrayChange frozen<list<varint>>;

COPY EntityToAggr TO 'tmp.csv' WITH HEADER = TRUE;
DROP TABLE EntityToAggr;
CREATE TABLE EntityToAggr
(
  id uuid,
  aggrSimple AggrSimple,
  aggrList list<frozen<AggrList>>,
  strAggrSimpleChange list<frozen<StrAggrSimpleChange>>,
  strAggrArrayChange StrAggrArrayChange,
  numAggrSimpleChange list<frozen<NumAggrSimpleChange>>,
  numAggrArrayChange NumAggrArrayChange,
  aggrByHand2 AggrByHand2,
  aggrByHand1 list<frozen<AggrByHand1>>,
  PRIMARY KEY (id)
);
COPY EntityToAggr FROM 'tmp.csv' WITH HEADER = TRUE;

CREATE TABLE IF NOT EXISTS EntityMorphAggr
(
  id text,
  PRIMARY KEY (id)
);

CREATE TYPE IF NOT EXISTS Aggr1
(
  string1 text, 
  string2 text
);
ALTER TABLE EntityMorphAggr ADD aggr1 Aggr1;

CREATE TYPE IF NOT EXISTS Aggr2
(
  aList frozen<list<int>>, 
  aMap frozen<map<text, int>>
);
ALTER TABLE EntityMorphAggr ADD aggr2 Aggr2;

CREATE TYPE IF NOT EXISTS Aggr3
(
  string5 text, 
  string6 text
);
ALTER TABLE EntityMorphAggr ADD aggr3 list<frozen<Aggr3>>;

CREATE TYPE IF NOT EXISTS Aggr4
(
  aSet frozen<set<int>>, 
  aTuple frozen<tuple<text>>
);
ALTER TABLE EntityMorphAggr ADD aggr4 list<frozen<Aggr4>>;

CREATE TABLE IF NOT EXISTS Aggr1
(
  id uuid,
  string1 text,
  string2 text,
  PRIMARY KEY (id)
);
ALTER TABLE EntityMorphAggr ADD refToAggr1 uuid;

CREATE TABLE IF NOT EXISTS Aggr2
(
  id uuid,
  aList list<int>,
  aMap map<text, int>,
  PRIMARY KEY (id)
);
ALTER TABLE EntityMorphAggr ADD refToAggr2 uuid;

CREATE TABLE IF NOT EXISTS Aggr3
(
  id uuid,
  string5 text,
  string6 text,
  PRIMARY KEY (id)
);
ALTER TABLE EntityMorphAggr ADD refToAggr3 list<uuid>;

CREATE TABLE IF NOT EXISTS Aggr4
(
  id uuid,
  aSet set<int>,
  aTuple tuple<text>,
  PRIMARY KEY (id)
);
ALTER TABLE EntityMorphAggr ADD refToAggr4 list<uuid>;
