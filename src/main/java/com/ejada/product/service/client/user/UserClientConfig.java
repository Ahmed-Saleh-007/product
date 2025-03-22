package com.ejada.product.service.client.user;

import com.ejada.product.service.client.user.model.response.UserClientErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Reader;
import java.nio.charset.StandardCharsets;

import static com.ejada.product.service.exception.CommonExceptionHandler.handleBadRequestException;
import static com.ejada.product.service.exception.CommonExceptionHandler.handleInternalServerErrorException;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class UserClientConfig {

    private final ObjectMapper objectMapper;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            if (response != null && response.body() != null) {
                try (final Reader reader = response.body().asReader(StandardCharsets.UTF_8)) {
                    String clientError = IOUtils.toString(reader);
                    log.error("Client status: {}, error: {}", response.status(), clientError);
                    UserClientErrorResponse userClientErrorResponse = objectMapper
                            .readValue(clientError, UserClientErrorResponse.class);
                    return handleBadRequestException(userClientErrorResponse.getError());
                } catch (Exception e) {
                    log.error("Error decoding response", e);
                    throw handleInternalServerErrorException("General error occurred");
                }
            }
            throw handleInternalServerErrorException("General error occurred");
        };
    }

}
