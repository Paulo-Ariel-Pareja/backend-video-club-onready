package com.adndevelopersoftware.app.controller.rest.advice;

import com.adndevelopersoftware.app.vo.ApiErrorHandler;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PeliculaRestControllerAdvice {

  @ExceptionHandler(DataAccessException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ApiErrorHandler DataAccessException(DataIntegrityViolationException exception) {
    return new ApiErrorHandler(exception);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrorHandler IllegalArgumentException(IllegalArgumentException exception) {
    return new ApiErrorHandler(exception);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ApiErrorHandler dataIntegrityViolationExceptionHandler(DataIntegrityViolationException exception) {
    return new ApiErrorHandler(exception);
  }


}
