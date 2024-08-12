package com.prueba.inicio.model;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Getter
@Setter
public class EnvioMaritimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El tipo de producto no puede estar vacío")
    private String tipoProducto;
    @NotBlank(message = "La cantidad de entrega no puede estar vacío")
    private int cantidad;
    @NotBlank(message = "La fecha de registro no puede estar vacío")
    private LocalDate fechaRegistro;
    @NotBlank(message = "La fecha de entrega no puede estar vacío")
    private LocalDate fechaEntrega;
    @ManyToOne
    @NotBlank(message = "El  Puerto de entrega no puede estar vacío")
    private Puerto puertoEntrega;
    @NotBlank(message = "El  Precio del envio no puede estar vacío")
    private double precioEnvio;
    private double precioEnvioDescuento;

    @NotBlank(message = "El  número de flota  no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{4}[A-Z]$", message = "El número de flota debe seguir el formato: 3 letras iniciales, 4 números y 1 letra")
    private String numeroFlota;
    @NotBlank(message = "El  número de guía no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9]{10}$", message = "El número de guía debe ser un alfanumérico único de 10 dígitos")
    private String numeroGuia;
    @ManyToOne
    @NotBlank(message = "El  cliente no puede estar vacío")
    private Cliente cliente;

    public void setCantidad(int cantidad) {
        if (cantidad < 10) {
             setPrecioEnvioDescuento(0);
        }
        setPrecioEnvioDescuento(this.getPrecioEnvio() * (1 - 0.03));
        this.cantidad = cantidad;
    }
}