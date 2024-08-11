package com.prueba.inicio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.inicio.DTO.user.UserDTO;
import com.prueba.inicio.DTO.user.UserRequest;
import com.prueba.inicio.DTO.user.UserResponse;
import com.prueba.inicio.model.Bodega;
import com.prueba.inicio.model.User;
import com.prueba.inicio.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Usuarios", description = "Gestión de Usuarios")
public class UserController {
    private final UserService userService;


     @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todas los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de bodegas obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class))),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "204", description = "No hay bodegas disponibles", content = @Content)
    })
    public ResponseEntity<List<User>> getAllBodegas() {
        List<User> usuarios = userService.getAllUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    @GetMapping(value = "{id}")
    @Operation(summary = "Obtener Usuario", description = "Devuelve la información de un usuario según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario obtenido exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        UserDTO userDTO = userService.getUser(id);
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping()
    @Operation(summary = "Actualizar Usuario", description = "Actualiza la información de un usuario existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(userRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Usuario", description = "Elimina un usuario específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Puerto eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Puerto no encontrado", content = @Content)
    })
    public ResponseEntity<String> deleteUsuario(@PathVariable Integer id) {
        boolean validacion = userService.deleteUser(id);
        if (!validacion) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
    }
}