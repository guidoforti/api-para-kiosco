/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.DAO;

import com.guido.ApiProductos.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends CrudRepository <Producto , Integer>{
    
    
    @Query("SELECT p FROM Producto p WHERE p.precio = :precio")
    public Producto buscarPorPrecio(@Param("precio") Double precio) ;
}
