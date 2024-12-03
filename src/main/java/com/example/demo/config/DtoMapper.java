package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoMapper {


    @Bean
    @Qualifier("ModelMapperDto")
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }


}
