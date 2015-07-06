package test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Persona;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JpaTest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public JpaTest() {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("ejercicio2"); 
	}
	
	@Before
	public void before() {
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}
	
	@After
	public void after() {
		entityTransaction.rollback();
		entityManager.close();
	}
	
	@Test
	public void insertar() {

		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		entityManager.persist(p);
		
		Assert.assertNotNull(p.getId());
	}
}






