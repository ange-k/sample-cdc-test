package chalkboard.me.cdc.producer.infrastructure.datasource.pizza;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PizzaMapper {
  @Select("sql/pizza/select.sql")
  PizzaDto findPizza(@Param("rid") Integer rid);

  @Insert("sql/pizza/insert.sql")
  void addPizza(@Param("dto") PizzaDto dto);
}
