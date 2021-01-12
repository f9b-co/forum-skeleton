package fr.formation.training.forum.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Question;

public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    @Query("select new fr.formation.training.forum.dtos.QuestionViewDto"
	    + "(q.id, q.phrase, q.text, q.author, q.questionDate, t.name) "
	    + "from Question q inner join q.technology t where q.id = :id")
    Optional<QuestionViewDto> findProjectedById(Long id);

    @Modifying
    @Query("update Question q set q.text = :text, q.phrase = :phrase, "
	    + "q.technology.id = :technologyId where q.id = :id")
    void updateQuestion(Long id, String text, String phrase, Long technologyId);

    QuestionInterfaceDto getById(Long id);
}
