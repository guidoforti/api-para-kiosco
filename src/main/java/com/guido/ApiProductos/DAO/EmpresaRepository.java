/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.DAO;

import com.guido.ApiProductos.entity.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guido
 */
@Repository
public interface EmpresaRepository extends CrudRepository <Empresa , Integer>{
 
    
  @Query ("SELECT e FROM Empresa e WHERE e.nombre = :nombre")  
  public Empresa obtenerEmpresaPorNombre(@Param("nombre") String nombre);
}
