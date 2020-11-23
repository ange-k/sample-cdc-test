package chalkboard.me.cdc.consumer.domain.pizza;

import chalkboard.me.cdc.producer.model.Pizza;

public interface PizzaRepository {
  Pizza findPizza(Integer rid);
  void addPizza(Pizza pizza);
}
