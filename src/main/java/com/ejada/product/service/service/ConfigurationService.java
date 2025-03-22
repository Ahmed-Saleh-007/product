package com.ejada.product.service.service;

import com.ejada.product.service.model.mapper.ConfigurationMapper;
import com.ejada.product.service.model.request.ConfigurationRequest;
import com.ejada.product.service.model.response.ConfigurationResponse;
import com.ejada.product.service.repository.facade.ConfigurationRepositoryFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.ejada.product.service.exception.CommonExceptionHandler.handleBadRequestException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigurationService {

    private final ConfigurationRepositoryFacade configurationRepositoryFacade;

    private final ConfigurationMapper configurationMapper;

    @Cacheable(cacheNames = "configurations", key = "#id")
    public ConfigurationResponse getConfigurationById(UUID id) {
        log.info("Get configuration by id: [{}]", id);
        return configurationRepositoryFacade.findById(id)
                .map(configurationMapper::mapToConfigurationResponse)
                .orElseThrow(() -> handleBadRequestException("Configuration not found"));
    }

    @CachePut(cacheNames = "configurations", key = "#result.id")
    public ConfigurationResponse createConfiguration(ConfigurationRequest request) {
        log.info("Create configuration: [{}]", request);
        return configurationMapper.mapToConfigurationResponse(
                configurationRepositoryFacade.save(configurationMapper.mapToConfiguration(request)));
    }

    @CachePut(cacheNames = "configurations", key = "#result.id")
    public ConfigurationResponse updateConfiguration(UUID id, ConfigurationRequest request) {
        log.info("Update configuration: [{}]", request);
        return configurationRepositoryFacade.findById(id)
                .map(configuration -> {
                    configurationMapper.mapToConfiguration(request, configuration);
                    return configurationRepositoryFacade.save(configuration);
                })
                .map(configurationMapper::mapToConfigurationResponse)
                .orElseThrow(() -> handleBadRequestException("Configuration not found"));
    }

}
