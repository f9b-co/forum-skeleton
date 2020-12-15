package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Answer;
import fr.formation.training.forum.entities.Question;
import fr.formation.training.forum.repositories.AnswerJpaRepository;
import fr.formation.training.forum.repositories.QuestionJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerServiceImpl extends AbstractService
	implements AnswerService {

	private final AnswerJpaRepository answers;
    private final QuestionJpaRepository questions;


    public AnswerServiceImpl(AnswerJpaRepository answers,
                             QuestionJpaRepository questions) {
	this.answers = answers;
	this.questions = questions;
    }

    @Override
    public IdentifierDto add(AnswerAddDto dto) {
	Answer answer = getMapper().map(dto, Answer.class);
	answer.setAnswerDate(LocalDateTime.now());
	setQuestion(answer, dto.getQuestionId());
	answers.save(answer);
	return new IdentifierDto(answer.getId());
    }

    @Override
    public void update(Long id, AnswerUpdateDto dto) {
	Answer answer = answers.findById(id).get();
	getMapper().map(dto, answer);
	answers.save(answer);
    }

    private void setQuestion(Answer answer, Long questionId) {
	Question question = questions.getOne(questionId);
	answer.setQuestion(question);
    }
}
