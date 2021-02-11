package fr.formation.training.forum;

import javax.validation.*;

import fr.formation.training.forum.dtos.TechnologyAddDto;

public class NotSameNameAndRatingValidator
        implements ConstraintValidator<NotSameNameAndRating, TechnologyAddDto> {

    @Override
    public boolean isValid(TechnologyAddDto dto,
                           ConstraintValidatorContext context) {
        String name = dto.getName();
        String rating = String.valueOf(dto.getRating());
        return !name.equals(rating);
    }
}