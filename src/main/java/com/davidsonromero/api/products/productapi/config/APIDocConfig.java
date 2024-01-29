package com.davidsonromero.api.products.productapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.web.bind.annotation.RestController;

@RestController
@OpenAPIDefinition(
        info = @Info(
                title = "Product API",
                version = "1.0",
                description = "Documentation for Product API v1.0"
        )
)
public class APIDocConfig {

}
