package com.prueba.inicio.service;

import com.prueba.inicio.model.EnvioMaritimo;
import com.prueba.inicio.repository.EnvioMaritimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioMaritimoService {
    
    @Autowired
    private EnvioMaritimoRepository envioMaritimoRepository;

    public EnvioMaritimo saveEnvioMaritimo(EnvioMaritimo envioMaritimo) {
        return envioMaritimoRepository.save(envioMaritimo);
    }

    public List<EnvioMaritimo> getAllEnviosMaritimos() {
        return envioMaritimoRepository.findAll();
    }

    public EnvioMaritimo getEnvioMaritimoById(Long id) {
        return envioMaritimoRepository.findById(id).orElse(null);
    }

    public EnvioMaritimo updateEnvioMaritimo(Long id, EnvioMaritimo envioMaritimoDetails) {
        return envioMaritimoRepository.findById(id).map(envioMaritimo -> {
            envioMaritimo.setTipoProducto(envioMaritimoDetails.getTipoProducto());
            envioMaritimo.setCantidad(envioMaritimoDetails.getCantidad());
            envioMaritimo.setFechaRegistro(envioMaritimoDetails.getFechaRegistro());
            envioMaritimo.setFechaEntrega(envioMaritimoDetails.getFechaEntrega());
            envioMaritimo.setPuertoEntrega(envioMaritimoDetails.getPuertoEntrega());
            envioMaritimo.setPrecioEnvio(envioMaritimoDetails.getPrecioEnvio());
            envioMaritimo.setNumeroFlota(envioMaritimoDetails.getNumeroFlota());
            envioMaritimo.setNumeroGuia(envioMaritimoDetails.getNumeroGuia());
            envioMaritimo.setCliente(envioMaritimoDetails.getCliente());
            return envioMaritimoRepository.save(envioMaritimo);
        }).orElse(null);
    }

    public boolean deleteEnvioMaritimo(long id) {
        if (!envioMaritimoRepository.existsById(id)) {
            return false;
        }
        envioMaritimoRepository.deleteById(id);
        return true;
    }
}
