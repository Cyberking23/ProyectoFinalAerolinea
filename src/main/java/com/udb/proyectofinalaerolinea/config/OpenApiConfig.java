package com.udb.proyectofinalaerolinea.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aerolinea API")
                        .version("v1")
                        .description("API de vuelos, aviones, aerolíneas, reservas y quejas")
                        .contact(new Contact().name("Equipo Aerolínea").email("soporte@aerolinea.test")))
                // Seguridad global: todas las operaciones requieren JWT salvo que se anoten distinto
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }

    // Grupos por dominio para que se vean ordenados en Swagger UI
    @Bean GroupedOpenApi authApi()       { return GroupedOpenApi.builder().group("auth").pathsToMatch("/auth/**").build(); }
    @Bean GroupedOpenApi vuelosApi()     { return GroupedOpenApi.builder().group("vuelos").pathsToMatch("/api/v1/vuelos/**").build(); }
    @Bean GroupedOpenApi quejasApi()     { return GroupedOpenApi.builder().group("quejas").pathsToMatch("/api/v1/quejas/**").build(); }
    @Bean GroupedOpenApi adminsApi()  { return GroupedOpenApi.builder().group("admins").pathsToMatch("/api/v1/admins/**", "/api/v1/usuarios/**").build(); }
    @Bean GroupedOpenApi clientesApi(){ return GroupedOpenApi.builder().group("clientes").pathsToMatch("/api/v1/clientes/**").build(); }

}
