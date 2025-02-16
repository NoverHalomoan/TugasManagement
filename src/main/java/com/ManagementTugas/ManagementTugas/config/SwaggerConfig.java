package com.ManagementTugas.ManagementTugas.config;

import java.time.LocalDateTime;

import org.springdoc.core.models.GroupedOpenApi;
//import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi ptugasmanagement() {
        return GroupedOpenApi.builder().displayName("Project API Management Tugas").group("project-pmanagement")
                .pathsToMatch("/api/tugas-management/**")
                .build();
    }

    // @Bean
    // public WebMvcConfigurer corsConfigurer() {

    // return new WebMvcConfigurer() {
    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    // registry.addMapping("/**")
    // .allowedOrigins("*")
    // .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    // .allowedHeaders("*");
    // }
    // };

    // }

}
