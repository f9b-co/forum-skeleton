package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Answer;

import java.util.List;

public interface AnswerService {

    IdentifierDto add(AnswerAddDto dto);

    void update(Long id, AnswerUpdateDto dto);

    List<Answer> getAnswersByQuestionId(Long questionId);
}
