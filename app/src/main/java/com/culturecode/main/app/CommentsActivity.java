package com.culturecode.main.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.CommentRequest;
import com.culturecode.main.app.connection.CommentResponse;
import com.culturecode.main.app.connection.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

    private List<String> commentsList;
    private ArrayAdapter<String> commentsAdapter;
    private UserService userService;
    private String placeId;
    private String login; // Nazwa użytkownika

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        userService = ApiClient.getService();
        commentsList = new ArrayList<>();

        placeId = getIntent().getStringExtra("objectIdentifier");

        SharedPreferences sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        login = sharedPref.getString("login", null);

        commentsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commentsList);
        ListView commentsListView = findViewById(R.id.commentsListView);
        commentsListView.setAdapter(commentsAdapter);

        fetchCommentsFromServer(placeId);

        Button btnAddComment = findViewById(R.id.btnAddComment);
        EditText etNewComment = findViewById(R.id.etNewComment);

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newComment = etNewComment.getText().toString().trim();
                if (!newComment.isEmpty()) {
                    addCommentToServer(newComment);
                    etNewComment.setText("");
                }
            }
        });

        Button btnBack = findViewById(R.id.btnBackToFacts);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchCommentsFromServer(String placeId) {
        userService.getCommentsByPlaceId(placeId).enqueue(new Callback<List<CommentResponse>>() {
            @Override
            public void onResponse(Call<List<CommentResponse>> call, Response<List<CommentResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CommentResponse> comments = response.body();
                    for (CommentResponse comment : comments) {
                        String formattedComment = comment.getTimeStamp() + " - " + comment.getUserId() + ": " + comment.getComment();
                        commentsList.add(formattedComment);
                    }
                    commentsAdapter.notifyDataSetChanged();
                } else {
                    Log.e("CommentsActivity", "Response was not successful or body was null");
                }
            }

            @Override
            public void onFailure(Call<List<CommentResponse>> call, Throwable t) {
                Log.e("CommentsActivity", "Error fetching comments", t);
            }
        });
    }

    private void addCommentToServer(String comment) {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        CommentRequest newComment = new CommentRequest();
        newComment.setUserId(login);
        newComment.setPlaceId(placeId);
        newComment.setComment(comment);
        newComment.setTimeStamp(timeStamp);

        userService.addComment(newComment).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    String formattedComment = timeStamp + " - " + login + ": " + comment;
                    commentsList.add(formattedComment);
                    commentsAdapter.notifyDataSetChanged();
                } else {
                    Log.e("CommentsActivity", "Error adding comment, response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CommentsActivity", "Error adding comment", t);
            }
        });
    }
}
