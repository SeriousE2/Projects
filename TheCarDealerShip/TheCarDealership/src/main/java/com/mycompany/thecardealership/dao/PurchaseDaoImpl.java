/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Inventory;
import com.mycompany.thecardealership.entity.Purchase;
import com.mycompany.thecardealership.entity.User;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.thecardealership.entity.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Eddy
 */
@Repository
public class PurchaseDaoImpl implements PurchaseDao {

    JdbcTemplate jdbc;
    ProfileDao profileDao;
    UserDao userDao;
    VehicleDao vehicleDao;

    @Autowired
    public PurchaseDaoImpl(JdbcTemplate jdbc, ProfileDao profileDao, UserDao userDao, VehicleDao vehicleDao) {
        this.jdbc = jdbc;
        this.profileDao = profileDao;
        this.userDao = userDao;
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Purchase createPurchase(Profile profile, int vehicleId, BigDecimal salePrice, String purchaseType, int userId) {
        Purchase purchase = new Purchase();
        purchase.setVehicle(vehicleDao.readVehicleById(vehicleId));
        purchase.setCustomerProfile(profile);
        purchase.setCreatedBy(userDao.readUserById(userId));
        purchase.setSalePrice(salePrice);
        purchase.setSaleType(purchaseType);

        Timestamp timestamp = Timestamp.valueOf(purchase.getDateAdded());

        final String CREATE_PURCHASE = "INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(?,?,?,?,?,?)";
        jdbc.update(CREATE_PURCHASE, profile.getProfileId(), vehicleId, salePrice, purchaseType, timestamp, userId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        purchase.setPurchaseId(newId);

        return purchase;
    }

    @Override
    public Purchase purchaseVehicle(Purchase purchase) {
        Timestamp timestamp = Timestamp.valueOf(purchase.getDateAdded());

        final String CREATE_PURCHASE = "INSERT INTO purchase(profileId, vehicleId, salePrice, saleType, dateAdded, userId) VALUES(?,?,?,?,?,?)";
        jdbc.update(CREATE_PURCHASE, purchase.getCustomerProfile().getProfileId(), purchase.getVehicle().getVehicleId(), purchase.getSalePrice(), purchase.getSaleType(), timestamp, purchase.getCreatedBy().getUserId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        purchase.setPurchaseId(newId);

        return purchase;
    }

    @Override
    public List<Purchase> readAllPurchases() {
        final String SELECT_ALL_PURCHASES = "SELECT * FROM purchase";
        List<Purchase> purchases = jdbc.query(SELECT_ALL_PURCHASES, new PurchaseMapper());
        purchases.stream().forEach(purchase -> {
            purchase.setCreatedBy(userDao.readUserById(purchase.getCreatedBy().getUserId()));
            purchase.setCustomerProfile(profileDao.readProfileById(purchase.getCustomerProfile().getProfileId()));
            purchase.setVehicle(vehicleDao.readVehicleById(purchase.getVehicle().getVehicleId()));
        });
        return purchases;
    }

    @Override
    public Purchase readPurchaseById(int id) {
        final String SELECT_PURCHASE_BY_ID = "SELECT * FROM purchase WHERE id = ?";
        Purchase purchase = null;
        try {
            purchase = jdbc.queryForObject(SELECT_PURCHASE_BY_ID, new PurchaseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        purchase.setCreatedBy(userDao.readUserById(purchase.getCreatedBy().getUserId()));
        purchase.setCustomerProfile(profileDao.readProfileById(purchase.getCustomerProfile().getProfileId()));

        purchase.setVehicle(vehicleDao.readVehicleById(purchase.getVehicle().getVehicleId()));
        return purchase;
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        final String UPDATE_PURCHASE = "UPDATE purchase SET salePrice = ?, saleType = ? WHERE id = ?";
        jdbc.update(UPDATE_PURCHASE, purchase.getSalePrice(), purchase.getSaleType(), purchase.getPurchaseId());
    }

    @Override
    public void deletePurchase(int id) {
        final String DELETE_PURCHASE = "DELETE FROM purchase WHERE id = ?";
        jdbc.update(DELETE_PURCHASE, id);
    }

    @Override
    public List<User> getGroupSalesReport(LocalDate startingOn, LocalDate to) {
        final String GET_NETSALES_BY_ID = "SELECT userId, SUM(salePrice) AS totalSales, COUNT(*) AS salesCount FROM purchase WHERE dateAdded BETWEEN ? AND ? GROUP BY userId";
        List<User> users = jdbc.query(GET_NETSALES_BY_ID, new UserSalesMapper(), startingOn, to);
        return users;
    }

    @Override
    public List<User> getUserSalesReport(int id, LocalDate startingOn, LocalDate to) {
        final String GET_NETSALES_BY_ID = "SELECT userId, SUM(salePrice) AS totalSales, COUNT(*) AS salesCount FROM purchase WHERE dateAdded BETWEEN ? AND ? AND userId = ? GROUP BY userId";
        List<User> users = jdbc.query(GET_NETSALES_BY_ID, new UserSalesMapper(), startingOn, to, id);
        return users;
    }

    @Override
    public List<Inventory> runNewInventoryReport() {
        final String RUN_INVENTORY_REPORT = "SELECT vehicleYear, makeName, modelName, SUM(vehicle.msrp) AS stockValue, COUNT(model.modelName) AS modelCount  FROM vehicle \n"
                + "INNER JOIN make ON vehicle.makeId = make.id\n"
                + "INNER JOIN model ON vehicle.modelId = model.id\n"
                + "WHERE vehicleType = \"new\"\n"
                + "AND isAvailable = true\n"
                + "GROUP BY modelName, vehicleYear, makeName";
        List<Inventory> inventory = jdbc.query(RUN_INVENTORY_REPORT, new InventoryMapper());
        return inventory;
    }

    @Override
    public List<Inventory> runUsedInventoryReport() {
        final String RUN_INVENTORY_REPORT = "SELECT vehicleYear, makeName, modelName, SUM(vehicle.msrp) AS stockValue, COUNT(model.modelName) AS modelCount  FROM vehicle \n"
                + "INNER JOIN make ON vehicle.makeId = make.id\n"
                + "INNER JOIN model ON vehicle.modelId = model.id\n"
                + "WHERE vehicleType = \"used\"\n"
                + "AND isAvailable = true\n"
                + "GROUP BY modelName, vehicleYear, makeName";
        List<Inventory> inventory = jdbc.query(RUN_INVENTORY_REPORT, new InventoryMapper());
        return inventory;
    }
}
