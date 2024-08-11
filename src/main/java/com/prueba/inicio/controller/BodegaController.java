package com.prueba.inicio.controller;

import com.prueba.inicio.model.Bodega;
import com.prueba.inicio.service.BodegaService;

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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Validated
@RequestMapping("/api/bodegas")
@Tag(name = "Bodegas", description = "Gestión de Bodegas")
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    @PostMapping
    @Operation(summary = "Crear Bodega", description = "Crea una nueva bodega y la guarda en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bodega creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public ResponseEntity<Bodega> createBodega(@Valid @RequestBody Bodega bodega) {
        Bodega createdBodega = bodegaService.saveBodega(bodega);
        return new ResponseEntity<>(createdBodega, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todas las Bodegas", description = "Devuelve una lista de todas las bodegas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de bodegas obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "204", description = "No hay bodegas disponibles", content = @Content)
    })
    public ResponseEntity<List<Bodega>> getAllBodegas() {
        List<Bodega> bodegas = bodegaService.getAllBodegas();
        return new ResponseEntity<>(bodegas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Bodega por ID", description = "Devuelve una bodega específica según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodega obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content)
    })
    public ResponseEntity<Bodega> getBodegaById(@PathVariable Long id) {
        Bodega bodega = bodegaService.getBodegaById(id);
        return bodega != null ? new ResponseEntity<>(bodega, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Bodega", description = "Actualiza la información de una bodega existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bodega actualizada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class))),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public ResponseEntity<Bodega> updateBodega(@PathVariable Long id, @Valid @RequestBody Bodega bodegaDetails) {
        Bodega updatedBodega = bodegaService.updateBodega(id, bodegaDetails);
        return updatedBodega != null ? new ResponseEntity<>(updatedBodega, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Bodega", description = "Elimina una bodega específica según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bodega eliminada exitosamente", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content)
    })
    public ResponseEntity<String> deleteBodega(@PathVariable Long id) {
        boolean validacion = bodegaService.deleteBodega(id);
        if (!validacion) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bodega no encontrado");
        }
        return new ResponseEntity<>("Bodega eliminada exitosamente", HttpStatus.OK);
    }
}
