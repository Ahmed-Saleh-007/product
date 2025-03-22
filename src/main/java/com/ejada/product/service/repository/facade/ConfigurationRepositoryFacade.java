package com.ejada.product.service.repository.facade;

import com.ejada.product.service.model.entity.Configuration;
import com.ejada.product.service.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.ejada.product.service.exception.CommonExceptionHandler.handleInternalServerErrorException;
import static com.ejada.product.service.util.Constants.DATABASE_GENERAL_ERROR_MESSAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConfigurationRepositoryFacade {

    private final ConfigurationRepository configurationRepository;

    public Optional<Configuration> findById(UUID id) {
        log.info("Find configuration by id ConfigurationRepositoryFacade: [{}]", id);
        try {
            return configurationRepository.findById(id);
        } catch (Exception e) {
            log.error("Error occurred while finding configuration by id ConfigurationRepositoryFacade: [{}]", id);
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

    public Optional<Configuration> findByKey(String key) {
        log.info("Find configuration by key ConfigurationRepositoryFacade: [{}]", key);
        try {
            return configurationRepository.findByKey(key);
        } catch (Exception e) {
            log.error("Error occurred while finding configuration by key ConfigurationRepositoryFacade: [{}]", key);
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

    public Configuration save(Configuration configuration) {
        log.info("Save configuration ConfigurationRepositoryFacade: [{}]", configuration);
        try {
            return configurationRepository.save(configuration);
        } catch (Exception e) {
            log.error("Error occurred while saving configuration ConfigurationRepositoryFacade: [{}]", configuration);
            throw handleInternalServerErrorException(DATABASE_GENERAL_ERROR_MESSAGE);
        }
    }

}
