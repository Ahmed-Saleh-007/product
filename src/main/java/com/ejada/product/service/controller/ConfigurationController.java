package com.ejada.product.service.controller;

import com.ejada.product.service.exception.ApiBusinessErrorResponse;
import com.ejada.product.service.model.request.ConfigurationRequest;
import com.ejada.product.service.model.response.ConfigurationResponse;
import com.ejada.product.service.service.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
@RequestMapping("/api/configuration")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationService configurationService;

    @GetMapping("/{id}")
    @Operation(description = "Get Configuration By id", summary = "Get Configuration By id", tags = "Configuration")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = ConfigurationResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<ConfigurationResponse> getConfigurationById(@PathVariable UUID id) {
        return ResponseEntity.ok(configurationService.getConfigurationById(id));
    }

    @PostMapping
    @Operation(description = "Create Configuration", summary = "Create Configuration", tags = "Configuration")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = ConfigurationResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<ConfigurationResponse> createConfiguration(
            @Valid @RequestBody ConfigurationRequest request) {
        return ResponseEntity.ok(configurationService.createConfiguration(request));
    }

    @PutMapping("/{id}")
    @Operation(description = "Update Configuration", summary = "Update Configuration", tags = "Configuration")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(schema = @Schema(implementation = ConfigurationResponse.class)))
    @ApiResponse(responseCode = "400", description = "Bad request",
            content = @Content(schema = @Schema(implementation = ApiBusinessErrorResponse.class)))
    public ResponseEntity<ConfigurationResponse> updateConfiguration(
            @PathVariable UUID id, @Valid @RequestBody ConfigurationRequest request) {
        return ResponseEntity.ok(configurationService.updateConfiguration(id, request));
    }

}
