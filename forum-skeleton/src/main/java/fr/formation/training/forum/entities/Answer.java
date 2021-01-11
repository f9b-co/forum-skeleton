package fr.formation.training.forum.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

/**
 * The answer to a question. The natural key of the entity is the author and
 * answer datetime.
 */
@Entity
@Table(name = "answers")
public class Answer extends AbstractEntity {

    @Column(name = "answer_text")
    private String text;

    @Column(name = "answer_author", updatable = false)
    private String author;

    @Column(name = "answer_datetime", updatable = false)
    private LocalDateTime answerDate;

    @ManyToOne()
    @JoinColumn(name = "question_id", updatable = false)
    private Question question;

    public void setAnswerDate(LocalDateTime answerDate) {
	this.answerDate = answerDate;
    }

    public void setQuestion(Question question) {
	this.question = question;
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

    public LocalDateTime getAnswerDate() {
	return answerDate;
    }

    public Question getQuestion() {
	return question;
    }
}
