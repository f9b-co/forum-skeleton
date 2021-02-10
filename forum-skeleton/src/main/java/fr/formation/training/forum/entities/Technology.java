package fr.formation.training.forum.entities;

import javax.persistence.*;

/**
 * A technology to classify questions. The natural key of the entity is the
 * name.
 */
@Entity
@Table(name = "technologies")
public class Technology extends AbstractEntity {

    @Column(name = "technology_name")
    private String name;

    @Column(name = "technology_rating")
    private double rating;

    public void setName(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setRating(double rating) {
	this.rating = rating;
    }

    public double getRating() {
	return rating;
    }

    @Override
    public String toString() {
	return String.format("{name=%s, rating=%s}", name, rating);
    }
}
