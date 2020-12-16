package chalkboard.me.cdc.producer.presentation.controller.pizza.exception;

public class PizzaNotFoundException extends RuntimeException {
  public PizzaNotFoundException(String msg) {
    super(msg);
  }
}
