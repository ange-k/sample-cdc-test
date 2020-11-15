package chalkboard.me.cdc.producer.infrastructure.datasource.pizza;

import chalkboard.me.cdc.producer.model.Pizza;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PizzaDto implements Serializable {
  private Integer id;
  private String pizza;
  private String topping;

  public Pizza toEntity() {
    return new Pizza()
        .id(id)
        .pizza(pizza)
        .topping(Arrays.asList(topping.split(",")));
  }
}
