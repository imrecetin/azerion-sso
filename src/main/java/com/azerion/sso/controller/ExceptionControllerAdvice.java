package com.azerion.sso.controller;

import com.azerion.sso.controller.resource.ErrorResource;
import com.azerion.sso.exception.EntityNotFoundException;
import com.azerion.sso.exception.EntityNotUpdatedException;
import com.azerion.sso.exception.InvalidM2MClientException;
import com.azerion.sso.exception.NotAnAppropriateInputParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.stream.Collectors;

/**
 *
 * Advice class that check if a rest endpoint throws an exception and change the response code and message according.
 *
 *
 * 1XX: Information
 *
 * 2XX: Success
 * 	200 - OK:
 * 			-> Everything worked
 *  201 - Created (mostly use for POST):
 *  		-> The server has successfully created a new resource
 *  		-> Newly created resource's location returned in the Location header
 *  202 - Accepted:
 *  		-> The server has accepted the request, but it is not yet complete
 *  		-> A location to determine the request's current status can be returned in the location header
 * 3XX: Redirection
 *
 * 4XX: Client Error
 * 	400 - Bad Request:
 * 			-> Malformed syntax
 * 			-> Should not be repeated without modification
 *  401 - Unauthorized:
 *  		-> Authentication is required
 *  		-> Includes a WWW-Authentication header
 *  403 - Forbidden:
 *  		-> Server has understood but refused to honor the request
 *  		-> Should not be repeated without modification
 *  404 - Not Found:
 *  		-> The server cannot find a resource matching a URI
 *  406 - Not Acceptable:
 *  		-> The server can only return response entities that do not match the client's Accept header
 *  409 - Conflict:
 *  		-> The resource is in a state that is in conflict with the request
 *  		-> Client should attempt to rectify the conflict and then resubmit the request
 *  422 - Unprocessable Entity:
 *          -> The resource already exist.
 *
 * 5XX: Server Error (if the app is responding a 500 code it means we have a bug or didn't thought all possible scenarios.)
 *
 *
 * @author Çetin İmre
 *
 */
// @formatter:on
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorResource> courseNotFoundExceptionHandler(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase()).message(ex.getMessage()).build());
    }


    @ResponseBody
    @ExceptionHandler(InvalidM2MClientException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResource>  invalidM2MClientExceptionHandler(InvalidM2MClientException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResource>  illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ErrorResource> internalServerErrorHandler(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message(ex.getMessage()).build());
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

    @ResponseBody
    @ExceptionHandler(EntityNotUpdatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResource> entityNotUpdatedExceptionHandler(EntityNotUpdatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(ex.getMessage()).build());
    }

    @ResponseBody
    @ExceptionHandler(NotAnAppropriateInputParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorResource>  duplicateFieldHandler(NotAnAppropriateInputParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResource.builder().name(ex.getClass().toString()).reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase()).message(ex.getMessage()).build());
    }
}
