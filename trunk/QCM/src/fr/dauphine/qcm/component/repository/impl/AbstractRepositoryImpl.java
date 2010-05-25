package fr.dauphine.qcm.component.repository.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;

import fr.dauphine.qcm.component.controller.IModelConstants;
import fr.dauphine.qcm.component.repository.IAbstractRepository;
import fr.dauphine.qcm.model.Identifiable;

/**
 * Dep™t generique (Implementation Hibernate).
 * 
 * @param <T>
 *            Classe entite
 */
@SuppressWarnings("unchecked")
public abstract class AbstractRepositoryImpl<T extends Identifiable> implements
		IAbstractRepository<T> {
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
		Serializable id = getCurrentSession().save(entity);
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
		criteria.setMaxResults(IModelConstants.RESULTS_BY_PAGE);
		criteria.setFirstResult(IModelConstants.RESULTS_BY_PAGE * page);
	}

	protected void paginate(Query query, int page) {
		query.setMaxResults(IModelConstants.RESULTS_BY_PAGE);
		query.setFirstResult(IModelConstants.RESULTS_BY_PAGE * page);
	}

	public T unproxy(T entity) {
		Hibernate.initialize(entity);
		if (entity instanceof HibernateProxy) {
			entity = (T) ((HibernateProxy) entity)
					.getHibernateLazyInitializer().getImplementation();
		}

		return entity;
	}
}
