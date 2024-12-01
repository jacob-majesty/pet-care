package com.majesty.pet_care.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.modelmapper.ModelMapper;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
