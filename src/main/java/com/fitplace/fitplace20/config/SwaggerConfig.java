package com.fitplace.fitplace20.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "FitPlace API", version = "2.0", description = "API para gerenciamento de exercícios"))
public class SwaggerConfig {
}
