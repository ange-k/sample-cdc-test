package chalkboard.me.cdc.consumer.domain.pizza;

public interface PizzaRepository {
  PizzaDto findPizza(Integer rid);
  void addPizza(PizzaDto pizza);
}
