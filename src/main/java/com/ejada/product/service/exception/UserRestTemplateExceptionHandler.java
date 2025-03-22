package com.ejada.product.service.exception;

import com.ejada.product.service.client.user.model.response.UserClientErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.ejada.product.service.exception.CommonExceptionHandler.handleBadRequestException;
import static com.ejada.product.service.exception.CommonExceptionHandler.handleInternalServerErrorException;

@Slf4j
@RequiredArgsConstructor
public class UserRestTemplateExceptionHandler implements ResponseErrorHandler {

    private final ObjectMapper objectMapper;

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
        log.error("Client status: {}, error body: {}", response.getStatusCode().value(), body);
        try {
            UserClientErrorResponse userClientErrorResponse =
                    objectMapper.readValue(body, UserClientErrorResponse.class);
            throw handleBadRequestException(userClientErrorResponse.getError());
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error decoding response", e);
            throw handleInternalServerErrorException("General error occurred");
        }
    }

}
