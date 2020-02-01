/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.entity.Model;
import com.mycompany.thecardealership.entity.QueryVehicle;
import com.mycompany.thecardealership.entity.Vehicle;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eddy
 */
@Repository
public class VehicleDaoImpl implements VehicleDao {

    JdbcTemplate jdbc;
    MakeDao makeDao;
    ModelDao modelDao;
    UserDao userDao;

    @Autowired
    public VehicleDaoImpl(JdbcTemplate jdbc, MakeDao makeDao, ModelDao modelDao, UserDao userDao) {
        this.jdbc = jdbc;
        this.makeDao = makeDao;
        this.modelDao = modelDao;
        this.userDao = userDao;
    }

    @Override
    public Vehicle createVehicle(Make make, Model model,
            BigDecimal msrp, BigDecimal listPrice, int mileage,
            int year, String vehicleType, String vehicleDescription,
            String image, String exteriorColor, String interiorColor,
            String transmission, String bodyStyle, String vin, int userId) {
        
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(model);
        vehicle.setMake(make);
        vehicle.setCreatedBy(userDao.readUserById(userId));

        vehicle.setExteriorColor(exteriorColor);
        vehicle.setImage(image);
        vehicle.setInteriorColor(interiorColor);
        vehicle.setListPrice(listPrice);
        vehicle.setMsrp(msrp);
        vehicle.setTransmission(transmission);
        vehicle.setVehicleDescription(vehicleDescription);
        vehicle.setVehicleType(vehicleType);
        vehicle.setMileage(mileage);
        vehicle.setVehicleYear(year);
        vehicle.setVin(vin);
        vehicle.setBodyStyle(bodyStyle);

        Timestamp timestamp = Timestamp.valueOf(vehicle.getDateAdded());

        final String CREATE_VEHICLE = "INSERT INTO vehicle(makeId, modelId, msrp,"
                + " listPrice, mileage, vehicleYear, vehicleType, vehicleDescription,"
                + " image, exteriorColor, interiorColor, transmission, bodyStyle, vin,"
                + " dateAdded, isAvailable, isFeatured, userId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbc.update(CREATE_VEHICLE, make.getMakeId(), model.getId(),
                msrp, listPrice, mileage, year, vehicleType,
                vehicleDescription, image, exteriorColor,
                interiorColor, transmission, bodyStyle, vin,
                timestamp, vehicle.isIsAvailable(), vehicle.isIsAvailable(), userId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        vehicle.setVehicleId(newId);

        return vehicle;
    }

    @Override
    public Vehicle buildVehicle(Vehicle vehicle) {
        Timestamp timestamp = Timestamp.valueOf(vehicle.getDateAdded());

        final String CREATE_VEHICLE = "INSERT INTO vehicle(makeId, modelId,"
                + " msrp, listPrice, mileage, vehicleYear, vehicleType,"
                + " vehicleDescription, image, exteriorColor, interiorColor,"
                + " transmission, bodyStyle, vin, dateAdded, isAvailable,"
                + " isFeatured, userId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        jdbc.update(CREATE_VEHICLE, vehicle.getMake().getMakeId(),
                vehicle.getModel().getId(), vehicle.getMsrp(),
                vehicle.getListPrice(), vehicle.getMileage(),
                vehicle.getVehicleYear(), vehicle.getVehicleType(),
                vehicle.getVehicleDescription(), vehicle.getImage(),
                vehicle.getExteriorColor(), vehicle.getInteriorColor(),
                vehicle.getTransmission(), vehicle.getBodyStyle(),
                vehicle.getVin(), timestamp, vehicle.isIsAvailable(),
                vehicle.isIsAvailable(), vehicle.getCreatedBy().getUserId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        vehicle.setVehicleId(newId);

        return vehicle;
    }

    @Override
    public List<Vehicle> readAllVehicles() {
        final String READ_VEHICLES = "SELECT * FROM vehicle";
        List<Vehicle> vehicles = jdbc.query(READ_VEHICLES, new VehicleMapper());
        vehicles.stream().forEach(vehicle -> {
            vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
            vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
            vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        });
        return vehicles;
    }

    @Override
    public List<Vehicle> readFeaturedVehicles() {
        final String READ_FEATURED_VEHICLES = "SELECT * FROM vehicle WHERE isFeatured = true";
        List<Vehicle> vehicles = jdbc.query(READ_FEATURED_VEHICLES, new VehicleMapper());
        vehicles.stream().forEach(vehicle -> {
            vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
            vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
            vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        });
        return vehicles;
    }

