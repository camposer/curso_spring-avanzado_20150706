package service;

import java.util.List;

import model.Persona;

public interface PersonaService {
	void agregarPersona(Persona p);
	void modificarPersona(Persona p);
	void eliminarPersona(Integer id);
	List<Persona> obtenerPersonas();
	Persona obtenerPersona(Integer id);
}
