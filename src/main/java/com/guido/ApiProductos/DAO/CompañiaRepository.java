package com.guido.ApiProductos.DAO;

import com.guido.ApiProductos.entity.Compañia;
import com.guido.ApiProductos.entity.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CompañiaRepository extends CrudRepository<Compañia, Integer> {
    
    
    @Query("SELECT c.productos FROM Compañia c WHERE c.id = :id")
    public List<Producto> obtenerProductosDeUnaCompañia (@Param("id") Integer id);
    
    @Query ("SELECT c FROM Compañia c WHERE c.nombre = :nombre")
    public Compañia obtenerCompañiaPorNombre (@Param("nombre") String nombre);

}
