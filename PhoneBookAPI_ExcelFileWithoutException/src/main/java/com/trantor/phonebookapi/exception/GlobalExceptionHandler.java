package com.trantor.phonebookapi.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


@ControllerAdvice // for global exception handling
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //for getting exception messges
    Properties properties = new Properties();

    public String getErrorMessage(String errorName){
        //Getting exception messages
        try {
            properties.load(new FileInputStream("./src/main/resources/exceptions.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(errorName);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.wrongHttpRequest");

        details.add(errorDetail);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, status);

        return ResponseEntity.status(status).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.mediaTypeNotFound");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, status);

        return ResponseEntity.status(status).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.missingPathVariable");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, status);

        return ResponseEntity.status(status).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.missingServletRequest");

        details.add(errorDetail);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, status);

        return ResponseEntity.status(status).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.typeMismatch");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, status);

        return ResponseEntity.status(status).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.messageNotReadable");

        details.add(errorDetail);
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, status);

        return ResponseEntity.status(status).body(errorDetails);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> ResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.resourceNotFound");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<Object> IdNotFoundException(InvalidIdException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.idNotFound");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(WrongURLException.class)
    public ResponseEntity<Object> wrongURLException(WrongURLException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.wrongURL");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(WrongDataException.class)
    public ResponseEntity<Object> wrongDataException(WrongDataException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.wrongData");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOther(Exception ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();

        String errorDetail = getErrorMessage("exception.handleOther");

        details.add(errorDetail);

        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, details, HttpStatus.BAD_REQUEST);

        //checking for internal server error
        if (ex instanceof HttpServerErrorException.InternalServerError) {
            errorDetails = new ErrorDetails(new Date(), message, details, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

}
