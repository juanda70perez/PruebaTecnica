package com.prueba.inicio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prueba.inicio.DTO.Enum.Role;
import com.prueba.inicio.DTO.user.UserDTO;
import com.prueba.inicio.DTO.user.UserRequest;
import com.prueba.inicio.DTO.user.UserResponse;
import com.prueba.inicio.model.Bodega;
import com.prueba.inicio.model.User;
import com.prueba.inicio.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {

        User user = User.builder()
                .id(userRequest.getId())
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .role(Role.USER)
                .build();

        userRepository.updateUser(user.getId(), user.getFirstname(), user.getLastname());
        return new UserResponse("El usuario se registró satisfactoriamente");
    }

    public UserDTO getUser(Integer id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname());
            return userDTO;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public boolean deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}