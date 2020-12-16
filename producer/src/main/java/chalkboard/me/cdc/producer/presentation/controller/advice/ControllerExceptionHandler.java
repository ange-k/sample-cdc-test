package chalkboard.me.cdc.producer.presentation.controller.advice;

import chalkboard.me.cdc.producer.presentation.controller.exception.SystemException;
import chalkboard.me.cdc.producer.presentation.controller.pizza.exception.PizzaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler({PizzaNotFoundException.class})
  ResponseEntity<ErrorMessage> resourceNotFoundException(PizzaNotFoundException e, WebRequest request) {
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({SystemException.class})
  ResponseEntity<ErrorMessage> systemException(SystemException e, WebRequest request) {
    return new ResponseEntity<>(new ErrorMessage(e.getMsg()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({Exception.class})
  ResponseEntity<ErrorMessage> systemException(Exception e, WebRequest request) {
    return new ResponseEntity<>(new ErrorMessage("規定の例外"), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
