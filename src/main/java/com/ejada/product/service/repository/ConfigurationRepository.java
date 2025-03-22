package com.ejada.product.service.repository;

import com.ejada.product.service.model.entity.Configuration;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConfigurationRepository extends CrudRepository<Configuration, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE) // need open Transaction use @Transactional
    Optional<Configuration> findByKey(String key);

}
