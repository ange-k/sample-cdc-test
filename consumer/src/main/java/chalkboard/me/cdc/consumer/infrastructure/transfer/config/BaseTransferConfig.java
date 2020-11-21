package chalkboard.me.cdc.consumer.infrastructure.transfer.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

abstract public class BaseTransferConfig {
  private final BiFunction<Integer, Integer, ReactorClientHttpConnector> clientConnector =
      (connectTimeout, readTimeout) ->
          new ReactorClientHttpConnector(HttpClient.newConnection()
              .tcpConfiguration(tcpClient -> tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                  .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))))
          );

  protected final BiFunction<Integer, Integer, ReactorClientHttpConnector> getDefaultClientConnector() {
    return this.clientConnector;
  }
}