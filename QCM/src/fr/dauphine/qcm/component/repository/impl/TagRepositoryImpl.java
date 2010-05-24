package fr.dauphine.qcm.component.repository.impl;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.ITagRepository;
import fr.dauphine.qcm.model.Tag;

@Repository
public class TagRepositoryImpl extends AbstractRepositoryImpl<Tag> implements
		ITagRepository {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<Tag> loadAllOrderedByQuestionnairesSize() {
		Criteria criteria = getCurrentSession().createCriteria(Tag.class);
		criteria.addOrder(Order.desc("questionnairesSize"));

		return criteria.list();
	}

}
