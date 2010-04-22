package fr.dauphine.qcm.component.repository;

import fr.dauphine.qcm.model.Questionnaire;

public interface IQuestionnaireRepository extends
		IAbstractRepository<Questionnaire> {

	Questionnaire loadForUser(Long questionnaireId, Long id);

}
