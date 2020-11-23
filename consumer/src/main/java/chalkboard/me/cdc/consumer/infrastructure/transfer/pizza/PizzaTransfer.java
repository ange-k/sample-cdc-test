package chalkboard.me.cdc.consumer.infrastructure.transfer.pizza;

import chalkboard.me.cdc.consumer.domain.pizza.PizzaRepository;
import chalkboard.me.cdc.producer.model.Pizza;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PizzaTransfer implements PizzaRepository {
  private final PizzaConfig pizzaConfig;

  @Override
  public Pizza findPizza(Integer rid) {
    return pizzaConfig.pizzaClient()
        .get()
        .uri(pizzaConfig.getFindPath(), rid)
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse -> {
          log.error("ピザ取得失敗" + clientResponse.statusCode().toString());
          return Mono.error(new RuntimeException());
        })
        .bodyToMono(Pizza.class)
        .block(); // SpringWebベースなのでblockしているが、FluxでやるならMonoを返してノンブロッキングな作りにしていく
  }

  @Override
  public void addPizza(Pizza pizza) {
    pizzaConfig.pizzaClient()
      .post()
      .uri(pizzaConfig.getReservationPath())
      .body(Mono.just(pizza), Pizza.class)
      .retrieve()
      .onStatus(HttpStatus::isError, clientResponse -> {
        log.error("ピザ登録失敗" + clientResponse.statusCode().toString());
        return Mono.error(new RuntimeException());
      })
      .bodyToMono(Void.class)
      .block();
  }
}
