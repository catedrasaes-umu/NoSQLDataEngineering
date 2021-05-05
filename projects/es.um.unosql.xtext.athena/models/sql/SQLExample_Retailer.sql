CREATE SCHEMA SQLExample_Retailer;
USE SQLExample_Retailer;

CREATE TABLE Offices
(
  officeCode VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  addressLine1 VARCHAR(255) NOT NULL,
  addressLine2 VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  postalCode VARCHAR(255) NOT NULL,
  territory VARCHAR(255) NOT NULL,
  PRIMARY KEY ( officeCode )
);

CREATE TABLE Employees
(
  employeeNumber INTEGER NOT NULL,
  lastName VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  extension VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  officeCode VARCHAR(255) NOT NULL,
  reportsTo INTEGER NOT NULL,
  jobTitle VARCHAR(255) NOT NULL,
  FOREIGN KEY ( officeCode ) REFERENCES Offices ( officeCode ),
  FOREIGN KEY ( reportsTo ) REFERENCES Employees ( employeeNumber ),
  PRIMARY KEY ( employeeNumber )
);

CREATE TABLE Customers
(
  customerNumber INTEGER NOT NULL,
  customerName VARCHAR(255) NOT NULL,
  contactLastName VARCHAR(255) NOT NULL,
  contactFirstName VARCHAR(255) NOT NULL,
  phone VARCHAR(255) NOT NULL,
  addressLine1 VARCHAR(255) NOT NULL,
  addressLine2 VARCHAR(255) NOT NULL,
  city VARCHAR(255) NOT NULL,
  state VARCHAR(255) NOT NULL,
  postalCode VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  salesRepEmployeeNumber INTEGER NOT NULL,
  creditLimit INTEGER NOT NULL,
  FOREIGN KEY ( salesRepEmployeeNumber ) REFERENCES Employees ( employeeNumber ),
  PRIMARY KEY ( customerNumber )
);

CREATE TABLE Products
(
  productCode VARCHAR(255) NOT NULL,
  productName VARCHAR(255) NOT NULL,
  productLine VARCHAR(255) NOT NULL,
  productScale VARCHAR(255) NOT NULL,
  productVendor VARCHAR(255) NOT NULL,
  productDescription VARCHAR(255) NOT NULL,
  quantityInStock INTEGER NOT NULL,
  buyPrice NUMERIC NOT NULL,
  MSRP NUMERIC NOT NULL,
  PRIMARY KEY ( productCode )
);

CREATE TABLE ProductLines
(
  productLine VARCHAR(255) NOT NULL,
  textDescription VARCHAR(255) NOT NULL,
  htmlDescription VARCHAR(255) NOT NULL,
  image VARCHAR(255) NOT NULL,
  FOREIGN KEY ( productLine ) REFERENCES Products ( productCode ),
  PRIMARY KEY ( productLine )
);

CREATE TABLE Orders
(
  orderNumber INTEGER NOT NULL,
  orderDate TIMESTAMP NOT NULL,
  requiredDate TIMESTAMP NOT NULL,
  shippedDate TIMESTAMP NOT NULL,
  status VARCHAR(255) NOT NULL,
  comments VARCHAR(255) NOT NULL,
  customerNumber INTEGER NOT NULL,
  FOREIGN KEY ( customerNumber ) REFERENCES Customers ( customerNumber ),
  PRIMARY KEY ( orderNumber )
);

CREATE TABLE OrderDetails
(
  orderNumber INTEGER NOT NULL,
  productCode VARCHAR(255) NOT NULL,
  quantityOrdered INTEGER NOT NULL,
  priceEach NUMERIC NOT NULL,
  orderLineNumber BOOLEAN NOT NULL,
  FOREIGN KEY ( orderNumber ) REFERENCES Orders ( orderNumber ),
  FOREIGN KEY ( productCode ) REFERENCES Products ( productCode ),
  PRIMARY KEY ( orderNumber, productCode )
);

CREATE TABLE Payments
(
  customerNumber INTEGER NOT NULL,
  checkNumber VARCHAR(255) NOT NULL,
  paymentDate TIMESTAMP NOT NULL,
  amount NUMERIC NOT NULL,
  FOREIGN KEY ( customerNumber ) REFERENCES Customers ( customerNumber ),
  PRIMARY KEY ( customerNumber, checkNumber )
);

