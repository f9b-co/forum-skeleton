package fr.formation.training.forum;

import java.time.LocalDateTime;
import java.util.*;

public class ApiError {

    private LocalDateTime timestamp = LocalDateTime.now();

    private String message;

    private List<Object> errors = new ArrayList<>();

    private boolean onError = false;

    public ApiError(String message) {
	this.message = message;
    }

    public LocalDateTime getTimestamp() {
	return timestamp;
    }

    public String getMessage() {
	return message;
    }

    public List<Object> getErrors() {
	return errors;
    }

    public void setErrors(List<Object> errors) {
	this.errors = errors;
    }

    public boolean isOnError() {
	return onError;
    }

    public void setOnError(boolean onError) {
	this.onError = onError;
    }
}
