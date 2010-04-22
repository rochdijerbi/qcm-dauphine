package fr.dauphine.qcm.component.service;

import fr.dauphine.qcm.model.Questionnaire;
import fr.dauphine.qcm.model.Result;
import fr.dauphine.qcm.model.User;

public interface IQuestionnaireService {

	Result getResultByUserAndQuestionnaireId(User user, Long questionnaireId);

	Questionnaire getQuestionnaireById(Long id, User user);

	void saveAnswers(Result result);
}
