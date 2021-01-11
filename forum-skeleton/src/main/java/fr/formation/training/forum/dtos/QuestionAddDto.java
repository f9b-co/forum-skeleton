package fr.formation.training.forum.dtos;

import javax.validation.constraints.*;

public class QuestionAddDto {

    @NotBlank // null, "", " "
    @Size(min = 0, max = 255) // "" -> positif
    private String phrase; // "emilie" ou "Émilie"

    @NotBlank // null, "", " "
    @Size(max = 1000) // "......1001chars"
    private String text;

    @NotBlank // null, "", " "
    @Size(max = 50) // "......51chars"
    private String author;

    @NotNull // null
    @Positive // -1 0
    private Long technologyId;

    public Long getTechnologyId() {
	return technologyId;
    }

    public void setTechnologyId(Long technologyId) {
	this.technologyId = technologyId;
    }

    public String getPhrase() {
	return phrase;
    }

    public void setPhrase(String phrase) {
	this.phrase = phrase;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }
}
