package controller;

import javax.validation.Valid;

import model.Ordenador;
import model.Persona;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import service.OrdenadorService;
import service.PersonaService;
import controller.form.OrdenadorForm;
import controller.validator.OrdenadorFormValidator;

@Controller
@RequestMapping("/ordenador/*")
public class OrdenadorController {
	@Autowired
	private PersonaService personaService;
	@Autowired
	private OrdenadorService ordenadorService;
	@Autowired
	private OrdenadorFormValidator ordenadorFormValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(ordenadorFormValidator);
    }
	
	@ModelAttribute("ordenadorForm")
	public OrdenadorForm getOrdenadorForm() {
		return new OrdenadorForm();
	}
	
	@RequestMapping({ "", "inicio" })
	public String inicio(Model model) {
		init(model);
		return "/WEB-INF/jsp/ordenador/inicio.jsp";
	}

	private void init(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());
		model.addAttribute("ordenadores", ordenadorService.obtenerOrdenadores());
	}

	@RequestMapping("guardar")
	public String guardar(Model model, 
			@ModelAttribute("ordenadorForm") @Valid OrdenadorForm ordenadorForm,
			BindingResult result) {
		
		System.out.println("Aquí " + ordenadorForm.getPersona());
		
		if (!result.hasErrors()) { 
			Ordenador o = new ModelMapper().map(ordenadorForm, Ordenador.class);
			o.setPersona(new Persona(ordenadorForm.getPersonaId()));

			try {
				if (o.getId() == null)
					ordenadorService.agregarOrdenador(o);
				else
					ordenadorService.modificarOrdenador(o);
			} catch (Exception e) {
				e.printStackTrace();
				result.rejectValue(null, null, "Error agregando a BD");
			}
		}
		
		if (!result.hasErrors()) { // success
			return "redirect:/ordenador/inicio.do";
		} else {
			init(model);
			return "forward:/WEB-INF/jsp/ordenador/inicio.jsp";
		}

	}
	
	@RequestMapping("eliminar")
	public String eliminar(Model model, 
			@ModelAttribute OrdenadorForm ordenadorForm,
			BindingResult result) {

		try {
			ordenadorService.eliminarOrdenador(ordenadorForm.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.rejectValue(null, null, "Error eliminando el ordenador de BD");
		}
		
		if (!result.hasErrors()) { // success
			return "redirect:/ordenador/inicio.do";
		} else { // error
			init(model);
			return "forward:/WEB-INF/jsp/ordenador/inicio.jsp";
		}
		
	}

	@RequestMapping("mostrar")
	public String mostrar(Model model, 
			@ModelAttribute OrdenadorForm ordenadorForm,
			BindingResult result) {
			
		Ordenador o = null;
		try {
			o = ordenadorService.obtenerOrdenador(ordenadorForm.getId());
			
			if (o == null)
				result.rejectValue(null, null, "No existe un ordenador para el id indicado");
		} catch (Exception e) {
			e.printStackTrace();
			result.rejectValue(null, null, "Error obteniendo el ordenador de BD");
		}
		
		if (!result.hasErrors()) { // success
			new ModelMapper().map(o, ordenadorForm);
			ordenadorForm.setPersonaId(o.getPersona().getId());
		} 
		
		init(model);
		return "forward:/WEB-INF/jsp/ordenador/inicio.jsp";
	}
}









