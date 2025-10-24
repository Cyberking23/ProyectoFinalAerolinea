package com.udb.proyectofinalaerolinea.controller.api;

import com.udb.proyectofinalaerolinea.api.ApiError;
import com.udb.proyectofinalaerolinea.dto.QuejaForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Quejas", description = "Reclamos de clientes")
@SecurityRequirement(name = "bearerAuth") // JWT requerido
@RestController
@RequestMapping("/api/v1/quejas")
public class QuejaRestDocController {

    @Operation(summary = "Crear una queja")
    @ApiResponse(responseCode = "201", description = "Queja registrada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(schema = @Schema(implementation = QuejaForm.class),
                    examples = @ExampleObject(value = """
            { "identificadorVuelo":"ACS1001",
              "asunto":"Retraso en el vuelo ACS1001",
              "descripcion":"El vuelo se retrasó 3 horas y perdí mi conexión." }""")))
    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody QuejaForm body) {
        return ResponseEntity.status(501).build();
    }
}
