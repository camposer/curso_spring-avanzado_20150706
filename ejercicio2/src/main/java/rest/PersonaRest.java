package rest;

import model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rest.response.JsonResponse;
import service.PersonaService;

@RestController
public class PersonaRest {
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/personas", produces = "application/json")
	public @ResponseBody JsonResponse obtenerPersonas() {
		try {
			return JsonResponse.success(personaService.obtenerPersonas());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.error(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/personas/{id}", produces = "application/json")
	public @ResponseBody JsonResponse obtenerPersona(@PathVariable Integer id) {
		try {
			return JsonResponse.success(personaService.obtenerPersona(id));
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.error(e.getMessage());
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/personas/{id}", produces = "application/json")
	public @ResponseBody JsonResponse eliminarPersona(@PathVariable Integer id) {
		try {
			personaService.eliminarPersona(id);
			return JsonResponse.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.error(e.getMessage());
		}
	}

	/**
	 * POST /api/personas
	 * Content-type: application/json
	 * {
	 * 	"nombre": "Juan",
	 * 	"apellido": "Pérez",
	 *  "fechaNacimiento": null
	 * }
	 */
	@RequestMapping(method = RequestMethod.POST, 
			value = "/personas", 
			consumes = "application/json",
			produces = "application/json")
	public @ResponseBody JsonResponse agregarPersona(@RequestBody Persona p) {
		try {
			personaService.agregarPersona(p);
			return JsonResponse.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.error(e.getMessage());
		}
	}
	/**
	 * PUT /api/personas/1
	 * Content-type: application/json
	 * {
	 * 	"nombre": "Juan",
	 * 	"apellido": "Pérez",
	 *  "fechaNacimiento": null
	 * }
	 */
	@RequestMapping(method = RequestMethod.PUT, 
			value = "/personas/{id}", 
			consumes = "application/json",
			produces = "application/json")
	public @ResponseBody JsonResponse modificarPersona(@PathVariable Integer id,
			@RequestBody Persona p) {
		try {
			p.setId(id);
			personaService.modificarPersona(p);
			return JsonResponse.success(null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.error(e.getMessage());
		}
	}

}




