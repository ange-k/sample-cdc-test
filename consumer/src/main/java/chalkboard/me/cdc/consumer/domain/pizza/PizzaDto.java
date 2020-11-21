package chalkboard.me.cdc.consumer.domain.pizza;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * データを流すだけならDtoをDomainに置く.
 * ビジネスロジックのために利用するならDtoとしてインフラにおいて、相応のクラスに変換すべき.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PizzaDto {
  private Integer id;
  private String pizza;
  private List<String> topping;
}
