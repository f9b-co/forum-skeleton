package fr.formation.training.forum.controllers;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.services.AnswerService;
import fr.formation.training.forum.services.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

}
