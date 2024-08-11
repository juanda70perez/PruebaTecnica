package com.prueba.inicio.service;

import com.prueba.inicio.model.Puerto;
import com.prueba.inicio.repository.PuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PuertoService {
    
    @Autowired
    private PuertoRepository puertoRepository;

    public Puerto savePuerto(Puerto puerto) {
        return puertoRepository.save(puerto);
    }

    public List<Puerto> getAllPuertos() {
        return puertoRepository.findAll();
    }

    public Puerto getPuertoById(Long id) {
        return puertoRepository.findById(id).orElse(null);
    }

    public Puerto updatePuerto(Long id, Puerto puertoDetails) {
        return puertoRepository.findById(id).map(puerto -> {
            puerto.setNombre(puertoDetails.getNombre());
            puerto.setUbicacion(puertoDetails.getUbicacion());
            return puertoRepository.save(puerto);
        }).orElse(null);
    }

    public boolean deletePuerto(Long id) {
        if (!puertoRepository.existsById(id)) {
            return false;
        }
        puertoRepository.deleteById(id);
        return true;
    }
}
