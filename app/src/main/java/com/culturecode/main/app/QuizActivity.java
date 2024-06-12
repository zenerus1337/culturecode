package com.culturecode.main.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.culturecode.main.app.connection.QuestionResponse;
import com.culturecode.main.app.connection.QuizQuestionsResponse;
import androidx.appcompat.app.AppCompatActivity;
import com.culturecode.main.app.connection.ApiClient;
import com.culturecode.main.app.connection.UserService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private UserService userService;
    private List<QuestionResponse> questions = new ArrayList<>();
    private List<List<QuizQuestionsResponse>> answersList = new ArrayList<>();
    private int currentQuestionIndex = 0;

    private TextView tvQuestion;
    private RadioGroup rgAnswers;
    private RadioButton rbOptionA, rbOptionB, rbOptionC, rbOptionD;
    private Button btnNextQuestion;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initializeViews();

        userService = ApiClient.getService();
        String placeId = getIntent().getStringExtra("place");
        fetchQuestions(placeId);

        Button btnReturnToFacts = findViewById(R.id.btnReturnToFacts);
        btnReturnToFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Zakładając, że FactsActivity jest rodzicem w stosie
            }
        });
    }



    private void initializeViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        rgAnswers = findViewById(R.id.rgAnswers);
        rbOptionA = findViewById(R.id.rbOptionA);
        rbOptionB = findViewById(R.id.rbOptionB);
        rbOptionC = findViewById(R.id.rbOptionC);
        rbOptionD = findViewById(R.id.rbOptionD);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        btnNextQuestion.setOnClickListener(v -> handleNextQuestion());
    }

    private void checkAnswer() {
        int selectedRadioButtonId = rgAnswers.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString().trim();
            List<QuizQuestionsResponse> currentAnswers = answersList.get(currentQuestionIndex);
            boolean isAnswerCorrect = false;
            for (QuizQuestionsResponse answer : currentAnswers) {
                if (answer.getValue().trim().equalsIgnoreCase(selectedAnswer) && Boolean.TRUE.equals(answer.getIsTrue())) {
                    isAnswerCorrect = true;
                    break;
                }
            }
            if (isAnswerCorrect) {
                score++;
            }
            Log.d("QuizActivity", "Selected Answer: " + selectedAnswer + " isCorrect: " + isAnswerCorrect);
        } else {
            Log.d("QuizActivity", "No option selected");
        }
    }




    private void finishQuiz() {
        setContentView(R.layout.activity_quiz_results);
        TextView resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Twój wynik: " + score + " na " + questions.size());
    }

    private void fetchQuestions(String placeId) {
        userService.getQuestionsByPlaceId(placeId).enqueue(new Callback<List<QuestionResponse>>() {
            @Override
            public void onResponse(Call<List<QuestionResponse>> call, Response<List<QuestionResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    questions.addAll(response.body());
                    if (!questions.isEmpty()) {
                        for (QuestionResponse question : questions) {
                            fetchAnswersForQuestion(question);
                        }
                    } else {
                        tvQuestion.setText("Pobrano 0 pytań.");
                    }
                } else {
                    tvQuestion.setText("Błąd: " + response.code() + " " + response.message());
                    try {
                        Log.e("QuizActivity", "Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<QuestionResponse>> call, Throwable t) {
                tvQuestion.setText("Błąd połączenia sieciowego: " + t.getMessage());
                Log.e("QuizActivity", "Network error", t);
            }
        });
    }

    private void fetchAnswersForQuestion(QuestionResponse question) {
        userService.getQuizQuestionsByQuestionId(question.getId()).enqueue(new Callback<List<QuizQuestionsResponse>>() {
            @Override
            public void onResponse(Call<List<QuizQuestionsResponse>> call, Response<List<QuizQuestionsResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("QuizActivity", "Response body: " + new Gson().toJson(response.body()));
                    for (QuizQuestionsResponse answer : response.body()) {
                        Log.d("QuizActivity", "Received answer: " + answer.getValue() + " IsTrue: " + answer.getIsTrue());
                    }
                    answersList.add(response.body());
                    if (answersList.size() == questions.size()) {
                        loadNextQuestion();
                    }
                } else {
                    Log.e("QuizActivity", "Failed to load answers with status code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<QuizQuestionsResponse>> call, Throwable t) {
                Log.e("QuizActivity", "Network error while fetching answers", t);
            }
        });
    }


    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuestionResponse currentQuestion = questions.get(currentQuestionIndex);
            List<QuizQuestionsResponse> currentAnswers = answersList.get(currentQuestionIndex);

            tvQuestion.setText(currentQuestion.getQuestion());
            RadioButton[] rbs = {rbOptionA, rbOptionB, rbOptionC, rbOptionD};
            int i = 0;
            for (; i < currentAnswers.size(); i++) {
                rbs[i].setText(currentAnswers.get(i).getValue());
                rbs[i].setVisibility(View.VISIBLE);
            }
            // Hide unused radio buttons
            for (; i < rbs.length; i++) {
                rbs[i].setVisibility(View.GONE);
            }
            rgAnswers.clearCheck();
        } else {
            finishQuiz();
        }
    }

    private void handleNextQuestion() {
        checkAnswer();
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            loadNextQuestion();
        } else {
            finishQuiz();
        }
    }



}
