package pl.pwr.isk.culturecode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.model.QuestionEntity;
import pl.pwr.isk.culturecode.model.QuizQuestionsEntity;
import pl.pwr.isk.culturecode.repository.QuizQuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizQuestionService {
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;

    public List<QuizQuestionsEntity> findQuizQuestionByQuestionId(Integer id) {
        List<QuizQuestionsEntity> quizQuestions = quizQuestionRepository.findAll();

        List<QuizQuestionsEntity> questionFound = new ArrayList<>();

        for(QuizQuestionsEntity question : quizQuestions) {
            if(question.getQuestionId().equals(id)) {
                if(!questionFound.contains(question)) {
                    questionFound.add(question);
                }
            }
        }
        return questionFound;
    }
}
