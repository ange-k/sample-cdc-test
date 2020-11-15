package chalkboard.me.cdc.producer.domain.entity.pizza;

import chalkboard.me.cdc.producer.model.Pizza;

public interface PizzaRepository {
  Pizza findPizza(Integer rid);
}
