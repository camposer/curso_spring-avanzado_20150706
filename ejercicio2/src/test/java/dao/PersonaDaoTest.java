package dao;

import java.util.Date;
import java.util.List;

import model.Persona;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.JpaConfigTest;
import dao.PersonaDaoTest.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfigTest.class, Config.class })
public class PersonaDaoTest {
	@Autowired
	private PersonaDao personaDao;
	
	@Test
	@Transactional
	public void agregar() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		
		personaDao.agregar(p);
		
		Assert.assertNotNull(p.getId());
	}
	
	@Test
	@Transactional
	public void modificar() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		personaDao.agregar(p); 
		Assert.assertNotNull(p.getId());
		
		p.setId(p.getId());
		p.setNombre("Juancito");
		p.setApellido("Pérez García");
		
		personaDao.modificar(p);
		
		p = personaDao.obtener(p.getId());
		Assert.assertEquals("Juancito", p.getNombre());
		Assert.assertEquals("Pérez García", p.getApellido());
	}

	@Test
	@Transactional
	public void eliminar() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		personaDao.agregar(p);
		 
		int tamanioInicial = personaDao.obtenerTodos().size();
		
		personaDao.eliminar(p.getId());

		int tamanioFinal = personaDao.obtenerTodos().size();

		Assert.assertTrue(tamanioInicial - 1 == tamanioFinal);
	}

	@Test
	@Transactional
	public void listar() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		personaDao.agregar(p);

		List<Persona> personas = personaDao.obtenerTodos();
		
		Assert.assertTrue(personas.size() > 0);
	}

	@Test
	@Transactional
	public void obtener() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		personaDao.agregar(p);

		Assert.assertNotNull(personaDao.obtener(p.getId()));
	}
	
	@Configuration
	@ComponentScan(basePackages = "dao")
	public static class Config {
		
	}
}
