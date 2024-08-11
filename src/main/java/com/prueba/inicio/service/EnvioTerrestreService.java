package com.prueba.inicio.service;

import com.prueba.inicio.model.EnvioTerrestre;
import com.prueba.inicio.repository.EnvioTerrestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioTerrestreService {
    
    @Autowired
    private EnvioTerrestreRepository envioTerrestreRepository;

    public EnvioTerrestre saveEnvioTerrestre(EnvioTerrestre envioTerrestre) {
        return envioTerrestreRepository.save(envioTerrestre);
    }

    public List<EnvioTerrestre> getAllEnviosTerrestres() {
        return envioTerrestreRepository.findAll();
    }

    public EnvioTerrestre getEnvioTerrestreById(Long id) {
        return envioTerrestreRepository.findById(id).orElse(null);
    }

    public EnvioTerrestre updateEnvioTerrestre(Long id, EnvioTerrestre envioTerrestreDetails) {
        return envioTerrestreRepository.findById(id).map(envioTerrestre -> {
            envioTerrestre.setTipoProducto(envioTerrestreDetails.getTipoProducto());
            envioTerrestre.setCantidad(envioTerrestreDetails.getCantidad());
            envioTerrestre.setFechaRegistro(envioTerrestreDetails.getFechaRegistro());
            envioTerrestre.setFechaEntrega(envioTerrestreDetails.getFechaEntrega());
            envioTerrestre.setBodegaEntrega(envioTerrestreDetails.getBodegaEntrega());
            envioTerrestre.setPrecioEnvio(envioTerrestreDetails.getPrecioEnvio());
            envioTerrestre.setPlacaVehiculo(envioTerrestreDetails.getPlacaVehiculo());
            envioTerrestre.setNumeroGuia(envioTerrestreDetails.getNumeroGuia());
            envioTerrestre.setCliente(envioTerrestreDetails.getCliente());
            return envioTerrestreRepository.save(envioTerrestre);
        }).orElse(null);
    }

    public boolean deleteEnvioTerrestre(long id) {
        if (!envioTerrestreRepository.existsById(id)) {
            return false;
        }
        envioTerrestreRepository.deleteById(id);
        return true;
    }
}

