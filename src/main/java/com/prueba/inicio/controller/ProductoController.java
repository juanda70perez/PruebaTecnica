package com.prueba.inicio.controller;

import com.prueba.inicio.model.Producto;
import com.prueba.inicio.service.ProductoService;

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
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "Gestión de Productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    @Operation(summary = "Crear Producto", description = "Crea un nuevo producto y lo guarda en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<Producto> createProducto(@Valid @RequestBody Producto producto) {
        Producto createdProducto = productoService.saveProducto(producto);
        return new ResponseEntity<>(createdProducto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Productos", description = "Devuelve una lista de todos los productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204", description = "No hay productos disponibles", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Producto por ID", description = "Devuelve un producto específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        return producto != null ? new ResponseEntity<>(producto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Producto", description = "Actualiza la información de un producto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @Valid @RequestBody Producto productoDetails) {
        Producto updatedProducto = productoService.updateProducto(id, productoDetails);
        return updatedProducto != null ? new ResponseEntity<>(updatedProducto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Producto", description = "Elimina un producto específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "No autorizado", content = @Content),
    })
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        boolean validacion = productoService.deleteProducto(id);
        if (!validacion) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
    }
}