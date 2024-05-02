package com.culturecode.main.app.connection;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.Call;
import retrofit2.http.Path;

public interface UserService {
    @POST("/authenticate")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/users")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);
}
