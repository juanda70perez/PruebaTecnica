package com.prueba.inicio.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.Entity
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull
    private String nombre;
    @Email(message = "Debe proporcionar un correo electrónico válido")
    private String email;
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Debe proporcionar un número de teléfono válido")
    private String telefono;

}

