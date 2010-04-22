package fr.dauphine.qcm.component.repository.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
}
