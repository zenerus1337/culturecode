package pl.pwr.isk.culturecode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pwr.isk.culturecode.model.QuestionEntity;
import pl.pwr.isk.culturecode.model.QuizQuestionsEntity;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestionsEntity, Long> {
}
