package dao;

import java.util.List;

import model.Persona;

import org.springframework.stereotype.Repository;

@Repository
public class PersonaDaoImpl
		extends GenericDaoImpl<Persona, Integer>
		implements PersonaDao {

	@Override
	public List<Persona> obtenerMayoresDeEdad() {
		// TODO Auto-generated method stub
		return null;
	}

}
