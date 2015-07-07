package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.PersonaService;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping({ "", "inicio" })
	public String listar(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
		
		return "/WEB-INF/jsp/persona/inicio.jsp";
	}

}




