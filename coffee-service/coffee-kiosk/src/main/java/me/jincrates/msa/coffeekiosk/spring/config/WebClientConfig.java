package me.jincrates.msa.coffeekiosk.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                //.exchangeStrategies(ExchangeStrategies.builder().codecs(this::acceptedCodecs).build())
                .build();
    }

//    private void acceptedCodecs(ClientCodecConfigurer clientCodecConfigurer) {
//        clientCodecConfigurer.customCodecs()
//                .register(new Jackson2JsonEncoder(new ObjectMapper(), TEXT_HTML));
//        clientCodecConfigurer.customCodecs()
//                .register(new Jackson2JsonDecoder(new ObjectMapper(), TEXT_HTML));
//    }
}
