/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.controller;

import com.guido.ApiProductos.Exceptions.MyException;
import com.guido.ApiProductos.entity.Compañia;
import com.guido.ApiProductos.service.CompañiaService;
import com.guido.ApiProductos.service.ProductoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/apiProductos/v1")
public class CompañiaController  {
    
@Autowired
ProductoService productoService;
@Autowired
CompañiaService compañiaService;

@PostMapping("/compañia")
public  ResponseEntity<?> saveCompañia (@RequestBody Compañia compañia) throws MyException {

Map <String, Object> response = new HashMap<>();

    try {
        if  (compañia != null) {
        compañiaService.save(compañia);
        return new ResponseEntity<>(compañia , HttpStatus.CREATED);
        } else {
            
            response.put("mensaje", "la compañia no puede estar vacia");
            response.put("compañia", null);
            return  new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
        }
        
        
    } catch (DataAccessException e) {
        response.put("mensaje", e.getMessage());
        response.put("compañia", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@GetMapping("/compañia/{id}")
public ResponseEntity<?> obtenerCompañiaPorId (@RequestParam Integer id) {
    
    Map <String, Object> response = new HashMap<>();
    try {
        
        if (compañiaService.obtenerPorId(id) != null) {
            Compañia compañia = compañiaService.obtenerPorId(id);
      return new ResponseEntity<>(compañia , HttpStatus.OK);
        } else {
        response.put("mensaje", "la compañia no se encuentra en la base de datos");
        response.put("compañia", null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
      
    } catch (DataAccessException e) {
          response.put("mensaje", e.getMessage());
        response.put("compañia", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    
@GetMapping("/compañias")
public ResponseEntity<?> listarCompañias () {
Map <String, Object> response = new HashMap<>();

    try {
        if (compañiaService.listarCompañias() != null) {
         List <Compañia> compañias = compañiaService.listarCompañias();
        return new ResponseEntity<>(compañias, HttpStatus.OK);
        } else {
        response.put("mensaje : ", "la lista de compañias esta vacia");
        response.put("compañias", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
 
        }
       
        
    } catch (DataAccessException e) {
         response.put("mensaje : ", e.getMessage());
        response.put("compañias", null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
