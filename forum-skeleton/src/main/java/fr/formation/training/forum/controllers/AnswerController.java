package fr.formation.training.forum.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.entities.Answer;
import fr.formation.training.forum.services.AnswerService;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService service;

    public AnswerController(AnswerService service) {
	this.service = service;
    }

    @PostMapping
    public IdentifierDto add(@RequestBody @Valid AnswerAddDto dto) {
	return service.add(dto);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") Long id,
	    @RequestBody @Valid AnswerUpdateDto dto) {
	service.update(id, dto);
    }

    // Open in view demo:
    @GetMapping("/{questionId}")
    public List<Answer> getAnswers(
	    @PathVariable("questionId") Long questionId) {
	return service.getAnswers(questionId);
    }
}
