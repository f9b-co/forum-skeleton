package fr.formation.training.forum.services;

import java.time.LocalDateTime;

import fr.formation.training.forum.RessourceNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.*;
import fr.formation.training.forum.repositories.*;
import org.springframework.transaction.annotation.Transactional;


@Service
public class QuestionServiceImpl extends AbstractService
	implements QuestionService {

    private final QuestionJpaRepository questions;

	private final AnswerJpaRepository answers;

    private final TechnologyJpaRepository technologies;

    public QuestionServiceImpl(QuestionJpaRepository questions, AnswerJpaRepository answers,
							   TechnologyJpaRepository technologies) {
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

	private void setTechnology(Question question, Long technologyId) {
		Technology technology = technologies.getOne(technologyId);
		question.setTechnology(technology);
	}

	@Transactional
	@Override
    public void update(Long id, QuestionUpdateDto dto) {
	Question question = questions.findById(id)
			.orElseThrow(RessourceNotFoundException::new);
	getMapper().map(dto, question);
	setTechnology(question, dto.getTechnologyId());
	questions.save(question);
    }

	@Transactional(readOnly = true)
	@Override
    public DiscussionViewDto getDiscussion(Long id) {
	QuestionViewDto questionView = questions.findProjectedById(id)
			.orElseThrow(RessourceNotFoundException::new);
	return new DiscussionViewDto(questionView, answers.findAllProjectedByQuestionId(id));
    }

	@Transactional
	@Override
	public void deleteDiscussion(Long id) {
		if(!questions.existsById(id)) {
			throw new RessourceNotFoundException();
		}
		answers.deleteByQuestionId(id);
		questions.deleteById(id);
	}
}
