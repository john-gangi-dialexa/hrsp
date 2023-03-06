package com.dialexa.hrsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialexa.hrsp.dao.jdbc.UserDaoJdbc;
import com.dialexa.hrsp.model.User;

@Service
public class UserService {
    private final UserDaoJdbc UserDaoJdbc;

    @Autowired
    public UserService(UserDaoJdbc UserDaoJdbc) {
        this.UserDaoJdbc = UserDaoJdbc;
    }

    public List<User> getAllUsers() {
        return UserDaoJdbc.getAllUsers();
    }

    public User createUser(String username, String password) {
         return UserDaoJdbc.createUser(username, password);
    }
}