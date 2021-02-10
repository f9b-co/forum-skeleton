package fr.formation.training.forum;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
	    ResourceNotFoundException ex, WebRequest request) {
	return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND,
		request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex,
	    WebRequest request) {
	return handleExceptionInternal(ex, null, null,
		HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
	    Object body, HttpHeaders headers, HttpStatus status,
	    WebRequest request) {
	// if (body == null) {
	// body = new ApiError(ex.getMessage());
	// }
	return super.handleExceptionInternal(ex, body, headers, status,
		request);
    }
}
