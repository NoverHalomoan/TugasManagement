package com.ManagementTugas.ManagementTugas.ArgumentResolver;

import java.io.BufferedReader;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

import com.ManagementTugas.ManagementTugas.DTOS.CreateUserDTO;
import com.ManagementTugas.ManagementTugas.controller.ApiResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class UserDTOResolver implements HandlerMethodArgumentResolver {

    private final ObjectMapper objectMapper;
    private final Validator validator;

    public UserDTOResolver(ObjectMapper objectMapper, Validator validator) {
        this.objectMapper = objectMapper;
        this.validator = validator;
    }

    @SuppressWarnings("static-access")
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
            ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {
        // TODO Auto-generated method stub

        // HttpServletRequest httpServletRequest = (HttpServletRequest)
        // nativeWebRequest.getNativeRequest();
        HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = httpServletRequest.getReader();
        String jsonrader = reader.lines().collect(Collectors.joining());

        CreateUserDTO createUserDTO = objectMapper.readValue(jsonrader, CreateUserDTO.class);
        Set<ConstraintViolation<CreateUserDTO>> violations = validator.validate(createUserDTO);
        // for (ConstraintViolation<CreateUserDTO> constraintViolation :
        // violations.stream()
        // .collect(Collectors.toList())) {
        // System.out.println(constraintViolation.getMessage());
        // }
        if (!violations.isEmpty()) {
            String ErrorMessage = violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
            // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorMessage);
            throw new IllegalArgumentException(ErrorMessage);
            // throw new ResponseEntity<>(HttpStatus.BAD_REQUEST).badRequest().body(new
            // ApiResponseData<>().setMessage(ErrorMessage)).build();
        }
        return createUserDTO;

    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // TODO Auto-generated method stub
        return parameter.getParameterType().equals(CreateUserDTO.class);
    }

}
