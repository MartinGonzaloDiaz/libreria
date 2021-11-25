
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial,String>{
    
     @Query("SELECT a FROM Libro a WHERE a.id=:id")
    public Editorial buscarEditorialPorId(@Param("id") String id);
    
      @Query("SELECT a FROM Libro a WHERE a.nombre=:nombre")
    public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
}
