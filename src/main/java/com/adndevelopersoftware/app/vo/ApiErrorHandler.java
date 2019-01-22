package com.adndevelopersoftware.app.vo;

public class ApiErrorHandler {

  private String exception;
  private String message;

  public ApiErrorHandler(Exception exception) {
    this.exception = exception.getClass().getName();
    this.message = exception.getMessage();
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
