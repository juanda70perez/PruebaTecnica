package com.prueba.inicio.controller;

import com.prueba.inicio.model.EnvioMaritimo;
import com.prueba.inicio.service.EnvioMaritimoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios-maritimos")
@CrossOrigin(origins = "*")
@Tag(name = "Envíos Marítimos", description = "Gestión de Envíos Marítimos")
public class EnvioMaritimoController {
    
    @Autowired
    private EnvioMaritimoService envioMaritimoService;

    @PostMapping
    @Operation(summary = "Crear Envío Marítimo", description = "Crea un nuevo envío marítimo y lo guarda en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envío Marítimo creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioMaritimo.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<EnvioMaritimo> createEnvioMaritimo(@Valid @RequestBody EnvioMaritimo envioMaritimo) {
        EnvioMaritimo createdEnvioMaritimo = envioMaritimoService.saveEnvioMaritimo(envioMaritimo);
        return new ResponseEntity<>(createdEnvioMaritimo, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Envíos Marítimos", description = "Devuelve una lista de todos los envíos marítimos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de envíos marítimos obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioMaritimo.class))),
            @ApiResponse(responseCode = "204", description = "No hay envíos marítimos disponibles", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<List<EnvioMaritimo>> getAllEnviosMaritimos() {
        List<EnvioMaritimo> enviosMaritimos = envioMaritimoService.getAllEnviosMaritimos();
        return new ResponseEntity<>(enviosMaritimos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Envío Marítimo por ID", description = "Devuelve un envío marítimo específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío marítimo obtenido exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioMaritimo.class))),
            @ApiResponse(responseCode = "404", description = "Envío marítimo no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<EnvioMaritimo> getEnvioMaritimoById(@PathVariable Long id) {
        EnvioMaritimo envioMaritimo = envioMaritimoService.getEnvioMaritimoById(id);
        return envioMaritimo != null ? new ResponseEntity<>(envioMaritimo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Envío Marítimo", description = "Actualiza la información de un envío marítimo existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío marítimo actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioMaritimo.class))),
            @ApiResponse(responseCode = "404", description = "Envío marítimo no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<EnvioMaritimo> updateEnvioMaritimo(@PathVariable Long id,@Valid @RequestBody EnvioMaritimo envioMaritimoDetails) {
        EnvioMaritimo updatedEnvioMaritimo = envioMaritimoService.updateEnvioMaritimo(id, envioMaritimoDetails);
        return updatedEnvioMaritimo != null ? new ResponseEntity<>(updatedEnvioMaritimo, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Envío Marítimo", description = "Elimina un envío marítimo específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envío marítimo eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Envío marítimo no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<String> deleteEnvioMaritimo(@PathVariable Long id) {
        boolean validacion = envioMaritimoService.deleteEnvioMaritimo(id);
        if (!validacion) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envío marítimo no encontrado");
        }
        return new ResponseEntity<>("Envío marítimo eliminado exitosamente", HttpStatus.OK);
    }

}