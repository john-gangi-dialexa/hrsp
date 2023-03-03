package com.dialexa.hrsp.dao;


import java.sql.SQLException;
import java.util.List;

import com.dialexa.hrsp.model.User;

public interface UserDao {
    List<User> getAllUsers() throws SQLException;
}

