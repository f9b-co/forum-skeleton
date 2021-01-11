package fr.formation.training.forum.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.training.forum.ResourceNotFoundException;
import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.*;
import fr.formation.training.forum.repositories.*;

@Service
public class QuestionServiceImpl extends AbstractService
	implements QuestionService {

    private final QuestionJpaRepository questions;

    private final AnswerJpaRepository answers;

    private final TechnologyJpaRepository technologies;

    public QuestionServiceImpl(QuestionJpaRepository questions,
	    AnswerJpaRepository answers, TechnologyJpaRepository technologies) {
	this.questions = questions;
	this.answers = answers;
	this.technologies = technologies;
    }

    @Transactional
    @Override
    public IdentifierDto add(QuestionAddDto dto) {
	Question question = getMapper().map(dto, Question.class);
	question.setQuestionDate(LocalDateTime.now());
	setTechnology(question, dto.getTechnologyId());
	questions.save(question);
	return new IdentifierDto(question.getId());
    }

    @Transactional
    @Override
    public void update(Long id, QuestionUpdateDto dto) {
	Question question = questions.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException());
	// Demo code:
	// Technology technology = question.getTechnology();
	// String name = technology.getName();
	//
	getMapper().map(dto, question);
	setTechnology(question, dto.getTechnologyId());
	questions.save(question);
	// ****************
	// Optional<Question> optional = questions.findById(id);
	// if (optional.isEmpty()) {
	// throw new ResourceNotFoundException();
	// } else {
	// Question question = optional.get();
	// getMapper().map(dto, question);
	// setTechnology(question, dto.getTechnologyId());
	// questions.save(question);
	// }
    }

    @Transactional(readOnly = true)
    @Override
    public DiscussionViewDto getDiscussion(Long id) {
	QuestionViewDto questionView = questions.findProjectedById(id)
		.orElseThrow(ResourceNotFoundException::new);
	List<AnswerViewDto> answersView = answers.findProjectedByQuestionId(id);
	return new DiscussionViewDto(questionView, answersView);
    }

    private void setTechnology(Question question, Long technologyId) {
	Technology technology = technologies.getOne(technologyId);
	question.setTechnology(technology);
    }

    @Transactional()
    @Override
    public void remove(Long id) {
	if (!questions.existsById(id)) {
	    throw new ResourceNotFoundException();
	}
	answers.deleteByQuestionId(id);
	questions.deleteById(id);
    }
}
