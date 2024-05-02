package com.culturecode.main.app.connection;

public class LoginRequest {
    private String  login;
    private String password;

    public String getLogin(String login) {
        return login;
    }

    public String getPassword(String password) {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
