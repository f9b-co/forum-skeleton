package fr.formation.training.forum.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import fr.formation.training.forum.dtos.*;
import fr.formation.training.forum.services.TechnologyService;

@RestController
@RequestMapping("/technologies")
public class TechnologyController {

    private final TechnologyService service;

    public TechnologyController(TechnologyService service) {
	this.service = service;
    }

    @GetMapping
    public List<TechnologyViewDto> getAll() {
	return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long id) {
	// TODO
    }

    @DeleteMapping
    public void deleteAll() {
	// TODO
    }
    // For illustration:

    @DeleteMapping("/bad")
    public void deleteBad() {
	service.deleteBad();
    }

    //
    @GetMapping("/{id}")
    public TechnologyViewDto getOne(@PathVariable("id") Long id) {
	TechnologyViewDto result = service.getOne(id);
	return result;
    }
    // @GetMapping("/{id}")
    // public ResponseEntity<TechnologyViewDto> getOne(
    // @PathVariable("id") Long id) {
    // ResponseEntity<TechnologyViewDto> result = null;
    // try {
    // TechnologyViewDto dto = service.getOne(id); // 404
    // result = ResponseEntity.ok(dto); // 200
    // } catch (ResourceNotFoundException e) {
    // // 404 instead of 400 BAD REQUEST (annotation)
    // result = ResponseEntity.notFound().build();
    // }
    // return result;
    // }

    @GetMapping("/{id}/byInterface")
    public TechnologyInterfaceDto getOneByInterface(
	    @PathVariable("id") Long id) {
	return service.getOneByInterface(id);
    }

    // Native insert and SQL injection demo:
    @PostMapping
    public void insertNative(@RequestBody @Valid TechnologyAddDto dto) {
	service.insertNative(dto);
    }

    // Add with db violation
    @PostMapping("/withViolation")
    public void postWithViolation() {
	service.postWithViolation();
    }
}
