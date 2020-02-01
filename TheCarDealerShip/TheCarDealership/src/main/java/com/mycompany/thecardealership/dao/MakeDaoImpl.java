/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thecardealership.dao;


import com.mycompany.thecardealership.entity.Make;
import com.mycompany.thecardealership.entity.User;
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
public class MakeDaoImpl implements MakeDao{
    JdbcTemplate jdbc;
    UserDao userDao;
    
    @Autowired
    public MakeDaoImpl(JdbcTemplate jdbc, UserDao userDao) {
        this.jdbc = jdbc;
        this.userDao = userDao;
    }

    @Override
    public Make createMake(String makeName, int userId) {
        Make make = new Make();
        Timestamp timestamp = Timestamp.valueOf(make.getDateAdded());
        make.setMakeName(makeName);
        User user = userDao.readUserById(userId);
        make.setCreatedBy(user);
        
        final String CREATE_MAKE = "INSERT INTO make(makeName, userId, dateAdded) VALUES(?,?,?)";
        jdbc.update(CREATE_MAKE, makeName, userId, timestamp);
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setMakeId(newId);
        
        return make;
    }

    @Override
    public List<Make> readAllMakes() {
        final String READ_ALL_MAKES = "SELECT * FROM make";
        List<Make> makes = jdbc.query(READ_ALL_MAKES, new MakeMapper());
        makes.stream().forEach(make -> make.setCreatedBy(userDao.readUserById(make.getCreatedBy().getUserId())));
        return makes;
    }

    @Override
    public Make readMakeById(int id) {
        final String READ_MAKE_BY_ID = "SELECT * FROM make WHERE id = ?";
        Make make = null;
        try {
            make = jdbc.queryForObject(READ_MAKE_BY_ID, new MakeMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        make.setCreatedBy(userDao.readUserById(make.getCreatedBy().getUserId()));
        return make;
    }

    @Override
    public void updateMake(Make make) {
        final String UPDATE_MAKE = "UPDATE make SET makeName = ? WHERE id = ?";
        jdbc.update(UPDATE_MAKE, make.getMakeName(), make.getMakeId());
    }
    
}
