package com.ejada.product.service.controller;

import com.ejada.product.service.exception.ApiBusinessErrorResponse;
import com.ejada.product.service.model.request.LoginRequest;
import com.ejada.product.service.model.request.UserRequest;
import com.ejada.product.service.model.response.TokenResponse;
import com.ejada.product.service.model.response.UserResponse;
import com.ejada.product.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(description = "Create user", summary = "Create user", tags = "Users")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(request));
    }

    @PostMapping("/login")
    @Operation(description = "Login", summary = "Login", tags = "Users")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<TokenResponse> login(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(request));
    }

    @PostMapping("/login-rest-template")
    @Operation(description = "Login", summary = "Login", tags = "Users")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<TokenResponse> loginRestTemplate(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginRestTemplate(request));
    }

    @PostMapping("/login-web-client")
    @Operation(description = "Login", summary = "Login", tags = "Users")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<TokenResponse> loginWebClient(
            @Valid @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginWebClient(request));
    }

    @PostMapping("/login-web-client-reactive")
    @Operation(description = "Login", summary = "Login", tags = "Users")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public Mono<TokenResponse> loginReactiveWebClient(
            @Valid @RequestBody LoginRequest request) {
        return userService.loginReactiveWebClient(request);
    }


}
