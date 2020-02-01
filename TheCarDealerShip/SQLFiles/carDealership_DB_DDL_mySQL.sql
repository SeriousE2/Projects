DROP DATABASE IF EXISTS carDealershipDB;

CREATE DATABASE carDealershipDB;

USE carDealershipDB;

CREATE TABLE personProfile (
	id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(30) NOT NULL,
    email VARCHAR(50),
    phone VARCHAR(16),
    streetAddress VARCHAR(50),
    zipcode VARCHAR(10)
);

CREATE TABLE carDealershipUser(
	id INT PRIMARY KEY AUTO_INCREMENT,
    profileId INT NOT NULL,
    userRole VARCHAR(8) NOT NULL,
    userPassword VARCHAR(45) NOT NULL,
    dateAdded TIMESTAMP NOT NULL,
    
	FOREIGN KEY (profileId) REFERENCES personProfile(id)
);

CREATE TABLE contact(
	id INT PRIMARY KEY AUTO_INCREMENT,
    profileId INT NOT NULL,
    message VARCHAR(225) NOT NULL,
    timePosted TIMESTAMP NOT NULL,
    
    FOREIGN KEY (profileId) REFERENCES personProfile(id)
);

CREATE TABLE make(
	id INT PRIMARY KEY AUTO_INCREMENT,
    makeName VARCHAR(30) NOT NULL,
    dateAdded TIMESTAMP NOT NULL,
    userId INT NOT NULL,
    
     FOREIGN KEY (userId) REFERENCES carDealershipUser(id)
);

CREATE TABLE model(
	id INT PRIMARY KEY AUTO_INCREMENT,
    makeId INT NOT NULL,
    modelName VARCHAR(45) NOT NULL,
    dateAdded TIMESTAMP NOT NULL,
    userId INT NOT NULL,
    
    FOREIGN KEY (makeId) REFERENCES make(id),
    FOREIGN KEY (userId) REFERENCES carDealershipUser(id)
);

CREATE TABLE vehicle(
	id INT PRIMARY KEY AUTO_INCREMENT,
    makeId INT NOT NULL,
    modelId INT NOT NULL,
    vehicleYear INT NOT NULL,
    mileage INT NOT NULL,
    vehicleType VARCHAR(14) NOT NULL,
    vin VARCHAR(45) NOT NULL,
    msrp DECIMAL(10,2) NOT NULL,
    listPrice DECIMAL(10,2) NOT NULL,
    exteriorColor VARCHAR(30) NOT NULL,
    interiorColor VARCHAR(30) NOT NULL,
    transmission VARCHAR(26),
    vehicleDescription TEXT,
    isAvailable BOOLEAN NOT NULL,
    bodyStyle VARCHAR(25) NOT NULL,
    image VARCHAR(75),
    isFeatured BOOLEAN NOT NULL,
    dateAdded TIMESTAMP NOT NULL,
    userId INT NOT NULL,
    
    FOREIGN KEY (modelId) REFERENCES model(id),
    FOREIGN KEY (makeId) REFERENCES make(id),
    FOREIGN KEY (userId) REFERENCES carDealershipUser(id)
);

CREATE TABLE special(
	id INT PRIMARY KEY AUTO_INCREMENT,
    vehicleId INT NOT NULL,
    title VARCHAR(25) NOT NULL,
    dateBegin DATE,
    dateEnd DATE ,
    specialDescription TEXT NOT NULL,
    dateAdded TIMESTAMP NOT NULL,
    userId INT NOT NULL,
    
    FOREIGN KEY (userId) REFERENCES carDealershipUser(id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id)
);

CREATE TABLE purchase(
	id INT PRIMARY KEY AUTO_INCREMENT,
    profileId INT NOT NULL,
    vehicleId INT NOT NULL,
    salePrice DECIMAL(10,2) NOT NULL,
    saleType VARCHAR(20),
    dateAdded TIMESTAMP NOT NULL,
    userId INT NOT NULL,
    
    FOREIGN KEY (profileId) REFERENCES personProfile(id),
    FOREIGN KEY (vehicleId) REFERENCES vehicle(id),
    FOREIGN KEY (userId) REFERENCES carDealershipUser(id)
);