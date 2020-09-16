package com.azerion.sso.controller;

import com.azerion.sso.controller.resource.ErrorResource;
import com.azerion.sso.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorResource> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase()).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler({
            EntityNotUpdatedException.class,
            InValidXAuthTypeException.class,
            InValidM2MClientException.class,
            IllegalArgumentException.class,
            NotAnAppropriateInputParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResource> entityNotUpdatedExceptionHandler(EntityNotUpdatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler({
            NotAuthenticatedException.class,
            InValidJwtTokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<ErrorResource>  inValidJwtTokenExceptionHandler(NotAuthenticatedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.UNAUTHORIZED.getReasonPhrase()).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResource> internalServerErrorHandler(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors()
                .stream()
                .map( ObjectError::getDefaultMessage )
                .collect( Collectors.toList() ).toString();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(ex.getMessage()).build());
    }

}
