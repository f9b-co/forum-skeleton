package fr.formation.training.forum.repositories;

import fr.formation.training.forum.dtos.AnswerViewDto;
import fr.formation.training.forum.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {

    @Query("select new fr.formation.training.forum.dtos.AnswerViewDto"
            + "(a.id, a.text, a.author, a.answerDate) "
            + "from Answer a inner join a.question q where q.id = :id")
    List<AnswerViewDto> findProjectedByQuestionId(Long id);

}
