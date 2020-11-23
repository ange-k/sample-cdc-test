package chalkboard.me.cdc.consumer.infrastructure.transfer.pizza;

import chalkboard.me.cdc.consumer.infrastructure.transfer.config.BaseTransferConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;

@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "transfer.pizza")
@Getter
public class PizzaConfig extends BaseTransferConfig {
  private final String url;
  private final String findPath;
  private final String reservationPath;
  private final Integer connectionTimeout;
  private final Integer readTimeout;

  public WebClient pizzaClient() {
    // jackson系のエンコーダ・デコーダを利用
    ExchangeStrategies strategies = ExchangeStrategies
        .builder()
        .codecs(clientDefaultCodecsConfigurer -> {
          clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
          clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(new ObjectMapper(), MediaType.APPLICATION_JSON));
        }).build();

    return WebClient.builder()
        .clientConnector(getDefaultClientConnector().apply(this.connectionTimeout, this.readTimeout))
        .baseUrl(this.url)
        .exchangeStrategies(strategies)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
