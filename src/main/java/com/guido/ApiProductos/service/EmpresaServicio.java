/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.service;

import com.guido.ApiProductos.DAO.EmpresaRepository;
import com.guido.ApiProductos.Exceptions.MyException;
import com.guido.ApiProductos.entity.Empresa;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Guido
 */

@Service
public class EmpresaServicio {
    
    @Autowired
    EmpresaRepository empresaRepository;
    
    
    @Transactional 
    public Empresa save (Empresa empresa) throws MyException {
    
        validate(empresa);
        
        return empresaRepository.save(empresa);
    }
    
    @Transactional
    public void delete (Empresa empresa) {
         empresaRepository.delete(empresa);
    }
    
    
    @Transactional
    public Empresa actualizar (Empresa empresa) throws MyException {
        validate(empresa);
        
        return empresaRepository.save(empresa);
    
    }
    
    
    public List <Empresa> listarEmpresas () {
    return (List <Empresa>) empresaRepository.findAll();
    }
    
    public Empresa obtenerEmpresaPorId (Integer id) {
    return empresaRepository.findById(id).orElse(null);
    }
    
    public Empresa obtenerEmpresaPorNombre (String nombre) {
    return empresaRepository.obtenerEmpresaPorNombre(nombre);
    }
    
    private void validate (Empresa empresa) throws  MyException {
    
        if (empresa.getNombre() == null) {
        throw  new MyException("los campos de la empresa no pueden estar vacios");
        }
    }
}
