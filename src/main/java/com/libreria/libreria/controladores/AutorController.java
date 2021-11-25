
package com.libreria.libreria.controladores;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.errores.errorServicio;
import com.libreria.libreria.servicios.autorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Autor")
public class AutorController {
    @Autowired
    private autorServicio autorServicio;
    
    @GetMapping("/registrAutor")
    public String formularioAutor(){
        return "form-autor";
    }
    @PostMapping("/registrarAutor")
    public String guardarAutor (ModelMap modelo, @RequestParam String nombre) throws errorServicio{
        try {
            autorServicio.agregarAutor(nombre);
            modelo.put("exito", "Registro exitoso");
            return "form-autor";
        }catch (Exception e) {
			modelo.put("error", "Te falto un dato");
			return "form-autor";
    
                
                
    }
    }
    @GetMapping("/listaAutor")
	public String lista(ModelMap modelo) {
		
		List<Autor> todos = autorServicio.listarTodos();
		
		modelo.addAttribute("Autor", todos);
		
		return "list-autor";
	}
	
}

