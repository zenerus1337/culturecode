package pl.pwr.isk.culturecode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.isk.culturecode.repository.QuestionRepository;

import pl.pwr.isk.culturecode.model.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<QuestionEntity> findQuestionByPlaceName(String name) {
        List<QuestionEntity> questions = findAll();

        List<QuestionEntity> questionsFound = new ArrayList<>();

        for(QuestionEntity question : questions) {
            if(question.getPlace_id().toLowerCase().contains(name.toLowerCase())) {
                if(!questionsFound.contains(question)) {
                    questionsFound.add(question);
                }
            }
        }
        return questionsFound;
    }


    public List<QuestionEntity> findAll() {
        return questionRepository.findAll();
    }
}
