package fr.formation.training.forum.services;

import java.util.List;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Answer;

public interface AnswerService {

    IdentifierDto add(AnswerAddDto dto);

    void update(Long id, AnswerUpdateDto dto);

    List<Answer> getAnswers(Long questionId);

    void deleteNative(String id);
}
