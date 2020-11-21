package chalkboard.me.cdc.consumer.infrastructure.transfer.pizza;

import chalkboard.me.cdc.consumer.infrastructure.transfer.config.BaseTransferConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.http.MediaType;
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
    return WebClient.builder()
        .clientConnector(getDefaultClientConnector().apply(this.connectionTimeout, this.readTimeout))
        .baseUrl(this.url)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}