    @Override
    public Vehicle readVehicleById(int id) {
        final String READ_VEHICLE_BY_ID = "SELECT * FROM vehicle WHERE vehicleId = ?";
        Vehicle vehicle = null;

        try {
            vehicle = jdbc.queryForObject(READ_VEHICLE_BY_ID, new VehicleMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
        vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
        vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {

        final String UPDATE_VEHICLE = "UPDATE vehicle SET makeId = ?,"
                + " modelId = ?, msrp = ?, listPrice = ?, mileage = ?,"
                + " vehicleYear = ?, vehicleType = ?, vehicleDescription = ?,"
                + " image = ?, exteriorColor = ?, interiorColor = ?, transmission = ?,"
                + " bodyStyle = ?, vin = ?, isAvailable = ?, isFeatured = ? WHERE vehicleId = ?";
        
        int successful = jdbc.update(UPDATE_VEHICLE, vehicle.getMake().getMakeId(),
                vehicle.getModel().getId(), vehicle.getMsrp(), vehicle.getListPrice(),
                vehicle.getMileage(), vehicle.getVehicleYear(), vehicle.getVehicleType(),
                vehicle.getVehicleDescription(), vehicle.getImage(), vehicle.getExteriorColor(),
                vehicle.getInteriorColor(), vehicle.getTransmission(), vehicle.getBodyStyle(),
                vehicle.getVin(), vehicle.isIsAvailable(), vehicle.isIsFeatured(), vehicle.getVehicleId());
        int num = 0;
    }

    @Override
    public List<Vehicle> query20VehiclesByTypePriceAndYearDescendingMSRP(String query, String type,
            BigDecimal minPrice, BigDecimal maxPrice, int minYear, int maxYear) {
        
        final String VEHICLE_INVENTORY_QUERY_BY_TYPE = "SELECT * FROM vehicle"
                + "INNER JOIN make ON vehicle.makeId = make.id"
                + "INNER JOIN model ON vehicle.modelId = model.id"
                + "WHERE vehicle.listPrice > ?"
                + "AND vehicle.listPrice < ?"
                + "AND vehicle.vehicleType = ?"
                + "AND vehicle.vehicleYear BETWEEN ? AND ?"
                + "AND vehicle.vehicleYear LIKE ?"
                + "OR make.makeName LIKE ?"
                + "OR model.modelName LIKE ?"
                + "ORDER BY vehicle.msrp DESC"
                + "LIMIT 0, 20";

        final String VEHICLE_INVENTORY_QUERY = "SELECT * FROM vehicle"
                + "INNER JOIN make ON vehicle.makeId = make.id"
                + "INNER JOIN model ON vehicle.modelId = model.id"
                + "WHERE vehicle.listPrice > ?"
                + "AND vehicle.listPrice < ?"
                + "AND vehicle.vehicleYear BETWEEN ? AND ?"
                + "AND vehicle.vehicleYear LIKE ?"
                + "OR make.makeName LIKE ?"
                + "OR model.modelName LIKE ?"
                + "ORDER BY vehicle.msrp DESC"
                + "LIMIT 0, 20";

        List<Vehicle> vehicles;
        if (type.equals("none")) {
            vehicles = jdbc.query(VEHICLE_INVENTORY_QUERY, new VehicleMapper(), minPrice, maxPrice,
                    minYear, maxYear, query + "%", query + "%", query + "%");
        } else {
            vehicles = jdbc.query(VEHICLE_INVENTORY_QUERY_BY_TYPE, new VehicleMapper(), minPrice,
                    maxPrice, type, minYear, maxYear, query + "%", query + "%", query + "%");
        }

        return vehicles;
    }

    @Override
    public List<Vehicle> query20(QueryVehicle data) {
        final String VEHICLE_INVENTORY_QUERY_BY_TYPE = "SELECT * FROM vehicle\n"
                + "INNER JOIN make ON vehicle.makeId = make.id\n"
                + "INNER JOIN model ON vehicle.modelId = model.id\n"
                + "WHERE vehicle.listPrice > ?\n"
                + "AND vehicle.listPrice < ?\n"
                + "AND vehicle.vehicleType = ?\n"
                + "AND vehicle.vehicleYear BETWEEN ? AND ?\n"
                + "AND vehicle.vehicleYear LIKE ?\n"
                + "OR make.makeName LIKE ?\n"
                + "OR model.modelName LIKE ?\n"
                + "ORDER BY vehicle.msrp DESC\n"
                + "LIMIT 0, 20";

        final String VEHICLE_INVENTORY_QUERY_BY_TYPE_NOQUERY = "SELECT * FROM vehicle\n"
                + "INNER JOIN make ON vehicle.makeId = make.id\n"
                + "INNER JOIN model ON vehicle.modelId = model.id\n"
                + "WHERE vehicle.listPrice > ?\n"
                + "AND vehicle.listPrice < ?\n"
                + "AND vehicle.vehicleType = ?\n"
                + "AND vehicle.vehicleYear BETWEEN ? AND ?\n"
                + "ORDER BY vehicle.msrp DESC\n"
                + "LIMIT 0, 20";

        final String VEHICLE_INVENTORY_QUERY = "SELECT * FROM vehicle\n"
                + "INNER JOIN make ON vehicle.makeId = make.id\n"
                + "INNER JOIN model ON vehicle.modelId = model.id\n"
                + "WHERE vehicle.listPrice > ?\n"
                + "AND vehicle.listPrice < ?\n"
                + "AND vehicle.vehicleYear BETWEEN ? AND ?\n"
                + "AND vehicle.vehicleYear LIKE ?\n"
                + "OR make.makeName LIKE ?\n"
                + "OR model.modelName LIKE ?\n"
                + "ORDER BY vehicle.msrp DESC\n"
                + "LIMIT 0, 20";

        final String VEHICLE_INVENTORY_NOTYPE_NOQUERY = "SELECT * FROM vehicle\n"
                + "INNER JOIN make ON vehicle.makeId = make.id\n"
                + "INNER JOIN model ON vehicle.modelId = model.id\n"
                + "WHERE vehicle.listPrice > ?\n"
                + "AND vehicle.listPrice < ?\n"
                + "AND vehicle.vehicleYear BETWEEN ? AND ?\n"
                + "ORDER BY vehicle.msrp DESC\n"
                + "LIMIT 0, 20";

        if (data.getQuery() == null) {
            data.setQuery("");
        }
        String query = data.getQuery();

        List<Vehicle> vehicles;
        if (data.getType().equals("none")) {
            if (query.equals("")) {
                vehicles = jdbc.query(VEHICLE_INVENTORY_NOTYPE_NOQUERY, new VehicleMapper(),
                        data.getMinPrice(), data.getMaxPrice(), data.getMinYear(), data.getMaxYear());
            } else {
                vehicles = jdbc.query(VEHICLE_INVENTORY_QUERY, new VehicleMapper(),
                        data.getMinPrice(), data.getMaxPrice(), data.getMinYear(),
                        data.getMaxYear(), query + "%", query + "%", query + "%");
            }
        } else {
            if (query.equals("")) {
                vehicles = jdbc.query(VEHICLE_INVENTORY_QUERY_BY_TYPE_NOQUERY,
                        new VehicleMapper(), data.getMinPrice(), data.getMaxPrice(),
                        data.getType(), data.getMinYear(), data.getMaxYear());
            } else {
                vehicles = jdbc.query(VEHICLE_INVENTORY_QUERY_BY_TYPE, new VehicleMapper(),
                        data.getMinPrice(), data.getMaxPrice(), data.getType(),
                        data.getMinYear(), data.getMaxYear(), query + "%", query + "%", query + "%");
            }
        }
        vehicles.stream().forEach(vehicle -> {
            vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
            vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
            vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        });
        return vehicles;
        
    }

    @Override
    public List<Vehicle> getAvailableVehicles() {
        final String READ_FEATURED_VEHICLES = "SELECT * FROM vehicle WHERE isAvailable = true ORDER BY dateAdded DESC";
        List<Vehicle> vehicles = jdbc.query(READ_FEATURED_VEHICLES, new VehicleMapper());
        vehicles.stream().forEach(vehicle -> {
            vehicle.setMake(makeDao.readMakeById(vehicle.getMake().getMakeId()));
            vehicle.setModel(modelDao.readModelById(vehicle.getModel().getId()));
            vehicle.setCreatedBy(userDao.readUserById(vehicle.getCreatedBy().getUserId()));
        });
        return vehicles;
    }

}
