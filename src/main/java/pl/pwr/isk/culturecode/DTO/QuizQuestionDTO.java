package pl.pwr.isk.culturecode.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizQuestionDTO {
    private Integer questionId;
    private String value;

    @JsonProperty("isTrue")
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

    public String getTrue() {
        return isTrue;
    }

    public void setTrue(String isTrue) {
        this.isTrue = isTrue;
    }
}
