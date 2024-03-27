/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.controller;

import com.guido.ApiProductos.DAO.EmpresaRepository;
import com.guido.ApiProductos.Exceptions.MyException;
import com.guido.ApiProductos.entity.Empresa;
import com.guido.ApiProductos.service.EmpresaServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Guido
 */
@RestController
@RequestMapping("/apiProductos/v1")
public class EmpresaController {
    
    @Autowired
    EmpresaServicio empresaServicio;
    
    
    @PostMapping("/empresa")
    public ResponseEntity<?> crearEmpresa (@RequestBody Empresa empresa) throws MyException {
    Map <String ,Object> response = new HashMap<>();
    
        try {
            if (empresa != null) {
            empresaServicio.save(empresa);
            
            return new ResponseEntity<> (empresa, HttpStatus.CREATED);
            } else {
            response.put("mensaje", "la empresa enviada no puede ser null");
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
            
            }
        } catch (DataAccessException e) {
            
             response.put("mensaje", e.getMessage());
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<?> eliminarEmpresa (@PathVariable Integer id) {
    Map <String ,Object> response = new HashMap<>();
        try {
            Empresa empresa = empresaServicio.obtenerEmpresaPorId(id);
            if (empresa != null ) {
            empresaServicio.delete(empresa);
            
            return new ResponseEntity<>(empresa , HttpStatus.OK);
            } else {
            response.put("mensaje", "la empresa enviada no puede ser null");
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
             response.put("mensaje", e.getMessage());
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    
    @PutMapping("/empresa/{id}")
    public ResponseEntity<?> actualizarEmpresa(@RequestBody Empresa empresaNueva , @PathVariable Integer id) {
    Map <String , Object> response = new HashMap<>();
    
        Empresa empresaExistente = empresaServicio.obtenerEmpresaPorId(id);
        try {
            if (empresaExistente != null) {
                empresaExistente.setId(id);
                empresaExistente.setNombre(empresaNueva.getNombre());
                empresaExistente.setProductos(empresaExistente.getProductos());
            return new ResponseEntity<>(empresaExistente , HttpStatus.OK);
            } else {
            response.put("mensaje", "la empresa enviada no puede ser null");
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            response.put("mensaje", e.getMessage());
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    
    }
    
    @GetMapping("/empresa/{id}")
    public ResponseEntity<?> obtenerEmpresaPorId(@PathVariable Integer id) {
       Map <String, Object> response = new HashMap <>();
       
       try {
          Empresa empresa = empresaServicio.obtenerEmpresaPorId(id);
          if ( empresa != null) {
              
             return new ResponseEntity<>(empresa,HttpStatus.OK);
          } else {
               response.put("mensaje", "la empresa con ese id no fue encontrada");
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
          }
        } catch (DataAccessException e) {
             response.put("mensaje", e.getMessage());
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/empresas")
    public ResponseEntity<?> listarEmpresas () {
    Map <String, Object> response = new HashMap<>();
    
        try {
            
            List <Empresa> empresas = empresaServicio.listarEmpresas();
            if (empresas != null) {
              return new ResponseEntity<>(empresas, HttpStatus.OK);
            } else {
                 response.put("mensaje", "no fueron encontradas las empresas");
            response.put("empresas", null);
            return new ResponseEntity<>(response , HttpStatus.BAD_REQUEST);
            }
          
         
           
        } catch (DataAccessException e) {
              response.put("mensaje", e.getMessage());
            response.put("empresa", null);
            return new ResponseEntity<>(response , HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
