package pl.pwr.isk.culturecode.web;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.isk.culturecode.DTO.QuestionDTO;
import pl.pwr.isk.culturecode.DTO.QuizQuestionDTO;
import pl.pwr.isk.culturecode.model.QuestionEntity;
import pl.pwr.isk.culturecode.model.QuizQuestionsEntity;
import pl.pwr.isk.culturecode.repository.QuizQuestionRepository;
import pl.pwr.isk.culturecode.service.QuizQuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/questions_answer")
@RestController
public class QuizQuestionController {
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizQuestionService quizQuestionService;

    @Autowired
    public QuizQuestionController(QuizQuestionRepository quizQuestionRepository, QuizQuestionService quizQuestionService) {
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizQuestionService = quizQuestionService;
    }

    @GetMapping
    public List<QuizQuestionsEntity> getAllQuizQuestions() {
        return quizQuestionRepository.findAll();
    }

    @PostMapping("/addQuizQuestion")
    public ResponseEntity<List<QuizQuestionDTO>> addQuizQuestion(@RequestBody QuizQuestionDTO quizQuestionDTO) {
        List<QuizQuestionDTO> response = new ArrayList<>();

        QuizQuestionsEntity newQuizQuestion = new QuizQuestionsEntity();

        newQuizQuestion.setQuestionId(quizQuestionDTO.getQuestionId());
        newQuizQuestion.setTrue(quizQuestionDTO.getTrue());
        newQuizQuestion.setValue(quizQuestionDTO.getValue());
        quizQuestionRepository.save(newQuizQuestion);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/getQuizQuestion/{question_id}")
    public ResponseEntity<List<QuizQuestionDTO>> getQuestionById(@PathVariable("question_id") Integer id) {
        List<QuizQuestionsEntity> entities = quizQuestionService.findQuizQuestionByQuestionId(id);

        if (entities != null) {
            List<QuizQuestionDTO> dtos = entities.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private QuizQuestionDTO convertToDto(QuizQuestionsEntity entity) {
        QuizQuestionDTO dto = new QuizQuestionDTO();
        dto.setQuestionId(entity.getQuestionId());
        dto.setValue(entity.getValue());
        dto.setTrue(entity.getTrue());
        return dto;
    }

}
