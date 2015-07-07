package dao;

import model.Persona;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.JpaConfig;
import dao.PersonaDaoTest.Config;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaConfig.class, Config.class })
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
	public static class Config {
		@Bean
		public PersonaDao personaDao() {
			return new PersonaDaoImpl();
		}
	}
}
