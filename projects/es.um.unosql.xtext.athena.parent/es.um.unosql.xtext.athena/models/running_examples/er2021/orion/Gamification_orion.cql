CREATE KEYSPACE IF NOT EXISTS Gamification_orion WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor' : 3 } ;
USE Gamification_orion ;

CREATE TABLE IF NOT EXISTS Stage
(
  id uuid,
  category text,
  description text,
  name text,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Minigame
(
  id uuid,
  categories list<text>,
  isActive boolean,
  name text,
  points int,
  PRIMARY KEY (id)
);

ALTER TABLE Stage ADD createdAt timestamp;

ALTER TABLE Stage ADD updatedAt timestamp;

ALTER TABLE Minigame ADD createdAt timestamp;

ALTER TABLE Minigame ADD updatedAt timestamp;

ALTER TABLE Minigame ADD stageIds list<uuid>;

CREATE TABLE IF NOT EXISTS User
(
  id uuid,
  avatarUrl text,
  email text,
  points int,
  PRIMARY KEY (id)
);

CREATE TYPE IF NOT EXISTS PersonalInfo
(
  city text, 
  name text, 
  number int, 
  postcode int, 
  street text
);
ALTER TABLE User ADD personalInfo PersonalInfo;

CREATE TYPE IF NOT EXISTS MinigameSummary
(
  scheduledAt timestamp, 
  completedAt timestamp, 
  points int
);
ALTER TABLE User ADD minigameSummary list<frozen<MinigameSummary>>;

ALTER TYPE MinigameSummary ADD gameId uuid;

COPY User ( id, minigameSummary ) TO 'tmp.csv' WITH HEADER = TRUE;
ALTER TABLE User DROP ( minigameSummary );
ALTER TABLE User ADD games list<frozen<MinigameSummary>>;
COPY User ( id, games ) FROM 'tmp.csv' WITH HEADER = TRUE;
