package chalkboard.me.cdc.producer.presentation.controller.advice;

import chalkboard.me.cdc.producer.presentation.controller.exception.SystemException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({NotFoundException.class})
  ResponseEntity<ErrorMessage> resourceNotFoundException(NotFoundException e, WebRequest request) {
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({SystemException.class})
  ResponseEntity<ErrorMessage> systemException(SystemException e, WebRequest request) {
    return new ResponseEntity<>(new ErrorMessage(e.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
