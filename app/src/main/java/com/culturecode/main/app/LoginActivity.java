package com.culturecode.main.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.LoginRequest;
import com.culturecode.main.app.connection.LoginResponse;
import com.culturecode.main.app.databinding.ActivityLoginBinding;
import com.culturecode.main.app.ui.home.localisations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn, registerRequestBtn;
    EditText edLogin, edPassword;

    localisations local = new localisations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = findViewById(R.id.login);
        edPassword = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerRequestBtn = findViewById(R.id.registerRequestBtn);

        registerRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edLogin.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString())) {
                    String message = "Wypełnij wszystkie pola";
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    LoginRequest loginRequest = new LoginRequest(edLogin.getText().toString(),edPassword.getText().toString());
                    loginUser(loginRequest);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.loginPanel), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void loginUser(LoginRequest loginRequest) {
        localisations local = new localisations();
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();

                    // Zapisanie loginu w SharedPreferences
                    SharedPreferences sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("login", edLogin.getText().toString());
                    editor.apply();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Nieprawidłowe dane.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                Log.e("LoginActivity", "Błąd: ", t);
            }
        });
    }
}
