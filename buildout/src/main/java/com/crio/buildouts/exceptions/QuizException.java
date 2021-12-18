package com.crio.buildouts.exceptions;

abstract class QuizException extends RuntimeException {

  static final int MODULE_NOT_FOUND = 100;
  static final int  NO_QUESTIONS_IN_MODULE = 101;

  QuizException() {}

  QuizException(String message) {
    super(message);
  }

  public abstract int getErrorType();
}

