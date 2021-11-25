package com.libreria.libreria.servicios;

import com.libreria.libreria.entidades.Editorial;


import com.libreria.libreria.errores.errorServicio;
import com.libreria.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class editorialServicio {

    @Autowired
    private EditorialRepositorio Er;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void agregarEditorial(String nombre) throws errorServicio {
        Editorial editorial = new Editorial();
        validacion(nombre);
        editorial.setNombre(nombre);
        editorial.setAlta(true);
        Er.save(editorial);
    }

    @Transactional (readOnly = true)
    public void modificarEditorialPorId(String id, String Nombre) throws errorServicio {
        validacion(Nombre);
        Optional<Editorial> Respuesta = Er.findById(id);
        if (Respuesta.isPresent()) {
            Editorial editorial = Respuesta.get();
            editorial.setAlta(true);
            editorial.setNombre(Nombre);
            Er.save(editorial);
        } else {
            throw new errorServicio("no se encontro el id a modificar");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void darDeBajaEditorial(String id) throws errorServicio {
        Optional<Editorial> Respuesta = Er.findById(id);
        if (Respuesta.isPresent()) {
            Editorial editorial = Respuesta.get();
            editorial.setAlta(false);
            Er.save(editorial);
        } else {
            throw new errorServicio("el id de la editorial no fue encontrado, no se puede eliminar");
        }
    }

   
    private void validacion(String nombre) throws errorServicio {
        if (nombre == null || nombre.isEmpty()) {
            throw new errorServicio("el titulo no puede ser nulo");
        }
    }
     @Transactional(readOnly = true)
    public Editorial buscarEditorialPorNombre(String nombre) throws errorServicio{
        validacion(nombre);
        return Er.buscarEditorialPorNombre(nombre);
    }
      @Transactional(readOnly = true)
    public Editorial findById(String id) {
        return Er.buscarEditorialPorId(id);
    }
     @Transactional(readOnly = true)
     public List<Editorial> listarTodos() {
        return Er.findAll();
    }
}
