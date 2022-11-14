package com.sdsu;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
@AllArgsConstructor
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("Product Service")
                .description("San Diego State University Academic project - Product Service")
                .version("v1.0.0")
                .license(getLicense());
    }

    private License getLicense() {
        return new License()
                .name("Ajay Vishwakarma")
                .url("https://sdsu.edu/");
    }


}
