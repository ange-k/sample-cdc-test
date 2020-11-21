package chalkboard.me.cdc.consumer.infrastructure.external.pizza;

import chalkboard.me.cdc.consumer.domain.pizza.PizzaDto;
import chalkboard.me.cdc.consumer.domain.pizza.PizzaRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"chalkboard.me.cdc:producer:+:stubs:8585"},
    stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class PizzaExternalTests {
  @Autowired
  private PizzaRepository pizzaRepository;

  @Test
  void Producerに対してgetする() {
    PizzaDto dto = pizzaRepository.findPizza(2); // ID=2がstubのkeyであることを知るにはcontractを見る必要がある...
    assertNotNull(dto);
    assertNotNull(dto.getId());
    assertNotNull(dto.getPizza());
    assertTrue(dto.getId() > 0 && Strings.isNotEmpty(dto.getPizza()));
    assertTrue(CollectionUtils.isNotEmpty(dto.getTopping()));
  }
}
