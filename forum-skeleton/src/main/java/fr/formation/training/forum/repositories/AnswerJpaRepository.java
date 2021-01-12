package fr.formation.training.forum.repositories;

import fr.formation.training.forum.dtos.AnswerViewDto;
import fr.formation.training.forum.entities.Answer;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {

    @Query("select new fr.formation.training.forum.dtos.AnswerViewDto"
            + "(a.id, a.text, a.author, a.answerDate) "
            + "from Answer a where a.question.id = :id order by answerDate desc")
    List<AnswerViewDto> findAllProjectedByQuestionId(Long id);

    List<Answer> findByQuestionId(Long questionId);

    void deleteByQuestionId(Long questionId);

    @Modifying
    @Query("update Answer a set a.text = :text where a.id = :id")
    void updateAnswer(Long id, String text);
}
