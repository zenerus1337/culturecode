package com.culturecode.main.app;

import com.culturecode.main.app.connection.QuizQuestionsResponse;
import java.util.List;

public class Question {
    private String questionText;
    private List<QuizQuestionsResponse> options; // Lista odpowiedzi
    private int points; // Punkty za pytanie

    public Question(String questionText, List<QuizQuestionsResponse> options, int points) {
        this.questionText = questionText;
        this.options = options;
        this.points = points;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<QuizQuestionsResponse> getOptions() {
        return options;
    }

    public int getPoints() {
        return points;
    }
}