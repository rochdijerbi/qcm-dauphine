package fr.dauphine.qcm.component.repository.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.ITagRepository;
import fr.dauphine.qcm.model.Questionnaire;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Questionnaire> getListQuestionnaireByTag(String idTag,
			Integer page, boolean isAdmin) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT q FROM Questionnaire q, Tag t WHERE t.id = ? AND q.id IN elements(t.questionnaires) ");
		if (!isAdmin) {
			sql.append("AND (q.start <= NOW() OR q.start IS NULL) AND (q.end >= NOW() OR q.end IS NULL) ");
		}
		sql.append("ORDER BY q.datecreate DESC");
		
		Query query = getCurrentSession().createQuery(sql.toString());
		query.setString(0, idTag);
		
		paginate(query, page);
		
		return query.list();
	}

	@Override
	public Long getListNbQuestionnairesValid(String idTag, boolean isAdmin) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM Questionnaire q, Tag t WHERE t.id = ? AND q.id IN elements(t.questionnaires) ");
		if (!isAdmin) {
			sql.append("AND (q.start <= NOW() OR q.start IS NULL) AND (q.end >= NOW() OR q.end IS NULL) ");
		}
		
		Query query = getCurrentSession().createQuery(sql.toString());
		query.setString(0, idTag);
		
		return (Long) query.uniqueResult();
	}

}
