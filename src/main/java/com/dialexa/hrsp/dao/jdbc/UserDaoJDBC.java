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
public class UserDaoJdbc implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoJdbc(DataSource dataSource) {
       this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User createUser(String username, String hashedPassword) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, new Object[] { username, hashedPassword }, Long.class);
        if (id != null) {
            User newUser = new User();
            newUser.setId(id);
            newUser.setUsername(username);
            newUser.setPassword(hashedPassword);
            return newUser;
        }
        
        return null;
    }
        
}