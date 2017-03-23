package org.jboss.bakery.data.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractDao<T> implements IAbstractDao<T> {

	@PersistenceContext
	private EntityManager entityManager;
	private final Class<T> forClass;

	protected AbstractDao(Class<T> forClass) {
		this.forClass = forClass;
	}

	protected final EntityManager getEntityManager() {
		return this.entityManager;
	}

	public final void flush() {
		this.entityManager.flush();
	}

	public T create(T entity) {
		this.entityManager.persist(entity);
		return entity;
	}

	public T update(T entity) {
		return this.entityManager.merge(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public T findById(Object pk) {
		return this.entityManager.find(this.forClass, pk);
	}

	public List<T> findAll() {
		return getResultList(this.entityManager.createQuery("FROM " + this.forClass.getName()));
	}

	public void refresh(T entity) {
		this.entityManager.refresh(entity);
	}

	@SuppressWarnings("unchecked")
	protected List<T> getResultList(Query query) {
		return query.getResultList();
	}

	protected T findSingleResult(Query query, String errorMessage) {
		List<T> resultList = getResultList(query);
		if (resultList.isEmpty()) {
			return null;
		}
		if (resultList.size() == 1) {
			return resultList.get(0);
		}
		throw new IllegalStateException(errorMessage);
	}

	public T merge(T entity) {
		return this.entityManager.merge(entity);
	}
}
