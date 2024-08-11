package com.prueba.inicio.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@jakarta.persistence.Entity
@Getter
@Setter
public class EnvioTerrestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El  tipo de producto  no puede estar vacío")
    private String tipoProducto;
    @NotBlank(message = "La cantidad no puede estar vacío")
    private int cantidad;
    private LocalDate fechaRegistro;
    @NotBlank(message = "La fecha de entrega   no puede estar vacío")
    private LocalDate fechaEntrega;
    @ManyToOne
    private Bodega bodegaEntrega;
    @NotBlank(message = "El  precio de envio  no puede estar vacío")
    private double precioEnvio;
    private double precioEnvioDescuento;
    @NotBlank(message = "El  número de placa  no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{4}[A-Z]$", message = "El número de placa debe seguir el formato: 3 letras iniciales y 3 números")
    private String placaVehiculo;
    @NotBlank(message = "El  número de guía no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9]{10}$", message = "El número de guía debe ser un alfanumérico único de 10 dígitos")
    private String numeroGuia;
    @ManyToOne
    @NotBlank(message = "El  cliente no puede estar vacío")
    private Cliente cliente;

    public void setPrecioEnvioDescuento(){
        if(this.getCantidad() < 10 ){
            this.precioEnvioDescuento = 0 ;
        }
        this.precioEnvioDescuento = (this.getPrecioEnvio() * (1-0.05));
    }
}
