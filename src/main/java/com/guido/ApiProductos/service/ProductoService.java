/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.service;

import com.guido.ApiProductos.DAO.ProductoRepository;
import com.guido.ApiProductos.Exceptions.MyException;
import com.guido.ApiProductos.entity.Producto;
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
    public Producto save (Producto producto) throws MyException {
        
      validate(producto);
    return productoRepository.save(producto);
    }
    
    
    public Producto obtenerProductoPorId (Integer id)  {
       return productoRepository.findById(id).orElse(null);
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
    
    
    private void validate ( Producto producto) throws MyException {
        if (producto.getNombre().equals(null) || producto.getPrecio().equals(null)) {
        throw new MyException ("los campos del producto no pueden estar vacios");
        }
    }
}