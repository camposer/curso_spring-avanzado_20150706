package controller;

import java.util.Date;

import javax.validation.Valid;

import model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.PersonaService;
import controller.editor.FechaEditor;
import controller.form.PersonaForm;
import controller.validator.PersonaFormValidator;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	@Autowired
	private PersonaService personaService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new FechaEditor());
        binder.addValidators(new PersonaFormValidator());
    }
	
	@ModelAttribute("personaForm")
	public PersonaForm getPersonaForm() {
		return new PersonaForm();
	}
	
	@RequestMapping({ "", "inicio" })
	public String inicio(Model model) {
		init(model);
		return "/WEB-INF/jsp/persona/inicio.jsp";
	}

	private void init(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
	}

	@RequestMapping("guardar")
	public String guardar(Model model, 
			@ModelAttribute("personaForm") @Valid PersonaForm personaForm,
			BindingResult result) {
		
		if (!result.hasErrors()) { 
			Persona p = new Persona();
			p.setId(personaForm.getId());
			p.setNombre(personaForm.getNombre());
			p.setApellido(personaForm.getApellido());
			p.setFechaNacimiento(personaForm.getFechaNacimiento());

			try {
				if (p.getId() == null)
					personaService.agregarPersona(p);
				else
					personaService.modificarPersona(p);
			} catch (Exception e) {
				e.printStackTrace();
				result.addError(null);
			}
		}
		
		if (!result.hasErrors()) { // success
			return "redirect:/persona/inicio.do";
		} else {
			init(model);
			return "forward:/WEB-INF/jsp/persona/inicio.jsp";
		}

	}
	
	@RequestMapping("eliminar")
	public String eliminar(Model model, 
			@ModelAttribute("personaForm") PersonaForm personaForm,
			BindingResult result) {

		try {
			personaService.eliminarPersona(personaForm.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.rejectValue(null, null, "Error eliminando la persona de BD");
		}
		
		if (!result.hasErrors()) { // success
			return "redirect:/persona/inicio.do";
		} else { // error
			init(model);
			return "forward:/WEB-INF/jsp/persona/inicio.jsp";
		}
		
	}

	@RequestMapping("mostrar")
	public String mostrar(Model model, 
			@ModelAttribute("personaForm") PersonaForm personaForm,
			BindingResult result) {
		Persona p = null;

		try {
			p = personaService.obtenerPersona(personaForm.getId());
			
			if (p == null)
				result.rejectValue(null, null, "No existe una persona para el id indicado");
		} catch (Exception e) {
			e.printStackTrace();
			result.rejectValue(null, null, "Error obteniendo la persona de BD");
		}
		
		if (!result.hasErrors()) { // success
			personaForm.setId(p.getId());
			personaForm.setNombre(p.getNombre());
			personaForm.setApellido(p.getApellido());
			personaForm.setFechaNacimiento(p.getFechaNacimiento());
		} 
		
		init(model);
		return "forward:/WEB-INF/jsp/persona/inicio.jsp";
	}
}









