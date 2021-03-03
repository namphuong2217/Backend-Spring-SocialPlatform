package com.personalproject.socialbloggingplatform.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

// Custom Exception Handler to handle exceptions
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // global exception handler
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add("Something went wrong in the server. Please contact administrator for more information");
         details.add(e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Server error", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // specific exceptions:

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException e
            , WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Record not found", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public final ResponseEntity<Object> handleConstraintViolation(ResourceAlreadyExistsException e
            , WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse("Must Change Request", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e
            , HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : e.getBindingResult().getAllErrors()){
            details.add(error.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse("Invalid Request", details);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
