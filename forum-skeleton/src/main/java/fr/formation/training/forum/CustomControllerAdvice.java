package fr.formation.training.forum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    private final static Log CUSTOMLOGGER = LogFactory.getLog(CustomControllerAdvice.class);

    @ExceptionHandler(RessourceNotFoundException.class)
    protected ResponseEntity<Object> handleRessourceNotFoundException (RessourceNotFoundException ex, WebRequest request) {
        CUSTOMLOGGER.debug("### Custom NotFoundException Msg");
        return handleExceptionInternal(ex, "CustomNotFoundExceptionViaCustomControllerAdvice", null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException (RuntimeException ex, WebRequest request) {
        CUSTOMLOGGER.error("### Custom RuntimeException Msg");
        return handleExceptionInternal(ex, "CustomRuntimeExceptionViaCustomControllerAdvice", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
             Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
