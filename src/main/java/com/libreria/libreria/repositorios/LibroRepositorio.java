
package com.libreria.libreria.repositorios;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,String> {
 
    @Query("SELECT c FROM Libro c WHERE c.id= :id")
    public Libro buscarLibroPorId(@Param("id") String id);
    
     @Query("SELECT c FROM Libro c WHERE c.isbn= :isbn")
    public Libro buscarLibroPorIsbn(@Param("isbn") Long isbn);
    
    
     @Query("SELECT c FROM Libro c WHERE c.titulo= :tilulo")
    public Libro buscarLibroPorTitulo(@Param("titulo") Long titulo);
}
