package com.sdsu;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("Account Service")
                .description("San Diego State University Academic project - Account Service")
                .version("v1.0.0")
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("Ajay Vishwakarma")
                .url("https://sdsu.edu/");
    }
}
