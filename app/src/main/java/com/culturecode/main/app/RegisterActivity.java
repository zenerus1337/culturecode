package com.culturecode.main.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.RegisterRequest;
import com.culturecode.main.app.connection.RegisterResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText edLogin, edEmail,edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.registerBtn);
        edLogin = findViewById(R.id.login);
        edEmail = findViewById(R.id.email);
        edPassword = findViewById(R.id.password);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edLogin.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
                    String message = "Wypełnij wszystkie pola";
                    Toast.makeText(RegisterActivity.this,message,Toast.LENGTH_LONG).show();
                }
                RegisterRequest registerRequest = new RegisterRequest(
                        edLogin.getText().toString(),
                        edPassword.getText().toString(),
                        edEmail.getText().toString());
                registerUser(registerRequest);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerPanel), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void registerUser(RegisterRequest registerRequest) {
        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);

        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    String message = registerResponse.getMessage();
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("RegisterActivity", "Błąd: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String message = "Nie można zarejestrować użytkownika, spróbuj ponownie później";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                Log.e("RegisterActivity", "Błąd rejestracji: : ", t);
            }
        });
    }
}