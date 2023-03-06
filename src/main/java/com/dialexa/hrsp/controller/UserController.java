package com.dialexa.hrsp.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.dialexa.hrsp.service.UserService;
import com.dialexa.hrsp.model.CreateUserInput;
import com.dialexa.hrsp.model.User;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

    @MutationMapping
    public User createUser(@Argument("input") CreateUserInput input) {
        String username = input.getUsername();
        String password = input.getPassword();
        return userService.createUser(username, password);
    }
}
