package com.prueba.inicio.service;

import com.prueba.inicio.model.Bodega;
import com.prueba.inicio.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodegaService {
    
    @Autowired
    private BodegaRepository bodegaRepository;

    public Bodega saveBodega(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    public List<Bodega> getAllBodegas() {
        return bodegaRepository.findAll();
    }

    public Bodega getBodegaById(Long id) {
        return bodegaRepository.findById(id).orElse(null);
    }

    public Bodega updateBodega(Long id, Bodega bodegaDetails) {
        return bodegaRepository.findById(id).map(bodega -> {
            bodega.setNombre(bodegaDetails.getNombre());
            bodega.setUbicacion(bodegaDetails.getUbicacion());
            return bodegaRepository.save(bodega);
        }).orElse(null);
    }


    public boolean deleteBodega(long id) {
        if (!bodegaRepository.existsById(id)) {
            return false;
        }
        bodegaRepository.deleteById(id);
        return true;
    }
}