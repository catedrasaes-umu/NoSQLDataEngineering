CREATE KEYSPACE IF NOT EXISTS Social_Network WITH REPLICATION = { 'class': 'SimpleStrategy', 'replication_factor': 3 } ;
USE Social_Network ;

CREATE TYPE IF NOT EXISTS UserData
(
  address text,
  name text,
  about_me text
);

CREATE TYPE IF NOT EXISTS Social
(
  views int,
  reactions int,
  shares varint
);

CREATE TABLE IF NOT EXISTS Comment
(
  id text,
  message text,
  post_id text,
  user_id text,
  created_time timestamp,
  last_activity_date timestamp,
  upvotes int,
  downvotes int,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS Post
(
  id uuid,
  message text,
  social Social,
  title text,
  user_id uuid,
  tags list<text>,
  created_time timestamp,
  last_activity_date timestamp,
  upvotes int,
  downvotes int,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS User
(
  id uuid,
  email text,
  type text,
  user_data UserData,
  is_active boolean,
  suspended_acc int,
  PRIMARY KEY (id, email)
);

