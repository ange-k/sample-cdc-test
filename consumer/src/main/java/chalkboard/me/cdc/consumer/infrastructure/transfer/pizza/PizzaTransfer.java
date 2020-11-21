package chalkboard.me.cdc.consumer.infrastructure.transfer.pizza;

import chalkboard.me.cdc.consumer.domain.pizza.PizzaDto;
import chalkboard.me.cdc.consumer.domain.pizza.PizzaRepository;
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
  public PizzaDto findPizza(Integer rid) {
    return pizzaConfig.pizzaClient()
        .get()
        .uri(pizzaConfig.getFindPath(), rid)
        .retrieve()
        .onStatus(HttpStatus::isError, clientResponse -> {
          log.error("ピザ取得失敗" + clientResponse.statusCode().toString());
          return Mono.error(new RuntimeException());
        })
        .bodyToMono(PizzaDto.class)
        .block(); // SpringWebベースなのでblockしているが、FluxでやるならMonoを返してノンブロッキングな作りにしていく
  }

  @Override
  public void addPizza(PizzaDto pizza) {
    pizzaConfig.pizzaClient()
      .post()
      .uri(pizzaConfig.getReservationPath())
      .body(Mono.just(pizza), PizzaDto.class)
      .retrieve()
      .onStatus(HttpStatus::isError, clientResponse -> {
        log.error("ピザ登録失敗" + clientResponse.statusCode().toString());
        return Mono.error(new RuntimeException());
      })
      .bodyToMono(Void.class)
      .block();
  }
}
