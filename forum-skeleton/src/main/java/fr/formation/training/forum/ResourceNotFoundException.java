package fr.formation.training.forum;

@SuppressWarnings("serial")
// @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
	//
    }

    public ResourceNotFoundException(String message) {
	super(message);
    }
}
