package fr.dauphine.qcm.component.repository.impl;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.closure.RefreshClosure;
import fr.dauphine.qcm.component.repository.IResultRepository;
import fr.dauphine.qcm.model.Result;

@Repository
public final class ResultRepositoryImpl extends AbstractRepositoryImpl<Result>
		implements IResultRepository {

	public Result loadByUserIdAndQuestionnaireId(Long userId,
			Long questionnaireId) {
		Criteria criteria = getCurrentSession().createCriteria(Result.class);
		criteria.add(Restrictions.eq("user.id", userId));
		criteria.add(Restrictions.eq("questionnaire.id", questionnaireId));

		return (Result) criteria.uniqueResult();
	}

	@Override
	public Result save(Result result) {
		Closure refreshClosure = new RefreshClosure(getCurrentSession());

		refreshClosure.execute(result.getUser());
		refreshClosure.execute(result.getQuestionnaire());
		CollectionUtils.forAllDo(result.getAnswers(), refreshClosure);

		return super.save(result);
	}
}
