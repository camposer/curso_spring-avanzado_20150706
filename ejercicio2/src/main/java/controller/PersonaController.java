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
import org.springframework.web.bind.annotation.RequestParam;

import service.PersonaService;
import controller.form.PersonaForm;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	private static final String DATE_MASK = "yyyy-MM-dd";
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping({ "", "inicio" })
	public String inicio(Model model) {
		init(model);
		return "/WEB-INF/jsp/persona/inicio.jsp";
	}

	private void init(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
	}

	@RequestMapping("guardar")
	public String guardar(PersonaForm personaForm, Model model) {
		// TODO Refactorizar validaciones y enviar a otro método
		List<String> errores = new ArrayList<String>();
		Date fechaNacimiento = null;
		
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
			Persona p = new Persona();
			p.setId(personaForm.getId());
			p.setNombre(personaForm.getNombre());
			p.setApellido(personaForm.getApellido());
			p.setFechaNacimiento(fechaNacimiento);

			try {
				if (p.getId() == null)
					personaService.agregarPersona(p);
				else
					personaService.modificarPersona(p);
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
	
	@RequestMapping("eliminar")
	public String eliminar(@RequestParam Integer id, Model model) {
		List<String> errores = new ArrayList<String>();

		try {
			personaService.eliminarPersona(id);
		} catch (Exception e) {
			e.printStackTrace();
			errores.add("Error eliminando la persona de BD");
		}
		
		if (errores.size() == 0) { // success
			return "redirect:/persona/inicio.do";
		} else { // error
			model.addAttribute("errores", errores);
			init(model);
			return "forward:/WEB-INF/jsp/persona/inicio.jsp";
		}
		
	}

	@RequestMapping("mostrar")
	public String mostrar(@RequestParam Integer id, Model model) {
		Persona p = null;
		List<String> errores = new ArrayList<String>();

		try {
			p = personaService.obtenerPersona(id);
			
			if (p == null)
				errores.add("No existe una persona para el id indicado");
		} catch (Exception e) {
			e.printStackTrace();
			errores.add("Error obteniendo la persona de BD");
		}
		
		if (errores.size() == 0) { // success
			PersonaForm personaForm = new PersonaForm();
			personaForm.setId(p.getId());
			personaForm.setNombre(p.getNombre());
			personaForm.setApellido(p.getApellido());
			if (p.getFechaNacimiento() != null)
				personaForm.setFechaNacimiento(
					new SimpleDateFormat(DATE_MASK).format(p.getFechaNacimiento()));
			
			model.addAttribute("personaForm", personaForm);
		} else { // error
			model.addAttribute("errores", errores);
		}
		
		init(model);
		return "forward:/WEB-INF/jsp/persona/inicio.jsp";
	}
}









