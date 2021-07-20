CREATE SCHEMA Social_Network;
USE Social_Network;

CREATE TABLE UserData
(
  address VARCHAR(255) NOT NULL,
  name VARCHAR(255) CHECK ( name LIKE '/^[A-Z][a-z]*$/' ) NOT NULL,
  about_me VARCHAR(255),
  id INTEGER NOT NULL,
  PRIMARY KEY ( id )
);

CREATE TABLE Social
(
  views INTEGER,
  reactions INTEGER,
  shares NUMERIC,
  id INTEGER NOT NULL,
  PRIMARY KEY ( id )
);

CREATE TABLE Comment
(
  id VARCHAR(255) NOT NULL,
  message VARCHAR(255) NOT NULL,
  post_id VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  created_time TIMESTAMP NOT NULL,
  last_activity_date TIMESTAMP NOT NULL,
  upvotes INTEGER CHECK ( upvotes BETWEEN 0 AND 1000 ) NOT NULL,
  downvotes INTEGER CHECK ( downvotes BETWEEN 0 AND 1000 ) NOT NULL,
  FOREIGN KEY ( post_id ) REFERENCES Post ( id ),
  FOREIGN KEY ( user_id ) REFERENCES User ( id ),
  PRIMARY KEY ( id )
);

CREATE TABLE Post
(
  id VARCHAR(255) NOT NULL,
  message VARCHAR(255) NOT NULL,
  social INTEGER NOT NULL,
  title VARCHAR(255) NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  created_time TIMESTAMP NOT NULL,
  last_activity_date TIMESTAMP NOT NULL,
  upvotes INTEGER CHECK ( upvotes BETWEEN 0 AND 1000 ) NOT NULL,
  downvotes INTEGER CHECK ( downvotes BETWEEN 0 AND 1000 ) NOT NULL,
  FOREIGN KEY ( user_id ) REFERENCES User ( id ),
  FOREIGN KEY ( social ) REFERENCES Social ( id ),
  PRIMARY KEY ( id )
);

CREATE TABLE User
(
  id VARCHAR(255) NOT NULL,
  email VARCHAR(255) CHECK ( email LIKE '/^.+@.+\\.com$/' ) NOT NULL,
  type ENUM ( 'Guest', 'User', 'Admin' ) NOT NULL,
  user_data INTEGER NOT NULL,
  is_active BOOLEAN,
  suspended_acc INTEGER,
  UNIQUE KEY ( email ),
  FOREIGN KEY ( user_data ) REFERENCES UserData ( id ),
  PRIMARY KEY ( id )
);

CREATE TABLE Tags
(
  id INTEGER NOT NULL,
  tags VARCHAR(255) NOT NULL,
  PRIMARY KEY ( id )
);

CREATE TABLE Post_Tags
(
  id1 VARCHAR(255) NOT NULL,
  id2 INTEGER NOT NULL,
  FOREIGN KEY ( id1 ) REFERENCES Post ( id ),
  FOREIGN KEY ( id2 ) REFERENCES Tags ( id ),
  PRIMARY KEY ( id1, id2 )
);

