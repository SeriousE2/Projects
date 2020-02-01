DROP DATABASE IF EXISTS carDealershipTestDB;

CREATE DATABASE carDealershipTestDB;

USE carDealershipTestDB;

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

INSERT INTO personProfile(id, fullName, email, phone) VALUES(639, "name", "email", "phone");
INSERT INTO personProfile(id, fullName, email, phone) VALUES(640, "name2", "email2", "phone2");
SELECT * FROM personProfile;

INSERT INTO carDealershipUser(profileId, userRole, userPassword, dateAdded) VALUES(639, "sales", "password" , "2016-01-01");
INSERT INTO carDealershipUser(profileId, userRole, userPassword, dateAdded) VALUES(640, "sales", "password" , "2016-01-01");
SELECT * FROM carDealershipUser;

INSERT INTO make(id, makeName, userId, dateAdded) VALUES(525, "makeName", 1, "2016-01-01");
SELECT * FROM make;

INSERT INTO model(makeId, modelName, dateAdded, userId) VALUES (525, "modelName", "2016-01-01", 1);
SELECT * FROM model;

INSERT INTO vehicle(makeId, modelId, msrp, listPrice, mileage, vehicleYear, vehicleType, vehicleDescription, image, exteriorColor, interiorColor, transmission, bodyStyle, vin, dateAdded, isAvailable, isFeatured, userId) 
VALUES(525,2,10.0, 10.0, 100, 2015, "vehicleType", "vehicleDescription", "image.png" , "ext", "int", "transmission", "bodyStyle", "vin", "2016-01-01", true, true, 1);
SELECT * FROM vehicle;

INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(639, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(639, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(639, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(640, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(640, 3, "10", "bank", "2016-01-01", 1);
SELECT * FROM purchase;

INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(639, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(639, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(640, 3, "10", "bank", "2016-01-01", 1);
INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(640, 3, "10", "bank", "2016-01-01", 1);
SELECT * FROM purchase;

SELECT COUNT(*) FROM purchase GROUP BY userId;
SELECT SUM(salePrice) FROM purchase GROUP BY userId;

SELECT COUNT(*) FROM purchase WHERE userId = 1;
SELECT SUM(salePrice) FROM purchase WHERE userId = 1;

SELECT userId, SUM(salePrice) AS totalSales, COUNT(*) AS salesCount FROM purchase GROUP BY userId;

SELECT *
FROM personProfile;
