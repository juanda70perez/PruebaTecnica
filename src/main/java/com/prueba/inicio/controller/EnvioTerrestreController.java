package com.prueba.inicio.controller;

import com.prueba.inicio.model.EnvioTerrestre;
import com.prueba.inicio.service.EnvioTerrestreService;

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
@RequestMapping("/api/envios-terrestres")
@CrossOrigin(origins = "*")
@Tag(name = "Envíos Terrestres", description = "Gestión de Envíos Terrestres")
public class EnvioTerrestreController {
    
    @Autowired
    
    private EnvioTerrestreService envioTerrestreService;

    @PostMapping
    @Operation(summary = "Crear Envío Terrestre", description = "Crea un nuevo envío terrestre y lo guarda en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envío Terrestre creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioTerrestre.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<EnvioTerrestre> createEnvioTerrestre(@Valid @RequestBody EnvioTerrestre envioTerrestre) {
        EnvioTerrestre createdEnvioTerrestre = envioTerrestreService.saveEnvioTerrestre(envioTerrestre);
        return new ResponseEntity<>(createdEnvioTerrestre, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Envíos Terrestres", description = "Devuelve una lista de todos los envíos terrestres.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de envíos terrestres obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioTerrestre.class))),
            @ApiResponse(responseCode = "204", description = "No hay envíos terrestres disponibles", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<List<EnvioTerrestre>> getAllEnviosTerrestres() {
        List<EnvioTerrestre> enviosTerrestres = envioTerrestreService.getAllEnviosTerrestres();
        return new ResponseEntity<>(enviosTerrestres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Envío Terrestre por ID", description = "Devuelve un envío terrestre específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío terrestre obtenido exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioTerrestre.class))),
            @ApiResponse(responseCode = "404", description = "Envío terrestre no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<EnvioTerrestre> getEnvioTerrestreById(@PathVariable Long id) {
        EnvioTerrestre envioTerrestre = envioTerrestreService.getEnvioTerrestreById(id);
        return envioTerrestre != null ? new ResponseEntity<>(envioTerrestre, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Envío Terrestre", description = "Actualiza la información de un envío terrestre existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío terrestre actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnvioTerrestre.class))),
            @ApiResponse(responseCode = "404", description = "Envío terrestre no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<EnvioTerrestre> updateEnvioTerrestre(@PathVariable Long id,@Valid @RequestBody EnvioTerrestre envioTerrestreDetails) {
        EnvioTerrestre updatedEnvioTerrestre = envioTerrestreService.updateEnvioTerrestre(id, envioTerrestreDetails);
        return updatedEnvioTerrestre != null ? new ResponseEntity<>(updatedEnvioTerrestre, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Envío Terrestre", description = "Elimina un envío terrestre específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envío terrestre eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Envío terrestre no encontrado", content = @Content)
    })
    public ResponseEntity<String> deleteEnvioTerrestre(@PathVariable Long id) {
        boolean validacion = envioTerrestreService.deleteEnvioTerrestre(id);
        if (!validacion) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envío terrestre no encontrado");
        }
        return new ResponseEntity<>("Envio terrestre eliminado exitosamente", HttpStatus.OK);
    }
}
