package com.dialexa.hrsp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dialexa.hrsp.dao.jdbc.UserDaoJDBC;
import com.dialexa.hrsp.model.User;

@Service
public class UserService {
    private final UserDaoJDBC UserDaoJDBC;

    public UserService(UserDaoJDBC UserDaoJDBC) {
        this.UserDaoJDBC = UserDaoJDBC;
    }

    public List<User> getAllUsers() {
        return UserDaoJDBC.getAllUsers();
    }
}