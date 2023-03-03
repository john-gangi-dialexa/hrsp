package com.dialexa.hrsp.model;

public class User {

    private Long id;
    private String username;
    private String password;

    public User(Long id, String username, String password ){
        this.id=id;
        this.username=username;
        this.password=password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

