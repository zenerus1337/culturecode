package com.culturecode.main.app.connection;

import java.io.Serializable;

public class LoginResponse {
    private String message;

    // Constructor
    public LoginResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }
}
