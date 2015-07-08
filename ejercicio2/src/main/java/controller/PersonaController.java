package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;

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
			PersonaForm personaForm = getPersonaForm();
			personaForm.setId(p.getId());
			personaForm.setNombre(p.getNombre());
			personaForm.setApellido(p.getApellido());
			personaForm.setFechaNacimiento(p.getFechaNacimiento());
			
			model.addAttribute("personaForm", personaForm);
		} else { // error
			model.addAttribute("errores", errores);
		}
		
		init(model);
		return "forward:/WEB-INF/jsp/persona/inicio.jsp";
	}
}









