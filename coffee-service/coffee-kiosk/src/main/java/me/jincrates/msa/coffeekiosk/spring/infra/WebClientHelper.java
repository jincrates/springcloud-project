package me.jincrates.msa.coffeekiosk.spring.infra;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientHelper {

    private final WebClient client;

    public ResponseSpec get(String uri) {
        return client.get()
            .uri(uri)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve();
    }

    public ResponseSpec get(String uri, Map<String, String> headers) {
        return client.get()
            .uri(uri)
            .headers(getHttpHeadersConsumer(headers))
            .accept(MediaType.APPLICATION_JSON)
            .retrieve();
    }

    public ResponseSpec post(String uri, Object request) {
        return client.post()
            .uri(uri)
            .bodyValue(request)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve();
    }

    public ResponseSpec post(String uri, Map<String, String> headers, Object request) {
        return client.post()
            .uri(uri)
            .headers(getHttpHeadersConsumer(headers))
            .bodyValue(request)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve();
    }

    private Consumer<HttpHeaders> getHttpHeadersConsumer(Map<String, String> headers) {
        return httpHeaders -> {
            if (headers != null) {
                for (Entry<String, String> entry : headers.entrySet()) {
                    httpHeaders.add(entry.getKey(), entry.getValue());
                }
            }
        };
    }
}
