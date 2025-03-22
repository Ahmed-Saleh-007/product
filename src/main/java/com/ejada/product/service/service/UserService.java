package com.ejada.product.service.service;

import com.ejada.product.service.client.user.UserClient;
import com.ejada.product.service.client.user.model.response.TokenClientResponse;
import com.ejada.product.service.model.mapper.UserMapper;
import com.ejada.product.service.model.request.LoginRequest;
import com.ejada.product.service.model.request.UserRequest;
import com.ejada.product.service.model.response.TokenResponse;
import com.ejada.product.service.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    @Value("${client.user.url}")
    private String userClientUrl;

    @Value("${client.user.login-path}")
    private String userLoginClientPath;

    public UserResponse createUser(UserRequest request) {
        return userMapper.mapToUserResponse(userClient.createUser(userMapper.mapToUserClientRequest(request)));
    }

    public TokenResponse login(LoginRequest request) {
        return userMapper.mapToTokenResponse(userClient.login(userMapper.mapToLoginClientRequest(request)));
    }

    public TokenResponse loginRestTemplate(LoginRequest request) {
        return userMapper.mapToTokenResponse(restTemplate
                .postForEntity(userClientUrl.concat(userLoginClientPath),
                        userMapper.mapToLoginClientRequest(request),
                        TokenClientResponse.class)
                .getBody());
    }

    public TokenResponse loginWebClient(LoginRequest request) {
        return webClientBuilder.baseUrl(userClientUrl)
                .build()
                .post()
                .uri(userLoginClientPath)
                .bodyValue(userMapper.mapToLoginClientRequest(request))
                .retrieve()
                .bodyToMono(TokenClientResponse.class)
                .map(userMapper::mapToTokenResponse)
                .block();
    }

    public Mono<TokenResponse> loginReactiveWebClient(LoginRequest request) {
        return webClientBuilder.baseUrl(userClientUrl)
                .build()
                .post()
                .uri(userLoginClientPath)
                .bodyValue(userMapper.mapToLoginClientRequest(request))
                .retrieve()
                .bodyToMono(TokenClientResponse.class)
                .map(userMapper::mapToTokenResponse);
    }

}
