package fr.formation.training.forum;

import java.util.List;

import org.apache.commons.logging.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    private final static Log LOGGER = LogFactory
	    .getLog(CustomControllerAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
	    ResourceNotFoundException ex, WebRequest request) {
	LOGGER.debug("##### CUSTOM MESSAGE");
	return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND,
		request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex,
	    WebRequest request) {
	LOGGER.error("##### CUSTOM MESSAGE", ex);
	return handleExceptionInternal(ex, null, null,
		HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	    MethodArgumentNotValidException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
	// ex.getAllErrors();
	BindingResult results = ex.getBindingResult();
	List<FieldError> fieldErrors = results.getFieldErrors();
	// results.getGlobalErrors();
	return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
	    Object body, HttpHeaders headers, HttpStatus status,
	    WebRequest request) {
	if (body == null) {
	    body = new ApiError(ex.getMessage());
	}
	return super.handleExceptionInternal(ex, body, headers, status,
		request);
    }
}
