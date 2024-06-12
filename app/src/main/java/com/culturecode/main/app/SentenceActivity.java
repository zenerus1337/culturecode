package com.culturecode.main.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.SentenceRequest;
import com.culturecode.main.app.connection.SentenceResponse;
import com.culturecode.main.app.connection.UserService;
import com.culturecode.main.app.localisations.LocalisationRequest;
import com.culturecode.main.app.ui.home.localisations;
import com.culturecode.main.app.SentenceActivity;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SentenceActivity extends AppCompatActivity {

    private EditText etSentence;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        userService = ApiClient.getService();
        etSentence = findViewById(R.id.etSentence);
        Button btnSubmit = findViewById(R.id.btnSubmit);
        Button btnBack = findViewById(R.id.btnBack);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qrCode = etSentence.getText().toString();
                if (!qrCode.isEmpty()) {
                    checkQrCodeFromServer(qrCode);
                } else {
                    etSentence.setError("Proszę wpisać kod QR");
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void checkQrCodeFromServer(String qrCode) {
        userService.checkQrCode(qrCode).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBodyString = response.body().string();
                        Intent intent = new Intent(SentenceActivity.this, FactsActivity.class);
                        intent.putExtra("place", responseBodyString.trim());
                        startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    etSentence.setError("Nieprawidłowy kod QR");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SentenceActivity.this, "Błąd połączenia sieciowego", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
