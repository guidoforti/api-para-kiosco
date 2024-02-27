/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.service;

import com.guido.ApiProductos.DAO.CompañiaRepository;
import com.guido.ApiProductos.DAO.ProductoRepository;
import com.guido.ApiProductos.entity.Compañia;
import com.guido.ApiProductos.entity.Producto;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompañiaService {
    
   @Autowired
    CompañiaRepository compañiaRepository;
   @Autowired
    ProductoRepository productoRepository;
   
   
   
   @Transactional
   public Compañia save (Compañia compañia) {
   
     return  compañiaRepository.save(compañia);
   }
   
   
   
   @Transactional
   public void delete (Compañia compañia) {
       compañiaRepository.delete(compañia);
   }
  
   
   public Compañia obtenerPorId (Integer id) {
   return compañiaRepository.findById(id).orElse(null);
   }
   
   public  Compañia obtenerCompañiaPorNombre (String nombre) {
   return compañiaRepository.obtenerCompañiaPorNombre(nombre);
   }
   
   
   @Transactional
   public Compañia actualizarCompañia (Compañia compania) {
   return compañiaRepository.save(compania);
   }
   
   
   
   public List <Producto> obtenerProductosDeCompañia (Integer id ) {
   return compañiaRepository.obtenerProductosDeUnaCompañia(id);
   }
   
   
   public List <Compañia> listarCompañias () {
   return (List<Compañia>) compañiaRepository.findAll();  
   }
   
   
   
}
