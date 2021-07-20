CREATE SCHEMA Campaigns;
USE Campaigns;

CREATE TABLE CampaignSummary
(
  campaignId VARCHAR(255) NOT NULL,
  scheduledAt TIMESTAMP NOT NULL,
  points INTEGER,
  completedAt TIMESTAMP,
  campaigns VARCHAR(255) NOT NULL,
  id INTEGER NOT NULL,
  FOREIGN KEY ( campaignId ) REFERENCES Campaign ( id ),
  FOREIGN KEY ( campaigns ) REFERENCES User ( id ),
  PRIMARY KEY ( id )
);

CREATE TABLE Stage
(
  id VARCHAR(255) NOT NULL,
  v INTEGER NOT NULL,
  imageUrl VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  createdAt TIMESTAMP NOT NULL,
  updatedAt TIMESTAMP NOT NULL,
  categories JSON NOT NULL,
  PRIMARY KEY ( id )
);

CREATE TABLE Campaign
(
  id VARCHAR(255) NOT NULL,
  v INTEGER NOT NULL,
  active BOOLEAN NOT NULL,
  points INTEGER NOT NULL,
  imageUrl VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  qr VARCHAR(255) NOT NULL,
  category VARCHAR(255) NOT NULL,
  stageId VARCHAR(255) NOT NULL,
  createdAt TIMESTAMP NOT NULL,
  updatedAt TIMESTAMP NOT NULL,
  FOREIGN KEY ( stageId ) REFERENCES Stage ( id ),
  PRIMARY KEY ( id )
);

CREATE TABLE User
(
  id VARCHAR(255) NOT NULL,
  v INTEGER NOT NULL,
  firebaseTokens JSON NOT NULL,
  points INTEGER NOT NULL,
  avatarUrl VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  employeeNumber INTEGER NOT NULL,
  phoneNumber VARCHAR(255) NOT NULL,
  office VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  tokens JSON NOT NULL,
  createdAt TIMESTAMP NOT NULL,
  updatedAt TIMESTAMP NOT NULL,
  PRIMARY KEY ( id )
);

