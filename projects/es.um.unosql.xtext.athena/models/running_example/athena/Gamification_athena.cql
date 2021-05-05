CREATE KEYSPACE IF NOT EXISTS Gamification_athena WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 3 } ;
USE Gamification_athena ;

CREATE TYPE IF NOT EXISTS PersonalInfo
(
  city text,
  name text,
  number int,
  street text,
  postcode int
);

CREATE TYPE IF NOT EXISTS MinigameSummary
(
  gameId uuid,
  scheduledAt timestamp,
  completedAt timestamp,
  points int
);

CREATE TABLE IF NOT EXISTS Stage
(
  id uuid,
  category text,
  description text,
  name text,
  createdAt timestamp,
  updatedAt timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Minigame
(
  id uuid,
  categories list<text>,
  isActive boolean,
  name text,
  points int,
  stageIds list<uuid>,
  createdAt timestamp,
  updatedAt timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS User
(
  id uuid,
  avatarUrl text,
  email text,
  personalInfo PersonalInfo,
  games list<frozen<MinigameSummary>>,
  points int,
  PRIMARY KEY (id)
);

