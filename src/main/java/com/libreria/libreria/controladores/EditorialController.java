
package com.libreria.libreria.controladores;

import com.libreria.libreria.entidades.Autor;
import com.libreria.libreria.entidades.Editorial;
import com.libreria.libreria.errores.errorServicio;
import com.libreria.libreria.servicios.editorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/Editorial")
public class EditorialController {
        @Autowired
    private editorialServicio editorialServicio;
    
    @GetMapping("/registrEditorial")
    public String formularioEditorial(){
        return "form-editorial";
    }
    @PostMapping("/registrarEditorial")
    public String guardarEditorial (ModelMap modelo, @RequestParam String nombre) throws errorServicio{
        try {
            editorialServicio.agregarEditorial(nombre);
            modelo.put("exito", "Registro exitoso");
            return "formularioEditorial";
        }catch (Exception e) {
			modelo.put("error", "Te falto un dato");
			return "form-editorial";
    
                
                
    }
    }
    @GetMapping("/listaEditorial")
	public String lista(ModelMap modelo) {
		
		List<Editorial> todos = editorialServicio.listarTodos();
		
		modelo.addAttribute("Autor", todos);
		
		return "list-editorial";
	}
}
