package fr.formation.training.forum;

import java.lang.annotation.*;

import javax.validation.*;

@Constraint(validatedBy = NotSameNameAndRatingValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NotSameNameAndRating {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}