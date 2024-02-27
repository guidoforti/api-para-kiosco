/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guido.ApiProductos.DAO;

import com.guido.ApiProductos.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends CrudRepository <Producto , Integer>{
    
    
    @Query("SELECT p FROM Producto p WHERE p.precio = :precio")
    public Producto buscarPorPrecio(@Param("precio") Double precio) ;
    
    @Query ("SELECT p FROM Producto p WHERE p.compania.nombre = :nombre") // en JPA no hay que hacer inner joins ni usar los datos o nombres de las tablas sino que tenes que usar los nombres y atributos del as entidades
    public List <Producto> obtenerProductosSegunCompania(@Param("nombre") String nombre);
}
