
package com.springproject.ECommerceSystem.config;



import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.security.SecurityScheme;

import io.swagger.v3.oas.models.security.SecurityRequirement;
 
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Drive API Documentation")

                        .description("This is a sample API documentation using Springdoc OpenAPI")

                        .version("v1"))

                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))  // Add security requirement

                .components(new io.swagger.v3.oas.models.Components()

                        .addSecuritySchemes("basicAuth", new SecurityScheme()

                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .description("Basic Authentication")));  // Define Basic Authentication

    }

}

 