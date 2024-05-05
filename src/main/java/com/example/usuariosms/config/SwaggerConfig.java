package com.example.usuariosms.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Gerenciamento de Cursos - Microsserviço Usuários")
                        .description("Microsserviço de usuários em uma aplicação de gerenciamento de cursos.")
                        .version("1.0").contact(new Contact().name("Victor Silva")
                                .email("victorhfsilva@protonmail.com")));
    }

}