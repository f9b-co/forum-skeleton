package fr.formation.training.forum.services;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Answer;
import fr.formation.training.forum.entities.Question;
import fr.formation.training.forum.repositories.AnswerCustomRepository;
import fr.formation.training.forum.repositories.AnswerJpaRepository;
import fr.formation.training.forum.repositories.QuestionJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerServiceImpl extends AbstractService
	implements AnswerService {

	private final AnswerJpaRepository answers;
	private final AnswerCustomRepository customAnswersRepo;
    private final QuestionJpaRepository questions;


    public AnswerServiceImpl(AnswerJpaRepository answers,
							 AnswerCustomRepository customAnswersRepo, QuestionJpaRepository questions) {
		this.answers = answers;
		this.customAnswersRepo = customAnswersRepo;
		this.questions = questions;
    }

	@Transactional
	@Override
    public IdentifierDto add(AnswerAddDto dto) {
	Answer answer = getMapper().map(dto, Answer.class);
	answer.setAnswerDate(LocalDateTime.now());
	setQuestion(answer, dto.getQuestionId());
	//answers.save(answer);
	return new IdentifierDto(answer.getId());
    }

	private void setQuestion(Answer answer, Long questionId) {
		Question question = questions.getOne(questionId);
		answer.setQuestion(question);
	}

	@Transactional
	@Override
    public void update(Long id, AnswerUpdateDto dto) {
	//Answer answer = answers.findById(id).orElseThrow(RessourceNotFoundException::new);
	//getMapper().map(dto, answer);
	answers.updateAnswer(id, dto.getText());
	//answers.save(answer); // redundant if @Transactional
    }

	@Override
	public List<Answer> getAnswersByQuestionId(Long questionId) {
		return answers.findByQuestionId(questionId);
	}

	@Transactional
	@Override
	public void deleteNative(String id) {
		customAnswersRepo.deleteConcat(id); // deleteParameterized(id) or deleteConcat(id) to permit sql injection
	}
}
