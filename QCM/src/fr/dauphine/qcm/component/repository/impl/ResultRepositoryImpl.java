package fr.dauphine.qcm.component.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IResultRepository;
import fr.dauphine.qcm.model.Answer;
import fr.dauphine.qcm.model.Result;

@Repository
public final class ResultRepositoryImpl extends AbstractRepositoryImpl<Result>
		implements IResultRepository {

	@Override
	public Result loadByUserIdAndQuestionnaireId(Long userId,
			Long questionnaireId) {
		Criteria criteria = getCurrentSession().createCriteria(Result.class);
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("questionnaire.id", questionnaireId));

		return (Result) criteria.uniqueResult();
	}
	
	@Override
	public void save(Result result) {
		refreshBeforeSave(result);
		super.save(result);
	}
	
	private void refreshBeforeSave(Result result) {
		Session session = getCurrentSession();
		
		session.refresh(result.getQuestionnaire());
		session.refresh(result.getUser());
		
		for (Answer answer : result.getAnswers()) {
			session.refresh(answer);
		}
	}
}
