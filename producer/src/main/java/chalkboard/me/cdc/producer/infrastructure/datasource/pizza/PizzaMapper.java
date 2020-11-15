package chalkboard.me.cdc.producer.infrastructure.datasource.pizza;

import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

@Mapper
public interface PizzaMapper {
  @Select("sql/pizza/select.sql")
  PizzaDto findPizza(Integer rid);
}
