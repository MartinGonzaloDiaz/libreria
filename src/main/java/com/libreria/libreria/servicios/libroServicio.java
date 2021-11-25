package com.libreria.libreria.servicios;

import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.errores.errorServicio;
import com.libreria.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class libroServicio {

    @Autowired
    LibroRepositorio Lr;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void agregarLibro(Long isbn, String titulo, Integer anio) throws errorServicio {
        validacion(isbn, titulo, anio);
        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setAlta(true);
        Integer ejemplares = (int) (Math.random() * 999 + 1);
        Integer prestados = ejemplares - (int) (Math.random() * 88 + 1);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(prestados);
        libro.setEjemplaresRestantes(ejemplares - prestados);
        Lr.save(libro);

    }

    @Transactional
    public void modificarLibroPorId(String id, Long isbn, String titulo, Integer anio) throws errorServicio {
        validacion(isbn, titulo, anio);
        Optional<Libro> Respuesta = Lr.findById(id);
        if (Respuesta.isPresent()) {
            Libro Libro = Respuesta.get();
            Libro.setIsbn(isbn);
            Libro.setTitulo(titulo);
            Libro.setAnio(anio);
            Libro.setAlta(true);
            Integer ejemplares = (int) (Math.random() * 999 + 1);
            Integer prestados = ejemplares - (int) (Math.random() * 88 + 1);
            Libro.setEjemplares(ejemplares);
            Libro.setEjemplaresPrestados(prestados);
            Libro.setEjemplaresRestantes(ejemplares - prestados);
            Lr.save(Libro);
        } else {
            throw new errorServicio("el id del libro no fue encontrado");
        }

    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void darDeBaja(String id) throws errorServicio {
        Optional<Libro> Respuesta = Lr.findById(id);
        if (Respuesta.isPresent()) {
            Libro Libro = Respuesta.get();
            Libro.setAlta(false);
            Lr.save(Libro);
        } else {
            throw new errorServicio("el id del libro no fue encontrado, no se puede dar de baja");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void darDeAltaLibro(String id) throws errorServicio {
        Optional<Libro> Respuesta = Lr.findById(id);
        if (Respuesta.isPresent()) {
            Libro Libro = Respuesta.get();
            Libro.setAlta(true);
            Lr.save(Libro);
        } else {
            throw new errorServicio("el id del libro no fue encontrado, no se puede dal el alta");
        }
    }

    //buscar por id
    @Transactional(readOnly = true)
    public Libro findById(String id) {
        return Lr.buscarLibroPorId(id);
    }

    //buscar por isbn
    @Transactional(readOnly = true)
    public Libro buscarPorIsbn(Long isbn) {
        return Lr.buscarLibroPorIsbn(isbn);
    }
//listar todos
    @Transactional(readOnly = true)
    public List<Libro> listarTodos() {
        return Lr.findAll();
    }

    private void validacion(Long isbn, String titulo, Integer anio) throws errorServicio {

        if (isbn == null || isbn < 999999) {
            throw new errorServicio("el valor del isbn no puede ser nulo o con mas de seis digitos");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new errorServicio("el titulo no puede ser nulo");
        }
        if (anio == null || anio.longValue() <= 9999) {
            throw new errorServicio("el valor del año no puede ser nulo o con mas de cuatrodigitos");
        }
        Libro l = Lr.buscarLibroPorIsbn(isbn);
        if (l != null) {
            throw new errorServicio("ya existe el isbn y no pueden repetirse");
        }
    }

}
