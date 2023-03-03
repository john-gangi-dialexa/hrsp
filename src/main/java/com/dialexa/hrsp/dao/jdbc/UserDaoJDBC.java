package com.dialexa.hrsp.dao.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dialexa.hrsp.dao.UserDao;
import com.dialexa.hrsp.model.User;

@Repository
public class UserDaoJDBC implements UserDao {


 private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoJDBC(DataSource dataSource) {
       this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    
}