package fr.dauphine.qcm.component.repository.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.closure.SaveOrUpdateClosure;
import fr.dauphine.qcm.component.repository.IQuestionnaireRepository;
import fr.dauphine.qcm.model.Questionnaire;

@Repository
public final class QuestionnaireRepositoryImpl extends
		AbstractRepositoryImpl<Questionnaire> implements
		IQuestionnaireRepository {

	@Override
	public Questionnaire loadForUser(Long questionnaireId, Long userId) {
		Query query = getCurrentSession()
				.createQuery(
						"FROM Questionnaire q WHERE q.id = :questionnaireId AND NOT EXISTS (SELECT 1 FROM Result r WHERE r.questionnaire.id = q.id AND r.user.id = :userId)");

		query.setLong("questionnaireId", questionnaireId);
		query.setLong("userId", userId);

		return (Questionnaire) query.uniqueResult();
	}

	@Override
	public Questionnaire save(Questionnaire questionnaire) {
		Closure closure = new SaveOrUpdateClosure(getCurrentSession());
		CollectionUtils.forAllDo(questionnaire.getTags(), closure);

		return super.save(questionnaire);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Questionnaire> paginateListQuestionnaire(Integer page) {
		Date today = Calendar.getInstance().getTime();
		
		Criteria criteria = getCurrentSession().createCriteria(Questionnaire.class);
		criteria.add(Restrictions.or(Restrictions.le("start", today), Restrictions.isNull("start")));
		criteria.add(Restrictions.or(Restrictions.ge("end", today), Restrictions.isNull("end")));
		criteria.addOrder(Order.desc("datecreate"));

		paginate(criteria, page);

		return criteria.list();
	}
	
	@Override
	public Long getNbQuestionnaires() {
		Query query = getCurrentSession().createQuery("SELECT COUNT(*) FROM Questionnaire q");
		
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questionnaire> getLastQuestionnaires() {
		Date today = Calendar.getInstance().getTime();
		
		Criteria criteria = getCurrentSession().createCriteria(Questionnaire.class);
		criteria.add(Restrictions.or(Restrictions.le("start", today), Restrictions.isNull("start")));
		criteria.add(Restrictions.or(Restrictions.ge("end", today), Restrictions.isNull("end")));
		criteria.addOrder(Order.desc("datecreate"));
		criteria.setMaxResults(NB_RESULTS_LAST_QCM);

		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questionnaire> getPopularQuestionnaires() {
		Query query = getCurrentSession().createQuery("FROM Questionnaire q WHERE resultsSize != 0 AND (q.start <= NOW() OR q.start IS NULL) AND (q.end >= NOW() OR q.end IS NULL) ORDER BY resultsSize DESC");
		query.setMaxResults(NB_RESULTS_POPULAR_QCM);
	
		return query.list();
	}

	@Override
	public Long getNbQuestionnairesValid() {
		Query query = getCurrentSession().createQuery("SELECT COUNT(*) FROM Questionnaire q WHERE q.start < NOW() AND q.end > NOW()");
		
		return (Long) query.uniqueResult();
	}
	
}
