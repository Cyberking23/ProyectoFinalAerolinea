package com.udb.proyectofinalaerolinea.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGlobalResponsesConfig {

    @Bean
    public OpenApiCustomizer globalResponsesCustomizer() {
        return openApi -> {
            // Reutiliza tu ApiError como $ref si lo registras en components (opcional)
            Schema<?> apiErrorSchema = new Schema<>().name("ApiError");
            openApi.components(new Components().addSchemas("ApiError", apiErrorSchema));

            openApi.getPaths().values().forEach(pathItem ->
                    pathItem.readOperations().forEach(op -> {
                        op.getResponses().addApiResponse("401",
                                new ApiResponse().description("No autenticado (JWT requerido)"));
                        op.getResponses().addApiResponse("403",
                                new ApiResponse().description("Prohibido"));
                        op.getResponses().addApiResponse("500",
                                new ApiResponse().description("Error interno"));
                    })
            );
        };
    }
}
