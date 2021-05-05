CREATE SCHEMA SQLExample_Sakila;
USE SQLExample_Sakila;

CREATE TABLE Actor
(
  actor_id INTEGER NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  PRIMARY KEY ( actor_id )
);

CREATE TABLE Language
(
  language_id BOOLEAN NOT NULL,
  name VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  PRIMARY KEY ( language_id )
);

CREATE TABLE Film
(
  film_id INTEGER NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  release_year TIMESTAMP NOT NULL,
  language_id BOOLEAN NOT NULL,
  original_language_id BOOLEAN NOT NULL,
  rental_duration BOOLEAN NOT NULL,
  rental_rate NUMERIC NOT NULL,
  length INTEGER NOT NULL,
  replacement_cost NUMERIC NOT NULL,
  rating VARCHAR(255) NOT NULL,
  special_features VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( language_id ) REFERENCES Language ( language_id ),
  FOREIGN KEY ( original_language_id ) REFERENCES Language ( language_id ),
  PRIMARY KEY ( film_id )
);

CREATE TABLE Film_actor
(
  actor_id INTEGER NOT NULL,
  film_id INTEGER NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( actor_id ) REFERENCES Actor ( actor_id ),
  FOREIGN KEY ( film_id ) REFERENCES Film ( film_id ),
  PRIMARY KEY ( actor_id, film_id )
);

CREATE TABLE Category
(
  category_id BOOLEAN NOT NULL,
  name VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  PRIMARY KEY ( category_id )
);

CREATE TABLE Film_category
(
  film_id INTEGER NOT NULL,
  category_id BOOLEAN NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( film_id ) REFERENCES Film ( film_id ),
  FOREIGN KEY ( category_id ) REFERENCES Category ( category_id ),
  PRIMARY KEY ( film_id, category_id )
);

CREATE TABLE Film_text
(
  film_id INTEGER NOT NULL,
  title VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  PRIMARY KEY ( film_id )
);

CREATE TABLE Inventory
(
  inventory_id INTEGER NOT NULL,
  film_id INTEGER NOT NULL,
  store_id BOOLEAN NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( film_id ) REFERENCES Film ( film_id ),
  FOREIGN KEY ( store_id ) REFERENCES Store ( store_id ),
  PRIMARY KEY ( inventory_id )
);

CREATE TABLE Staff
(
  staff_id BOOLEAN NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  address_id INTEGER NOT NULL,
  picture VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  store_id BOOLEAN NOT NULL,
  active BOOLEAN NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( address_id ) REFERENCES Address ( address_id ),
  FOREIGN KEY ( store_id ) REFERENCES Store ( store_id ),
  PRIMARY KEY ( staff_id )
);

CREATE TABLE Store
(
  store_id BOOLEAN NOT NULL,
  manager_staff_id BOOLEAN NOT NULL,
  address_id INTEGER NOT NULL,
  last_update TIMESTAMP NOT NULL,
  UNIQUE KEY ( manager_staff_id ),
  FOREIGN KEY ( manager_staff_id ) REFERENCES Staff ( staff_id ),
  FOREIGN KEY ( address_id ) REFERENCES Address ( address_id ),
  PRIMARY KEY ( store_id )
);

CREATE TABLE Customer
(
  customer_id INTEGER NOT NULL,
  store_id BOOLEAN NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  address_id INTEGER NOT NULL,
  active BOOLEAN NOT NULL,
  create_date TIMESTAMP NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( store_id ) REFERENCES Store ( store_id ),
  FOREIGN KEY ( address_id ) REFERENCES Address ( address_id ),
  PRIMARY KEY ( customer_id )
);

CREATE TABLE Rental
(
  rental_id INTEGER NOT NULL,
  rental_date TIMESTAMP NOT NULL,
  inventory_id INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  return_date TIMESTAMP,
  staff_id BOOLEAN NOT NULL,
  last_update TIMESTAMP NOT NULL,
  UNIQUE KEY ( rental_date ),
  UNIQUE KEY ( inventory_id ),
  UNIQUE KEY ( customer_id ),
  FOREIGN KEY ( inventory_id ) REFERENCES Inventory ( inventory_id ),
  FOREIGN KEY ( customer_id ) REFERENCES Customer ( customer_id ),
  FOREIGN KEY ( staff_id ) REFERENCES Staff ( staff_id ),
  PRIMARY KEY ( rental_id )
);

CREATE TABLE Payment
(
  payment_id INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  staff_id BOOLEAN NOT NULL,
  rental_id INTEGER NOT NULL,
  amount NUMERIC NOT NULL,
  payment_date TIMESTAMP NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( customer_id ) REFERENCES Customer ( customer_id ),
  FOREIGN KEY ( staff_id ) REFERENCES Staff ( staff_id ),
  FOREIGN KEY ( rental_id ) REFERENCES Rental ( rental_id ),
  PRIMARY KEY ( payment_id )
);

CREATE TABLE Address
(
  address_id INTEGER NOT NULL,
  address VARCHAR(255) NOT NULL,
  address2 VARCHAR(255) NOT NULL,
  district VARCHAR(255) NOT NULL,
  city_id INTEGER NOT NULL,
  postal_code VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( city_id ) REFERENCES City ( city_id ),
  PRIMARY KEY ( address_id )
);

CREATE TABLE City
(
  city_id INTEGER NOT NULL,
  city VARCHAR(255) NOT NULL,
  country_id INTEGER NOT NULL,
  last_update TIMESTAMP NOT NULL,
  FOREIGN KEY ( country_id ) REFERENCES Country ( country_id ),
  PRIMARY KEY ( city_id )
);

CREATE TABLE Country
(
  country_id INTEGER NOT NULL,
  country VARCHAR(255) NOT NULL,
  last_update TIMESTAMP NOT NULL,
  PRIMARY KEY ( country_id )
);

