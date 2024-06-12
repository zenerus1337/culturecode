package pl.pwr.isk.culturecode.web;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.DTO.QuestionDTO;
import pl.pwr.isk.culturecode.model.PlaceEntity;
import pl.pwr.isk.culturecode.model.QuestionEntity;
import pl.pwr.isk.culturecode.repository.QuestionRepository;
import pl.pwr.isk.culturecode.service.QuestionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestMapping("/questions")
@RestController
public class QuestionController {
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionEntity> getAllQuestions() {return questionRepository.findAll();}

    @PostMapping("/addQuestion")
    public ResponseEntity<List<QuestionDTO>> addQuestion(@RequestBody QuestionDTO questionDTO) {
        List<QuestionDTO> response = new ArrayList<>();

        QuestionEntity newQuestion = new QuestionEntity();
        newQuestion.setQuestion(questionDTO.getQuestion());
        newQuestion.setPoints(questionDTO.getPoints());
        newQuestion.setPlace_id(questionDTO.getPlace_id());
        questionRepository.save(newQuestion);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/question/{place_id}")
    public ResponseEntity<List<QuestionEntity>> getQuestionsByName(@PathVariable("place_id") String name) {
        List<QuestionEntity> result = questionService.findQuestionByPlaceName(name);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
