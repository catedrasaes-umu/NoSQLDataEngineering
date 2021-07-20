CREATE SCHEMA SQLExample_Employees;
USE SQLExample_Employees;

CREATE TABLE Employees
(
  emp_no INTEGER NOT NULL,
  birth_date TIMESTAMP NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  gender VARCHAR(255) NOT NULL,
  hire_date TIMESTAMP NOT NULL,
  PRIMARY KEY ( emp_no )
);

CREATE TABLE Departments
(
  dept_no VARCHAR(255) NOT NULL,
  dept_name VARCHAR(255) NOT NULL,
  UNIQUE KEY ( dept_name ),
  PRIMARY KEY ( dept_no )
);

CREATE TABLE Dept_emp
(
  emp_no INTEGER NOT NULL,
  dept_no VARCHAR(255) NOT NULL,
  from_date TIMESTAMP NOT NULL,
  to_date TIMESTAMP NOT NULL,
  FOREIGN KEY ( emp_no ) REFERENCES Employees ( emp_no ),
  FOREIGN KEY ( dept_no ) REFERENCES Departments ( dept_no ),
  PRIMARY KEY ( emp_no, dept_no )
);

CREATE TABLE Dept_manager
(
  dept_no VARCHAR(255) NOT NULL,
  emp_no INTEGER NOT NULL,
  from_date TIMESTAMP NOT NULL,
  to_date TIMESTAMP NOT NULL,
  FOREIGN KEY ( dept_no ) REFERENCES Departments ( dept_no ),
  FOREIGN KEY ( emp_no ) REFERENCES Employees ( emp_no ),
  PRIMARY KEY ( dept_no, emp_no )
);

CREATE TABLE Titles
(
  emp_no INTEGER NOT NULL,
  title VARCHAR(255) NOT NULL,
  from_date TIMESTAMP NOT NULL,
  to_date TIMESTAMP NOT NULL,
  FOREIGN KEY ( emp_no ) REFERENCES Employees ( emp_no ),
  PRIMARY KEY ( emp_no, title, from_date )
);

CREATE TABLE Salaries
(
  emp_no INTEGER NOT NULL,
  salary INTEGER NOT NULL,
  from_date TIMESTAMP NOT NULL,
  to_date TIMESTAMP NOT NULL,
  FOREIGN KEY ( emp_no ) REFERENCES Employees ( emp_no ),
  PRIMARY KEY ( emp_no, from_date )
);

