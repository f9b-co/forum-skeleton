package fr.formation.training.forum;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class RessourceNotFoundException extends RuntimeException {

    public RessourceNotFoundException() {
	//
    }

    public RessourceNotFoundException(String message) {
        super(message);
    }
}
