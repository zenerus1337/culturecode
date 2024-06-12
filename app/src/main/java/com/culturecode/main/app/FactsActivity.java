package com.culturecode.main.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.FactResponse;
import com.culturecode.main.app.connection.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;
import java.io.IOException;
import java.util.List;

public class FactsActivity extends AppCompatActivity {
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        userService = ApiClient.getService();
        TextView tvPlaceName = findViewById(R.id.tvPlaceName);
        TextView tvFacts = findViewById(R.id.tvFacts);
        Button btnBackToList = findViewById(R.id.btnBackToList);
        Button btnCommentsForm = findViewById(R.id.btnCommentsForm);
        Button btnStartQuiz = findViewById(R.id.btnStartQuiz);

        String placeName = getIntent().getStringExtra("place");
        tvPlaceName.setText(placeName);

        fetchFacts(placeName, tvFacts);

        btnCommentsForm.setOnClickListener(v -> {
            Intent intent = new Intent(FactsActivity.this, CommentsActivity.class);
            intent.putExtra("objectIdentifier", placeName);
            startActivity(intent);
        });

        btnStartQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(FactsActivity.this, QuizActivity.class);
            intent.putExtra("place", placeName);
            startActivity(intent);
        });

        btnBackToList.setOnClickListener(v -> finish());
    }

    private void fetchFacts(String placeName, TextView tvFacts) {
        userService.getFactsByPlaceName(placeName).enqueue(new Callback<List<FactResponse>>() {
            @Override
            public void onResponse(Call<List<FactResponse>> call, Response<List<FactResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FactResponse> facts = response.body();
                    StringBuilder factsHtml = new StringBuilder("<b>Fakty o obiekcie " + placeName + ":</b><br><br>");
                    for (FactResponse fact : facts) {
                        factsHtml.append(fact.getFact()).append("<br><br>");
                    }
                    tvFacts.setText(Html.fromHtml(factsHtml.toString(), Html.FROM_HTML_MODE_COMPACT));
                } else {
                    try {
                        Log.e("FactsActivity", "Failed with status: " + response.code() + " and error: " + response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    tvFacts.setText("Nie znaleziono faktów dla tego miejsca.");
                }
            }

            @Override
            public void onFailure(Call<List<FactResponse>> call, Throwable t) {
                tvFacts.setText("Błąd sieci przy próbie pobrania faktów.");
                Log.e("FactsActivity", "Błąd: ", t);
            }
        });
    }
}
