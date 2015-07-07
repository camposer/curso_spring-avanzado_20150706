package dao;

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
	
	@Configuration
	@ComponentScan(basePackages = "dao")
	public static class Config {
		
	}
}
