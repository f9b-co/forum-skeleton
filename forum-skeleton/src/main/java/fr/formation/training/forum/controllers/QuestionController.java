package fr.formation.training.forum.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.services.QuestionService;

import java.util.List;

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

    @GetMapping("/{id}/byInterface")
    public QuestionInterfaceDto getOneByInterface(@PathVariable("id") Long id) {
        return service.getOneByInterface(id);
    }

    @GetMapping
    public List<QuestionViewDto> listAll() {
        return service.listAll();
    }

    @DeleteMapping("/{id}")
    public void deleteDiscussion(@PathVariable("id") Long id) {
        service.deleteDiscussion(id);
    }
}
