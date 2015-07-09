package controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import controller.form.OrdenadorForm;

@Component
public class OrdenadorFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(OrdenadorForm.class);
	}

	@Override
	public void validate(Object obj, Errors errores) {
		OrdenadorForm ordenadorForm = (OrdenadorForm) obj;
		
		if (ordenadorForm.getNombre() == null || 
				ordenadorForm.getNombre().trim().equals("") || 
				ordenadorForm.getNombre().trim().length() < 3)
			errores.rejectValue("nombre", null, "Nombre inválido");
		
		if (ordenadorForm.getSerial() == null || 
				ordenadorForm.getSerial().trim().equals("") || 
				ordenadorForm.getSerial().trim().length() < 3)
			errores.rejectValue("serial", null, "Serial inválido");

		if (ordenadorForm.getPersonaId() == null) 
				errores.rejectValue("personaId", null, "Dueño inválido");
	}
	
}
