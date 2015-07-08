package controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import controller.form.PersonaForm;

public class PersonaFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(PersonaForm.class);
	}

	@Override
	public void validate(Object obj, Errors errores) {
		PersonaForm personaForm = (PersonaForm) obj;
		
		if (personaForm.getNombre() == null || 
				personaForm.getNombre().trim().equals("") || 
				personaForm.getNombre().trim().length() < 3)
			errores.rejectValue("nombre", null, "Nombre inválido");
		
		if (personaForm.getApellido() == null || 
				personaForm.getApellido().trim().equals("") || 
				personaForm.getApellido().trim().length() < 3)
			errores.rejectValue("apellido", null, "Apellido inválido");
	}
	
}
