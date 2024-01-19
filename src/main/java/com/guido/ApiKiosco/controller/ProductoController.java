/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiKiosco.controller;

import com.guido.ApiKiosco.entity.Producto;
import com.guido.ApiKiosco.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/apiKiosco/v1")
public class ProductoController {
  
    @Autowired
    ProductoService productoService;
    
    @PostMapping("/producto")
   public Producto saveProducto ( @RequestBody Producto producto) {
       return productoService.save(producto);
   }
   
   @DeleteMapping("/producto")
   public void deleteProducto ( @RequestBody Producto producto) {
       productoService.delete(producto);
   }
   
   @PutMapping("/producto")
   public Producto actualizarProducto (@RequestBody Producto producto) {
   return productoService.actualizarProducto(producto);
   }
   
   @GetMapping("/producto/{id}")
   public Producto obtenerProductoPorId (@PathVariable Integer id) {
   return productoService.obtenerProductoPorId(id);
   }
   
   @GetMapping("/productos")
   public List <Producto> listarProductos () {
       return productoService.listarProductos();
   }
}
