package com.codebake.springerp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    
    @Value("${spring.application.name}")
    private String applicationName;
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(applicationName + " API")
                        .version("1.0.0")
                        .description("Spring ERP Application REST API Documentation")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("CodeBake Team")
                                .email("contact@codebake.com")
                                .url("https://codebake.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Development Server"),
                        new Server().url("http://localhost:8080/api").description("API Base Path")
                ));
    }
}