package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.PersonaService;
import controller.form.PersonaForm;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	private static final String DATE_MASK = "yyyy-MM-dd";
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping({ "", "inicio" })
	public String listar(Model model) {
		init(model);
		return "/WEB-INF/jsp/persona/inicio.jsp";
	}

	private void init(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
	}

	@RequestMapping("agregar")
	public String agregar(PersonaForm personaForm, Model model) {
		// TODO Refactorizar validaciones y enviar a otro método
		List<String> errores = new ArrayList<String>();
		Date fechaNacimiento = null;
		Persona p = new Persona();
		
		if (personaForm.getNombre() == null || 
				personaForm.getNombre().trim().equals("") || 
				personaForm.getNombre().trim().length() < 3)
			errores.add("Nombre inválido");
		
		if (personaForm.getApellido() == null || 
				personaForm.getApellido().trim().equals("") || 
				personaForm.getApellido().trim().length() < 3)
			errores.add("Apellido inválido");

		try {
			fechaNacimiento = 
					new SimpleDateFormat(DATE_MASK).parse(personaForm.getFechaNacimiento());
		} catch (Exception e) {
			errores.add("Fecha de nacimiento inválida");
		}

		if (errores.size() == 0) { 
			p.setNombre(personaForm.getNombre());
			p.setApellido(personaForm.getApellido());
			p.setFechaNacimiento(fechaNacimiento);

			try {
				personaService.agregarPersona(p);
			} catch (Exception e) {
				e.printStackTrace();
				errores.add("Error al agregar en BD");
			}
		}
		
		if (errores.size() == 0) { // success
			return "redirect:/persona/inicio.do";
		} else {
			model.addAttribute("errores", errores);
			model.addAttribute("personaForm", personaForm);
			init(model);
			return "forward:/WEB-INF/jsp/persona/inicio.jsp";
		}

	}
}









