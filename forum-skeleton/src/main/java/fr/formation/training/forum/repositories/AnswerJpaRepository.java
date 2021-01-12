package fr.formation.training.forum.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import fr.formation.training.forum.dtos.AnswerViewDto;
import fr.formation.training.forum.entities.Answer;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {

    @Query("select new fr.formation.training.forum.dtos.AnswerViewDto"
	    + "(a.text, a.author, a.answerDate) "
	    + "from Answer a where a.question.id = :id order by a.answerDate desc")
    List<AnswerViewDto> findProjectedByQuestionId(Long id);

    // Derived query thanks to delete prefix
    // delete from Answer a where a.question.id = :id
    void deleteByQuestionId(Long id);

    // @Transactional optional if @Transactional in service
    @Modifying
    @Query("update Answer a set a.text = :text where a.id = :id")
    void updateAnswer(Long id, String text);

    List<Answer> findByQuestionId(Long id);
}
