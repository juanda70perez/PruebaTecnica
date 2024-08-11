package com.prueba.inicio.service;

import com.prueba.inicio.model.Producto;
import com.prueba.inicio.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoDetails.getNombre());
            producto.setTipo(productoDetails.getTipo());
            return productoRepository.save(producto);
        }).orElse(null);
    }

    public boolean deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}