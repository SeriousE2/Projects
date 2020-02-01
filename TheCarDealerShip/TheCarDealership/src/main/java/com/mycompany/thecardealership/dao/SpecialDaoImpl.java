/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Special;
import com.mycompany.thecardealership.entity.User;
import com.mycompany.thecardealership.entity.Vehicle;
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
public class SpecialDaoImpl implements SpecialDao {
    JdbcTemplate jdbc;
    VehicleDao vehicleDao;
    UserDao userDao;

    @Autowired
    public SpecialDaoImpl(JdbcTemplate jdbc, VehicleDao vehicleDao, UserDao userDao) {
        this.jdbc = jdbc;
        this.vehicleDao = vehicleDao;
        this.userDao = userDao;
    }

    @Override
    public Special createSpecial(String title, String description, int vehicleId, int userId) {
        Special special = new Special();
        special.setSpecialDescription(description);
        special.setVehicle(vehicleDao.readVehicleById(vehicleId));
        special.setCreatedBy(userDao.readUserById(userId));
        special.setTitle(title);
        
        Vehicle vehicle = vehicleDao.readVehicleById(vehicleId);
        special.setVehicle(vehicle);
        
        User user = userDao.readUserById(userId);
        special.setCreatedBy(user);

        Timestamp timestamp = Timestamp.valueOf(special.getDateAdded());

        final String CREATE_SPECIAL = "INSERT INTO special(title, specialDescription, vehicleId, dateAdded, userId) VALUES(?,?,?,?,?)";
        int wasSuccessful = jdbc.update(CREATE_SPECIAL, title, description, vehicleId, timestamp, userId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setSpecialId(newId);

        return special;
    }

    @Override
    public List<Special> readAllSpecials() {
        final String READ_ALL_SPECIALS = "SELECT * FROM special";
        List<Special> specials = jdbc.query(READ_ALL_SPECIALS, new SpecialMapper());
        specials.stream().forEach(special -> {
            special.setCreatedBy(userDao.readUserById(special.getCreatedBy().getUserId()));
            special.setVehicle(vehicleDao.readVehicleById(special.getVehicle().getVehicleId()));
        });
        return specials;
    }

    @Override
    public Special readSpecialById(int id) {
        final String READ_SPECIAL_BY_ID = "SELECT * FROM special WHERE id = ?";
        Special special = null;
        try {
            special = jdbc.queryForObject(READ_SPECIAL_BY_ID, new SpecialMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        special.setCreatedBy(userDao.readUserById(special.getCreatedBy().getUserId()));
        special.setVehicle(vehicleDao.readVehicleById(special.getVehicle().getVehicleId()));
        return special;
    } 

    @Override
    public void updateSpecial(Special special) {
        final String UPDATE_SPECIAL = "UPDATE special SET title = ?, dateBegin = ?, dateEnd = ?, specialDescription = ? WHERE id = ?";
        jdbc.update(UPDATE_SPECIAL, special.getTitle(), special.getDateBegin(), special.getDateEnd(), special.getSpecialDescription(), special.getSpecialId());
    }

    @Override
    public void deleteSpecial(int id) {
        final String DELETE_SPECIAL = "DELETE FROM special WHERE id = ?";
        jdbc.update(DELETE_SPECIAL, id);
    }

}
