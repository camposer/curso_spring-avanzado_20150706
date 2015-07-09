package service;

import java.util.List;

import model.Ordenador;
import model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.PersonaDao;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaDao personaDao;
	@Autowired
	private OrdenadorDao ordenadorDao;
	
	@Override
	@Transactional
	public void agregarPersona(Persona p) {
		personaDao.agregar(p);
	}

	@Override
	@Transactional
	public void modificarPersona(Persona p) {
		personaDao.modificar(p);
	}

	@Override
	@Transactional
	public void eliminarPersona(Integer id) {
		List<Ordenador> ordenadores = personaDao.obtener(id).getOrdenadores();
		
		// Eliminando primero los ordenadores de la persona
		if (ordenadores != null) for (Ordenador o : ordenadores)
			ordenadorDao.eliminar(o.getId());
		
		personaDao.eliminar(id);
	}

	@Override
	public List<Persona> obtenerPersonas() {
		return personaDao.obtenerTodos();
	}

	@Override
	public Persona obtenerPersona(Integer id) {
		return personaDao.obtener(id);
	}

}
