
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{ 
    
     @Query("SELECT a FROM Autor a WHERE a.id=:id")
    public Autor buscarAutorPorId(@Param("id") String id);
    
     @Query("SELECT a FROM Autor a WHERE a.nombre=:nombre")
    public Autor buscarAutorPorNombre(@Param("nombre") String nombre);
}
