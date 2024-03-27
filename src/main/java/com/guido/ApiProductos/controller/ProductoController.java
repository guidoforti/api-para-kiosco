/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.controller;

import com.guido.ApiProductos.Exceptions.MyException;
import com.guido.ApiProductos.entity.Producto;
import com.guido.ApiProductos.entity.ProductoDTO;
import com.guido.ApiProductos.payload.MensajeResponse;

import com.guido.ApiProductos.service.ProductoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apiProductos/v1")
public class ProductoController {
  
    @Autowired
    ProductoService productoService;
   
    
    @PostMapping("/producto")
    //@ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity<?> saveProducto ( @RequestBody Producto producto) throws MyException {
       Map<String, Object> response = new HashMap<>();
        try {
            if ( producto != null) {

               productoService.save(producto);
                 return new ResponseEntity<>(producto , HttpStatus.CREATED);
            } else {
           response.put("mensaje" , "el producto enviado no puede estar vacio");
           response.put("producto", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            
        } catch (DataAccessException e) {
            response.put("mensaje", e.getMessage());
           response.put("producto", null);
           return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
   }
   
   @DeleteMapping("/producto")
   //@ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<?> deleteProducto ( @RequestBody Producto producto) {
       
       Map<String, Object> response = new HashMap<>();
       try {
           if (producto != null) {
               productoService.delete(producto);
           return new ResponseEntity<>(producto , HttpStatus.NO_CONTENT);
           } else  {
               response.put("mensaje", "el producto no se encontro");
               response.put("producto", null);
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
           }
           
       } catch (DataAccessException e) {
           response.put("mensaje", e.getMessage());
           response.put("producto", null);
           return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
   }
   
    @DeleteMapping("/producto/{id}")
   //@ResponseStatus(HttpStatus.NO_CONTENT)
   public ResponseEntity<?> deleteProductoById ( @PathVariable Integer id)  {
       Map<String, Object> response = new HashMap<>();
        try {
          Producto producto =  productoService.obtenerProductoPorId(id);
          if ( producto != null) {
              productoService.delete(producto);
            return new ResponseEntity<>(producto , HttpStatus.NO_CONTENT );
          } else {
          response.put("mensaje", "no se encontro el producto solicitado");
          response.put("producto" , null);
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
                  }
            
            
        } catch (DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("producto", null);
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
       
   }
   
   @PutMapping("/producto/{id}")
   //@ResponseStatus(HttpStatus.OK)
   public ResponseEntity<?> actualizarProducto (@RequestBody Producto producto, @PathVariable Integer id) throws MyException {
       Map <String, Object> response = new HashMap<>();
       
        Producto productoActualizar = productoService.obtenerProductoPorId(id);
       try {
           if ( productoActualizar != null) {
               productoActualizar.setId(id);
               productoActualizar.setNombre(producto.getNombre());
               productoActualizar.setPrecio(producto.getPrecio());
        productoService.actualizarProducto(productoActualizar);
        return new ResponseEntity<>(productoActualizar, HttpStatus.OK);
       } else {
               response.put("mensaje", "el producto a actualizar no fue encontrado en la base de datos");
               response.put("producto", null);
               return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
       } catch (DataAccessException e) {
           response.put("mensaje", e.getMessage());
            response.put("producto", null);
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
  
   }
   
   @GetMapping("/producto/{id}")
   //@ResponseStatus(HttpStatus.OK)
   public ResponseEntity<?> obtenerProductoPorId (@PathVariable Integer id) throws MyException {
   
       Map <String, Object> response = new HashMap<>();
       
       try {
        
          Producto producto =  productoService.obtenerProductoPorId(id);
          
         if ( producto != null) {
         return  new ResponseEntity<>(producto, HttpStatus.OK);
         } else {
             response.put("mensaje", "producto no encontrado");
             response.put("procuto", null);
              return  new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
         }
           
       } catch (DataAccessException e) {
           response.put("mensaje", e.getMessage());
           response.put("producto", null);
           return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
       }
       
   }
   
   

   
   @GetMapping("/productos")
   @ResponseStatus(HttpStatus.OK)
   public List <Producto> listarProductos () {
       return productoService.listarProductos();
   }
}
