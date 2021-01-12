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
	// Functional approach:
	// Question question = questions.findById(id)
	// .orElseThrow(() -> new ResourceNotFoundException());
	// getMapper().map(dto, question);
	// setTechnology(question, dto.getTechnologyId());
	// questions.save(question);
	// Imperative approach:
	// Optional<Question> optional = questions.findById(id);
	// if (optional.isEmpty()) {
	// throw new ResourceNotFoundException();
	// } else {
	// Question question = optional.get();
	// getMapper().map(dto, question);
	// setTechnology(question, dto.getTechnologyId());
	// questions.save(question);
	// }
	// JPQL update:
	// ResourceNotFoundException not handled!
	// If required check first with an "exists" query
	// like in "remove" service
	questions.updateQuestion(id, dto.getText(), dto.getPhrase(),
		dto.getTechnologyId());
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

    @Transactional
    @Override
    public void remove(Long id) {
	if (!questions.existsById(id)) {
	    throw new ResourceNotFoundException();
	}
	answers.deleteByQuestionId(id);
	questions.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Question getQuestion(Long id) {
	Question question = questions.findById(id).get();
	// Returns reference to Technology proxy:
	Technology tech = question.getTechnology();
	// First time access, proxy/lazy initialization
	// Need a database access
	String name = tech.getName();
	System.out.println(name);//
	//
	return question;
    }

    @Override
    public QuestionInterfaceDto getQuestionInterface(Long id) {
	return questions.getById(id);
    }
}
