/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiKiosco.service;

import com.guido.ApiKiosco.DAO.ProductoRepository;
import com.guido.ApiKiosco.entity.Producto;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guido
 */
@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;
    
    
    @Transactional
    public Producto save (Producto producto) {
        
    return productoRepository.save(producto);
    }
    
    
    public Producto obtenerProductoPorId (Integer id)  {
        Optional <Producto> response = productoRepository.findById(id);
 
        if (response.isPresent()) {
        Producto producto = response.get();
        return producto;
        }
        else {
        return null;}
    }
    
    public List <Producto> listarProductos() {
        return (List<Producto>) productoRepository.findAll();
    }
    
     @Transactional
    public void delete (Producto producto) {
    
        productoRepository.delete(producto);
    }
    
    
    @Transactional
    public Producto actualizarProducto (Producto producto) {
        
        return productoRepository.save(producto);
    
    }
    
    
    public  List <Producto> obtenerProductoPorPrecio(Double precio) {
   
        return (List<Producto>) productoRepository.buscarPorPrecio(precio);
    }
}
