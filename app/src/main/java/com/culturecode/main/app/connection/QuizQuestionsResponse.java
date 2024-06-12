package com.culturecode.main.app.connection;

import com.google.gson.annotations.SerializedName;

public class QuizQuestionsResponse {
    private Integer questionId;
    private String value;

    @SerializedName("isTrue")
    private String isTrue;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIsTrue() {
        return Boolean.parseBoolean(isTrue);
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }
}
