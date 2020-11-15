package chalkboard.me.cdc.producer.presentation.controller.pizza.exception;

import org.apache.ibatis.javassist.NotFoundException;

public class PizzaNotFoundException extends NotFoundException {
  public PizzaNotFoundException(String msg) {
    super(msg);
  }
}
