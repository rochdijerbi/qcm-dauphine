package fr.dauphine.qcm.component.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.dauphine.qcm.component.repository.IAbstractRepository;
import fr.dauphine.qcm.model.AbstractEntity;

/**
 * Dep™t generique (Implementation Hibernate).
 * 
 * @param <T>
 *            Classe entite
 */
@SuppressWarnings("unchecked")
public abstract class AbstractRepositoryImpl<T extends AbstractEntity>
		implements IAbstractRepository<T> {

	/**
	 * Nombre de resultats par page.
	 */
	private static final int NB_RESULTS_BY_PAGE = 10;

	/**
	 * Session factory Hibernate.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	private Class templateClass;

	public AbstractRepositoryImpl() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();

		templateClass = (Class) parameterizedType.getActualTypeArguments()[0];
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> loadAll() {
		return getCurrentSession().createCriteria(templateClass).list();
	}

	/**
	 * {@inheritDoc}
	 */
	public T load(Serializable primaryKey) {
		return (T) getCurrentSession().load(templateClass, primaryKey);
	}

	/**
	 * {@inheritDoc}
	 */
	public T save(T entity) {
		Long id = (Long) getCurrentSession().save(entity);
		entity.setId(id);
		
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(T entity) {
		getCurrentSession().update(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void evict(T entity) {
		getCurrentSession().evict(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public void refresh(T entity) {
		getCurrentSession().refresh(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	public T merge(T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	/**
	 * Recupere la session actuellement ouverte.
	 * 
	 * @return La session actuellement ouverte
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void paginate(Criteria criteria, int page) {
		criteria.setMaxResults(NB_RESULTS_BY_PAGE);
		criteria.setFirstResult(NB_RESULTS_BY_PAGE * page);
	}

	protected void paginate(Query query, int page) {
		query.setMaxResults(NB_RESULTS_BY_PAGE);
		query.setFirstResult(NB_RESULTS_BY_PAGE * page);
	}
}
