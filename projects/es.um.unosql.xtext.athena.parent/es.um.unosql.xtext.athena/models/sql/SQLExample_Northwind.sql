CREATE SCHEMA SQLExample_Northwind;
USE SQLExample_Northwind;

CREATE TABLE Customers
(
  CustomerID VARCHAR(255) NOT NULL,
  CompanyName VARCHAR(255) NOT NULL,
  ContactName VARCHAR(255) NOT NULL,
  ContactTitle VARCHAR(255) NOT NULL,
  Address VARCHAR(255) NOT NULL,
  City VARCHAR(255) NOT NULL,
  Region VARCHAR(255) NOT NULL,
  PostalCode VARCHAR(255) NOT NULL,
  Country VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  Fax VARCHAR(255) NOT NULL,
  PRIMARY KEY ( CustomerID )
);

CREATE TABLE Employees
(
  EmployeeID INTEGER NOT NULL,
  LastName VARCHAR(255) NOT NULL,
  FirstName VARCHAR(255) NOT NULL,
  Title VARCHAR(255) NOT NULL,
  TitleOfCourtesy VARCHAR(255) NOT NULL,
  BirthDate TIMESTAMP NOT NULL,
  HireDate TIMESTAMP NOT NULL,
  Address VARCHAR(255) NOT NULL,
  City VARCHAR(255) NOT NULL,
  Region VARCHAR(255) NOT NULL,
  PostalCode VARCHAR(255) NOT NULL,
  Country VARCHAR(255) NOT NULL,
  HomePhone VARCHAR(255) NOT NULL,
  Extension VARCHAR(255) NOT NULL,
  Photo VARCHAR(255) NOT NULL,
  Notes VARCHAR(255) NOT NULL,
  ReportsTo INTEGER,
  PhotoPath VARCHAR(255) NOT NULL,
  Salary INTEGER NOT NULL,
  FOREIGN KEY ( ReportsTo ) REFERENCES Employees ( EmployeeID ),
  PRIMARY KEY ( EmployeeID )
);

CREATE TABLE Region
(
  RegionID BOOLEAN NOT NULL,
  RegionDescription VARCHAR(255) NOT NULL,
  PRIMARY KEY ( RegionID )
);

CREATE TABLE Territories
(
  TerritoryID VARCHAR(255) NOT NULL,
  TerritoryDescription VARCHAR(255) NOT NULL,
  RegionID BOOLEAN NOT NULL,
  FOREIGN KEY ( RegionID ) REFERENCES Region ( RegionID ),
  PRIMARY KEY ( TerritoryID )
);

CREATE TABLE EmployeeTerritories
(
  EmployeeID INTEGER NOT NULL,
  TerritoryID VARCHAR(255) NOT NULL,
  FOREIGN KEY ( EmployeeID ) REFERENCES Employees ( EmployeeID ),
  FOREIGN KEY ( TerritoryID ) REFERENCES Territories ( TerritoryID ),
  PRIMARY KEY ( EmployeeID, TerritoryID )
);

CREATE TABLE Categories
(
  CategoryID BOOLEAN NOT NULL,
  CategoryName VARCHAR(255) NOT NULL,
  Description VARCHAR(255) NOT NULL,
  Picture VARCHAR(255) NOT NULL,
  PRIMARY KEY ( CategoryID )
);

CREATE TABLE Suppliers
(
  SupplierID INTEGER NOT NULL,
  CompanyName VARCHAR(255) NOT NULL,
  ContactName VARCHAR(255) NOT NULL,
  ContactTitle VARCHAR(255) NOT NULL,
  Address VARCHAR(255) NOT NULL,
  City VARCHAR(255) NOT NULL,
  Region VARCHAR(255) NOT NULL,
  PostalCode VARCHAR(255) NOT NULL,
  Country VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  Fax VARCHAR(255) NOT NULL,
  HomePage VARCHAR(255) NOT NULL,
  PRIMARY KEY ( SupplierID )
);

CREATE TABLE Products
(
  ProductID INTEGER NOT NULL,
  ProductName VARCHAR(255) NOT NULL,
  SupplierID INTEGER NOT NULL,
  CategoryID BOOLEAN NOT NULL,
  QuantityPerUnit VARCHAR(255) NOT NULL,
  UnitPrice NUMERIC NOT NULL,
  UnitsInStock INTEGER NOT NULL,
  UnitsOnOrder INTEGER NOT NULL,
  ReorderLevel INTEGER NOT NULL,
  Discontinued BOOLEAN NOT NULL,
  FOREIGN KEY ( SupplierID ) REFERENCES Suppliers ( SupplierID ),
  FOREIGN KEY ( CategoryID ) REFERENCES Categories ( CategoryID ),
  PRIMARY KEY ( ProductID )
);

CREATE TABLE Shippers
(
  ShipperID BOOLEAN NOT NULL,
  CompanyName VARCHAR(255) NOT NULL,
  Phone VARCHAR(255) NOT NULL,
  PRIMARY KEY ( ShipperID )
);

CREATE TABLE Orders
(
  OrderID INTEGER NOT NULL,
  CustomerID VARCHAR(255) NOT NULL,
  EmployeeID INTEGER NOT NULL,
  OrderDate TIMESTAMP NOT NULL,
  RequiredDate TIMESTAMP NOT NULL,
  ShippedDate TIMESTAMP NOT NULL,
  ShipVia BOOLEAN NOT NULL,
  Freight NUMERIC NOT NULL,
  ShipName VARCHAR(255) NOT NULL,
  ShipAddress VARCHAR(255) NOT NULL,
  ShipCity VARCHAR(255) NOT NULL,
  ShipRegion VARCHAR(255) NOT NULL,
  ShipPostalCode VARCHAR(255) NOT NULL,
  ShipCountry VARCHAR(255) NOT NULL,
  FOREIGN KEY ( CustomerID ) REFERENCES Customers ( CustomerID ),
  FOREIGN KEY ( EmployeeID ) REFERENCES Employees ( EmployeeID ),
  FOREIGN KEY ( ShipVia ) REFERENCES Shippers ( ShipperID ),
  PRIMARY KEY ( OrderID )
);

CREATE TABLE Order_Details
(
  OrderID INTEGER NOT NULL,
  ProductID INTEGER NOT NULL,
  UnitPrice NUMERIC NOT NULL,
  Quantity INTEGER NOT NULL,
  Discount DOUBLE NOT NULL,
  FOREIGN KEY ( OrderID ) REFERENCES Orders ( OrderID ),
  FOREIGN KEY ( ProductID ) REFERENCES Products ( ProductID ),
  PRIMARY KEY ( OrderID, ProductID )
);

CREATE TABLE CustomerDemographics
(
  CustomerTypeID VARCHAR(255) NOT NULL,
  CustomerDesc VARCHAR(255) NOT NULL,
  PRIMARY KEY ( CustomerTypeID )
);

CREATE TABLE CustomerCustomerDemo
(
  CustomerID VARCHAR(255) NOT NULL,
  CustomerTypeID VARCHAR(255) NOT NULL,
  FOREIGN KEY ( CustomerID ) REFERENCES Customers ( CustomerID ),
  FOREIGN KEY ( CustomerTypeID ) REFERENCES CustomerDemographics ( CustomerTypeID ),
  PRIMARY KEY ( CustomerID, CustomerTypeID )
);

