package me.jincrates.msa.coffeekiosk.spring.global.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.TEXT_HTML;

@Configuration
public class WebClientConfig {

    private final int CONNECT_TIMEOUT_MILLIS = 5_000;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(createConnector())
                .exchangeStrategies(createExchangeStrategies())
                .build();
    }

    public ReactorClientHttpConnector createConnector() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_MILLIS)
                .responseTimeout(Duration.ofMillis(CONNECT_TIMEOUT_MILLIS))
                .doOnConnected(conn ->
                        conn.addHandlerLast(
                                        new ReadTimeoutHandler(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                                .addHandlerLast(
                                        new WriteTimeoutHandler(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)));

        return new ReactorClientHttpConnector(httpClient);
    }

    private ExchangeStrategies createExchangeStrategies() {
        return ExchangeStrategies.builder()
                .codecs(this::acceptedCodecs)
                .build();
    }

    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
        clientCodecConfigurer.customCodecs()
                .register(new Jackson2JsonEncoder(new ObjectMapper(), TEXT_HTML));
        clientCodecConfigurer.customCodecs()
                .register(new Jackson2JsonDecoder(new ObjectMapper(), TEXT_HTML));
    }
}
