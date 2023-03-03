package com.dialexa.hrsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dialexa.hrsp.dao.jdbc.UserDaoJDBC;
import com.dialexa.hrsp.model.User;

@Controller
public class UserController {

    @Autowired
    private final UserDaoJDBC userDaoJDBC;

    public UserController(UserDaoJDBC userDaoJDBC) {
        this.userDaoJDBC = userDaoJDBC;
    }

    @QueryMapping
    public List<User> users(@Argument String UserNumber) {
        return userDaoJDBC.getAllUsers();
    }

}