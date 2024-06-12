package com.culturecode.main.app.connection;

public class RegisterRequest {
    private String login;
    private String password;
    private String email;

    // Constructor
    public RegisterRequest(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
