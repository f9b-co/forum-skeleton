package fr.formation.training.forum.repositories;

import org.springframework.data.jpa.repository.*;

import fr.formation.training.forum.dtos.QuestionViewDto;
import fr.formation.training.forum.entities.Question;

import java.util.Optional;

public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    @Query("select new fr.formation.training.forum.dtos.QuestionViewDto"
	    + "(q.id, q.phrase, q.text, q.author, " + "q.questionDate, t.name) "
	    + "from Question q inner join q.technology t where q.id = :id")
    Optional<QuestionViewDto> findProjectedById(Long id);
}
