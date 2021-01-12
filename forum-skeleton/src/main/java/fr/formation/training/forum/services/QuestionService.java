package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Question;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionService {

    IdentifierDto add(QuestionAddDto dto);

    DiscussionViewDto getDiscussion(Long id);

    void update(Long id, QuestionUpdateDto dto);

    List<QuestionViewDto> listAll();

    void deleteDiscussion(Long id);

    QuestionInterfaceDto getOneByInterface(Long id);
}
