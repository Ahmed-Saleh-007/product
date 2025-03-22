package com.ejada.product.service.exception;

import com.ejada.product.service.client.user.model.response.UserClientErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

import static com.ejada.product.service.exception.CommonExceptionHandler.handleBadRequestException;
import static com.ejada.product.service.exception.CommonExceptionHandler.handleInternalServerErrorException;

@Slf4j
@RequiredArgsConstructor
public class UserWebClientExceptionHandler {

    private final ObjectMapper objectMapper;

    public ExchangeFilterFunction handleError() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is4xxClientError() || clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            log.error("Client status: {}, error body: {}", clientResponse.statusCode().value(), body);
                            try {
                                UserClientErrorResponse userClientErrorResponse =
                                        objectMapper.readValue(body, UserClientErrorResponse.class);
                                return Mono.error(handleBadRequestException(userClientErrorResponse.getError()));
                            } catch (Exception e) {
                                log.error("Error decoding response", e);
                                return Mono.error(handleInternalServerErrorException("General error occurred"));
                            }
                        });
            }
            return Mono.just(clientResponse);
        });
    }
}
