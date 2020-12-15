package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;

public interface AnswerService {

    IdentifierDto add(AnswerAddDto dto);

    void update(Long id, AnswerUpdateDto dto);
}
