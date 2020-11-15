package chalkboard.me.cdc.producer.presentation.controller.pizza;

import chalkboard.me.cdc.producer.api.ReservationApiDelegate;
import chalkboard.me.cdc.producer.domain.entity.pizza.PizzaRepository;
import chalkboard.me.cdc.producer.model.Pizza;
import chalkboard.me.cdc.producer.presentation.controller.exception.SystemException;
import chalkboard.me.cdc.producer.presentation.controller.pizza.exception.PizzaNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReservationDelegateImpl implements ReservationApiDelegate {

  private final PizzaRepository pizzaRepository;

  @Override
  public ResponseEntity<Pizza> getReservationRid(Integer rid) {
    try {
      Pizza pizza = pizzaRepository.findPizza(rid);
      if(Objects.isNull(pizza)) {
        throw new PizzaNotFoundException("ピザがありませんでした");
      }
      return new ResponseEntity<>(pizza, HttpStatus.OK);
    } catch (Exception e) {
      throw new SystemException("ピザ取得時にサーバーエラー");
    }
  }

  @Override
  public ResponseEntity<Void> postReservation(Pizza pizza) {
    return null;
  }
}
