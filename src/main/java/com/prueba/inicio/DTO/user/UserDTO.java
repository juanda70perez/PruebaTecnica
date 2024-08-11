package com.prueba.inicio.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDTO {
    int id;
    String username;
    String firstname;
    String lastname;
}