package fr.formation.training.forum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    private final static Log CUSTOMLOGGER = LogFactory.getLog(CustomControllerAdvice.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        CUSTOMLOGGER.trace("400 BAD REQUEST - Validation error", ex);
        ApiError body = buildNotValidApiError(status, ex);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    private static ApiError buildNotValidApiError(HttpStatus status, MethodArgumentNotValidException ex) {
        BindingResult results = ex.getBindingResult();
        List<FieldError> fieldErrors = results.getFieldErrors();
        List<NotValidError> notValidErrors = new ArrayList<>();
        List<ObjectError> globalErrors = results.getGlobalErrors();
        for (FieldError error : fieldErrors) {
            String input = error.getField();
            String notValidated = error.getCode();
            notValidErrors.add(new NotValidError(input, notValidated, false));
        }
        for (ObjectError error : globalErrors) {
            String input = error.getObjectName();
            String notValidated = error.getCode();
            notValidErrors.add(new NotValidError(input, notValidated, true));
        }
        return new ApiError(status.getReasonPhrase(), notValidErrors);
    }

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
        if (body == null) {
            body = new ApiError(ex.getMessage());
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
