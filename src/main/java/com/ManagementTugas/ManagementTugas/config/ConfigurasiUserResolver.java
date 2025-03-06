package com.ManagementTugas.ManagementTugas.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ManagementTugas.ManagementTugas.ArgumentResolver.UserDTOResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Validator;

@Configuration
public class ConfigurasiUserResolver implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;
    private final Validator validator;
    private final UserDTOResolver userDTOResolver;

    public ConfigurasiUserResolver(ObjectMapper objectMapper, Validator validator, UserDTOResolver userDTOResolver) {
        this.objectMapper = objectMapper;
        this.validator = validator;
        this.userDTOResolver = userDTOResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // TODO Auto-generated method stub
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new UserDTOResolver(objectMapper, validator));
    }

}
