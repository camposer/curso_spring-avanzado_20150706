package service;

import java.util.List;

import javax.annotation.Resource;

import model.Ordenador;
import model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import annotation.CustomLog;
import dao.PersonaDao;

@Service
public class OrdenadorServiceImpl implements OrdenadorService {
	@Autowired
	private OrdenadorDao ordenadorDao;
	@Resource(name = "personaDaoProxy") 
	private PersonaDao personaDao;

	@Override
	@Transactional
	public void agregarOrdenador(Ordenador o) {
		Persona p = personaDao.obtener(o.getPersona().getId());
		
		if (p == null) // TODO Escribir excepción personalizada
			throw new RuntimeException("No existe una persona para el id especificado");
			
		o.setPersona(p);
		ordenadorDao.agregar(o);
	}

	@Override
	@Transactional
	public void modificarOrdenador(Ordenador o) {
		ordenadorDao.modificar(o);
	}

	@Override
	@Transactional
	public void eliminarOrdenador(Integer id) {
		ordenadorDao.eliminar(id);
	}

	@CustomLog
	@Override
	public List<Ordenador> obtenerOrdenadores() {
		return ordenadorDao.obtenerTodos();
	}

	@Override
	public Ordenador obtenerOrdenador(Integer id) {
		return ordenadorDao.obtener(id);
	}

}
