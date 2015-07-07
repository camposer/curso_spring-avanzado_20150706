package jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Persona;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BasicoTest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public BasicoTest() {
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
	
	@Test
	public void modificar() {
		Persona pTransient = new Persona();
		pTransient.setNombre("Juan");
		pTransient.setApellido("Pérez");
		pTransient.setFechaNacimiento(new Date());
		
		// Agregando
		Persona pPersistent = entityManager.merge(pTransient); 
			
		Assert.assertNotNull(pPersistent.getId());
		
		// Modificando
		pTransient.setId(pPersistent.getId());
		pTransient.setNombre("Juancito");
		pTransient.setApellido("Pérez García");
		
		pPersistent = entityManager.merge(pTransient);
		
		Assert.assertEquals("Juancito", pPersistent.getNombre());
		Assert.assertEquals("Pérez García", pPersistent.getApellido());
	}

	@Test
	public void eliminar() {
		Persona pTransient = new Persona();
		pTransient.setNombre("Juan");
		pTransient.setApellido("Pérez");
		pTransient.setFechaNacimiento(new Date());
		
		// Agregando
		Persona pPersistent = entityManager.merge(pTransient); 
			
		int tamanioInicial = entityManager.createNamedQuery("Persona.findAll")
				.getResultList().size();
		
		// Eliminando
		entityManager.remove(pPersistent);

		int tamanioFinal = entityManager.createNamedQuery("Persona.findAll")
				.getResultList().size();

		Assert.assertTrue(tamanioInicial - 1 == tamanioFinal);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listar() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		entityManager.persist(p);

		List<Persona> personas = 
				entityManager.createNamedQuery("Persona.findAll")
					.getResultList();
		
		Assert.assertTrue(personas.size() == 1);
	}

	@Test
	public void obtener() {
		Persona p = new Persona();
		p.setNombre("Juan");
		p.setApellido("Pérez");
		p.setFechaNacimiento(new Date());
		
		entityManager.persist(p);

		Query q = entityManager.createQuery("from Persona p where p.id = :id");
		q.setParameter("id", p.getId());
		
		Assert.assertTrue(q.getResultList().size() == 1);
	}

}






