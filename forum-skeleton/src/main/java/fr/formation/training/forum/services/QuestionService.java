package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;

public interface QuestionService {

    IdentifierDto add(QuestionAddDto dto);

    DiscussionViewDto getDiscussion(Long id);

    void update(Long id, QuestionUpdateDto dto);

    void deleteDiscussion(Long id);
}
