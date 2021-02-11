package fr.formation.training.forum;

import java.time.LocalDateTime;
import java.util.*;

public class ApiError {

  private LocalDateTime timestamp = LocalDateTime.now();

  private String message;

  private List<NotValidError> errors = new ArrayList<>();

  private boolean NotValidErrors = false;


  public ApiError(String message) {
    this.message = message;
  }

  public ApiError(String message, List<NotValidError> errors) {
    this.message = message;
    this.errors = errors;
    NotValidErrors = true;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<NotValidError> getErrors() {
    return errors;
  }

  public void setErrors(List<NotValidError> errors) {
    this.errors = errors;
  }

  public boolean isNotValidErrors() {
    return NotValidErrors;
  }

  public void setNotValidErrors(boolean notValidErrors) {
    NotValidErrors = notValidErrors;
  }
}