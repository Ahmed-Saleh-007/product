package com.ejada.product.service.util;

import com.ejada.product.service.exception.UserWebClientExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(new UserWebClientExceptionHandler(objectMapper).handleError());
    }

}
