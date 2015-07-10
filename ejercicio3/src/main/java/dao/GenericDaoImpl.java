package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import annotation.CustomLog;

public abstract class GenericDaoImpl<E, K> implements GenericDao<E, K> {
	@PersistenceContext // Gestiona el entityManager (no transacciones!!!)
	protected EntityManager entityManager;
	protected Class<E> clazz;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		clazz = (Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void agregar(E e) {
		entityManager.persist(e);
	}

	@Override
	public void modificar(E e) {
		entityManager.merge(e);
	}

	@Override
	public void eliminar(K id) {
		E e = obtener(id);
		entityManager.remove(e);
	}

	@Override
	public E obtener(K id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> obtenerTodos() {
		return entityManager.createQuery("from " + clazz.getSimpleName() + 
				" e").getResultList();
	}

}
