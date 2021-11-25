
package com.libreria.libreria.servicios;

import com.libreria.libreria.entidades.Autor;

import com.libreria.libreria.errores.errorServicio;
import com.libreria.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service 

public class autorServicio {
    @Autowired
    AutorRepositorio Ar;
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void agregarAutor(String nombre) throws errorServicio {
        
        validacion(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);
        Ar.save(autor);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void darDeAltaAutor(String id) throws errorServicio {
        Optional<Autor> Respuesta = Ar.findById(id);
        if (Respuesta.isPresent()) {
            Autor Autor = Respuesta.get();
            Autor.setAlta(true);
            Ar.save(Autor);
        } else {
            throw new errorServicio("el id del autor no fue encontrado, no se puede eliminar");
        }
    }
    

    @Transactional
    public void modificarAutorPorId(String id, String Nombre) throws errorServicio {
        validacion(Nombre);
        Optional<Autor> Respuesta = Ar.findById(id);
        if (Respuesta.isPresent()) {
            Autor autor = Respuesta.get();
            autor.setAlta(true);
            autor.setNombre(Nombre);
            Ar.save(autor);
        } else {
            throw new errorServicio("no se encontro el id a modificar");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void darDeBajaAutor(String id) throws errorServicio {
        Optional<Autor> Respuesta = Ar.findById(id);
        if (Respuesta.isPresent()) {
            Autor Autor = Respuesta.get();
            Autor.setAlta(false);
            Ar.save(Autor);
        } else {
            throw new errorServicio("el id del autor no fue encontrado, no se puede eliminar");
        }
    }

    @Transactional(readOnly = true)
    public Autor buscarAutorPorNombre(String nombre) throws errorServicio{
        validacion(nombre);
        return Ar.buscarAutorPorNombre(nombre);
    }
      @Transactional(readOnly = true)
    public Autor findById(String id) {
        return Ar.buscarAutorPorId(id);
    }
    @Transactional(readOnly = true)
	public List<Autor> listarTodos() {
		return Ar.findAll();
	}
         private void validacion(String nombre) throws errorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new errorServicio("el nombre no puede ser nulo");
        }
    }
}
