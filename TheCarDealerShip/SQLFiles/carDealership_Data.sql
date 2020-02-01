USE carDealershipDB;

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