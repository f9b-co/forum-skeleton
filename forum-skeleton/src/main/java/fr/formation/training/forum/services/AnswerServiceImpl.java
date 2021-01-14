package fr.formation.training.forum.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.*;
import fr.formation.training.forum.repositories.*;

@Service
// @Transactional BAD PRACTICE at class level!
public class AnswerServiceImpl extends AbstractService
	implements AnswerService {

    private final QuestionJpaRepository questions;

    private final AnswerJpaRepository answers;

    private final AnswerCustomRepository customRepo;

    public AnswerServiceImpl(QuestionJpaRepository questions,
	    AnswerJpaRepository answers, AnswerCustomRepository customRepo) {
	this.questions = questions;
	this.answers = answers;
	this.customRepo = customRepo;
    }

    @Transactional
    @Override
    public IdentifierDto add(AnswerAddDto dto) {
	// Answer answer = new Answer()
	Answer answer = getMapper().map(dto, Answer.class);
	answer.setAnswerDate(LocalDateTime.now());
	Question question = questions.getOne(dto.getQuestionId());
	answer.setQuestion(question);
	answers.save(answer);
	return new IdentifierDto(answer.getId());
    }

    @Transactional
    @Override
    public void update(Long id, AnswerUpdateDto dto) {
	Answer answer = answers.findById(id).get();
	getMapper().map(dto, answer);
	//
	// answers.updateAnswer(id, dto.getText());
	//
	// redundant if @Transactional:
	// answers.save(answer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Answer> getAnswers(Long questionId) {
	List<Answer> result = answers.findByQuestionId(questionId);
	return result;
    }

    @Transactional
    @Override
    public void deleteNative(String id) {
	customRepo.deleteParameterized(id);
    }
}
