package com.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.modelmapper.ModelMapper;

/**
 * @author davidjmartin
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper () {
        return new ModelMapper();
    }

}
