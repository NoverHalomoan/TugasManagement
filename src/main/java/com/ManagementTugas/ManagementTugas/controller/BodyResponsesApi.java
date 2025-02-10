package com.ManagementTugas.ManagementTugas.controller;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfull operation", content = @Content(mediaType = "application/json", schema = @Schema(hidden = true, implementation = BodyResponsesApi.class))),
        @ApiResponse(responseCode = "403", description = "Invalid request", content = @Content(mediaType = "application/json", schema = @Schema(hidden = true, implementation = BodyResponsesApi.class)))
})
public @interface BodyResponsesApi {
    Class<?> successresponse();

    Class<?> errorresponse();
}
