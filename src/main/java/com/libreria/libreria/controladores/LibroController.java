
package com.libreria.libreria.controladores;

import com.libreria.libreria.entidades.Libro;
import com.libreria.libreria.errores.errorServicio;
import com.libreria.libreria.servicios.libroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Libro")
public class LibroController {
      @Autowired
    private libroServicio libroServicio;
    
    @GetMapping("/registrLibro")
    public String formularioLibro(){
        return "form-libro";
    }
    @PostMapping("/registrarLibro")
    public String guardarLibro (ModelMap modelo,@RequestParam long isbn, @RequestParam String titulo,@RequestParam Integer anio) throws errorServicio{
        try {
            libroServicio.agregarLibro(isbn, titulo, anio);
            modelo.put("exito", "Registro exitoso");
            return "formularioEditorial";
        }catch (Exception e) {
			modelo.put("error", "Te falto un dato");
			return "form-libro";
    
                
                
    }
    }
    @GetMapping("/listaEditorial")
	public String lista(ModelMap modelo) {
		
		List<Libro> todos = libroServicio.listarTodos();
		
		modelo.addAttribute("Libro", todos);
		
		return "list-libro";
	}
}
 


