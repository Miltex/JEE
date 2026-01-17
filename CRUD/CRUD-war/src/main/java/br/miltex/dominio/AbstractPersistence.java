/**
 * 
 */
package br.miltex.dominio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author Milton Ferreira Lima Filho
 * @version 1
 */
public abstract class AbstractPersistence<PK extends Number, T extends AbstractEntity> {

	private Class<T> entityClass;
	
	/*
	 * Define o metodo abstrato que deve ser implementado por todas as classes concretas
	 * que herdarem desta classe
	 */
	protected abstract EntityManager getEntityManager();

	public AbstractPersistence(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public T save(T e) {
		//verifica se objeto está no PC
		if (e.getId() != null)
			return getEntityManager().merge(e);
		else {
			getEntityManager().persist(e);
			return e;
		}
	}

	public void remove(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	public T find(PK id) {
		return getEntityManager().find(entityClass, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder()
				.createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

/*	public List<T> findRange(int[] range) {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder()
				.createQuery();
		cq.select(cq.from(entityClass));
		Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	public int count() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder()
				.createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}*/
}
