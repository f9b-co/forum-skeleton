package fr.formation.training.forum.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Question;
import fr.formation.training.forum.services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
	this.service = service;
    }

    @PostMapping
    public IdentifierDto add(@RequestBody @Valid QuestionAddDto dto) {
	return service.add(dto);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Long id,
	    @RequestBody @Valid QuestionUpdateDto dto) {
	service.update(id, dto);
    }

    /**
     * Returns the aggregate view of the question with given id and all its
     * answers.
     *
     * @param id the id of the question to return a view of
     * @return the the aggregate view
     */
    @GetMapping("/{id}")
    public DiscussionViewDto getDiscussion(@PathVariable("id") Long id) {
	return service.getDiscussion(id);
    }

    /**
     * Removes the question with given id and its related answers if any from
     * the forum.
     *
     * @param id the id of the question to be removed
     */
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
	service.remove(id);
    }

    // Open in view demo:
    // Note: never expose entities directly!
    // @Transactional(readOnly = true)
    @GetMapping("/{id}/entity")
    public Question getQuestion(@PathVariable("id") Long id) {
	Question question = service.getQuestion(id);
	// Technology tech = question.getTechnology();
	// String name = tech.getName();
	return question;
    }

    @GetMapping("/{id}/byInterface")
    public QuestionInterfaceDto getQuestionInterface(
	    @PathVariable("id") Long id) {
	QuestionInterfaceDto result = service.getQuestionInterface(id);
	return result;
    }
}
