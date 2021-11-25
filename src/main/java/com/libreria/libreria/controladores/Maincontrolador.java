/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.libreria.controladores;
import com.libreria.libreria.servicios.autorServicio;
import com.libreria.libreria.servicios.editorialServicio;
import com.libreria.libreria.servicios.libroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Maincontrolador {
    	@Autowired
	private autorServicio autorServicio;

	@Autowired
	private editorialServicio editorialServicio;
        
        @Autowired
	private libroServicio libroServicio;
        
      
    
    @GetMapping("/")
public String index(ModelMap modelo){
    
return "index";
}
}

