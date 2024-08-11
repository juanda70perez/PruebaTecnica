package com.prueba.inicio.controller;

import com.prueba.inicio.model.Puerto;
import com.prueba.inicio.service.PuertoService;

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
@RequestMapping("/api/puertos")
@CrossOrigin(origins = "*")
@Tag(name = "Puertos", description = "Gestión de Puertos")
public class PuertoController {

    @Autowired
    private PuertoService puertoService;

    @PostMapping
    @Operation(summary = "Crear Puerto", description = "Crea un nuevo puerto y lo guarda en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Puerto creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Puerto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<Puerto> createPuerto(@Valid @RequestBody Puerto puerto) {
        Puerto createdPuerto = puertoService.savePuerto(puerto);
        return new ResponseEntity<>(createdPuerto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Puertos", description = "Devuelve una lista de todos los puertos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de puertos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Puerto.class))),
            @ApiResponse(responseCode = "204", description = "No hay puertos disponibles", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<List<Puerto>> getAllPuertos() {
        List<Puerto> puertos = puertoService.getAllPuertos();
        return new ResponseEntity<>(puertos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Puerto por ID", description = "Devuelve un puerto específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Puerto obtenido exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Puerto.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Puerto no encontrado", content = @Content)
    })
    public ResponseEntity<Puerto> getPuertoById(@PathVariable Long id) {
        Puerto puerto = puertoService.getPuertoById(id);
        return puerto != null ? new ResponseEntity<>(puerto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Puerto", description = "Actualiza la información de un puerto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Puerto actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Puerto.class))),
            @ApiResponse(responseCode = "404", description = "Puerto no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public ResponseEntity<Puerto> updatePuerto(@PathVariable Long id,@Valid @RequestBody Puerto puertoDetails) {
        Puerto updatedPuerto = puertoService.updatePuerto(id, puertoDetails);
        return updatedPuerto != null ? new ResponseEntity<>(updatedPuerto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Puerto", description = "Elimina un puerto específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Puerto eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Puerto no encontrado", content = @Content)
    })
    public ResponseEntity<String> deletePuerto(@PathVariable Long id) {
        boolean validacion = puertoService.deletePuerto(id);
        if (!validacion) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Puerto no encontrado");
        }
        return new ResponseEntity<>("Puerto eliminado exitosamente", HttpStatus.OK);
    }
}
