package fr.formation.training.forum.dtos;

import java.time.LocalDateTime;

public interface QuestionInterfaceDto {

    String getAuthor();

    LocalDateTime getQuestionDate();

    String getTechnologyName();
    // double getTechnologyRating();
    //
    // TechnologyInterfaceDto getTechnology();
}
