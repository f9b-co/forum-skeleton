package fr.formation.training.forum.repositories;

import fr.formation.training.forum.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {

}
