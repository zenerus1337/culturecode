package pl.pwr.isk.culturecode.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = false, nullable = false)
    private Integer questionId;

    @Column(unique = true, nullable = false)
    private String value;

    @Column(nullable = false)
    private String isTrue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public void setTrue(String aTrue) {
        isTrue = aTrue;
    }
}
