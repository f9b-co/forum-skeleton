package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Question;

public interface QuestionService {

    IdentifierDto add(QuestionAddDto dto);

    DiscussionViewDto getDiscussion(Long id);

    void update(Long id, QuestionUpdateDto dto);

    void remove(Long id);

    Question getQuestion(Long id);

    QuestionInterfaceDto getQuestionInterface(Long id);
}
