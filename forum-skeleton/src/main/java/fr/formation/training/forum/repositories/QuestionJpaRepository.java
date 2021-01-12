package fr.formation.training.forum.repositories;

import fr.formation.training.forum.dtos.QuestionInterfaceDto;
import org.springframework.data.jpa.repository.*;

import fr.formation.training.forum.dtos.QuestionViewDto;
import fr.formation.training.forum.entities.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    @Query("select new fr.formation.training.forum.dtos.QuestionViewDto"
            + "(q.id, q.phrase, q.text, q.author, " + "q.questionDate, t.name) "
            + "from Question q inner join q.technology t where q.id = :id")
    Optional<QuestionViewDto> findProjectedById(Long id);

    @Query("select new fr.formation.training.forum.dtos.QuestionViewDto"
            + "(q.id, q.phrase, q.text, q.author, " + "q.questionDate, t.name) "
            + "from Question q inner join q.technology t order by q.questionDate desc")
    List<QuestionViewDto> listAll();

    @Modifying
    @Query("update Question q set q.phrase = :phrase, q.text = :text,"
            + "q.technology.id = :technologyId where q.id = :id")
    void updateQuestion(Long id, String phrase, String text, Long technologyId);

    QuestionInterfaceDto getById(Long id);
}
