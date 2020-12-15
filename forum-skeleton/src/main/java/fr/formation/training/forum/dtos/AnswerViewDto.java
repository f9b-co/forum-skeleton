package fr.formation.training.forum.dtos;

import java.time.LocalDateTime;

public class AnswerViewDto {

    private final Long id;

    private final String text;

    private final String author;

    private final LocalDateTime answerDate;

    public AnswerViewDto(Long id, String text, String author, LocalDateTime answerDate) {
	this.id = id;
	this.text = text;
	this.author = author;
	this.answerDate = answerDate;
    }

    public Long getId() {
	return id;
    }

    public String getText() {
	return text;
    }

    public String getAuthor() {
	return author;
    }

    public LocalDateTime getAnswerDate() {
	return answerDate;
    }
}
