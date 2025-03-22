package com.ejada.product.service.model.mapper;

import com.ejada.product.service.model.entity.Configuration;
import com.ejada.product.service.model.request.ConfigurationRequest;
import com.ejada.product.service.model.response.ConfigurationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConfigurationMapper {

    ConfigurationResponse mapToConfigurationResponse(Configuration configuration);

    Configuration mapToConfiguration(ConfigurationRequest configurationRequest);

    void mapToConfiguration(ConfigurationRequest configurationRequest, @MappingTarget Configuration configuration);

}
