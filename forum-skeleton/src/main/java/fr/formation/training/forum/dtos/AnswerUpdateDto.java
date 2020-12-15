package fr.formation.training.forum.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AnswerUpdateDto {

    @NotBlank
    @Size(max = 1000)
    private String text;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }
}
