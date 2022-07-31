package com.hrmelo.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
