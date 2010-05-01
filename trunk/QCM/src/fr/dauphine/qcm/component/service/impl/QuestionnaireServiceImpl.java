package fr.dauphine.qcm.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dauphine.qcm.component.repository.IQuestionnaireRepository;
import fr.dauphine.qcm.component.repository.IResultRepository;
import fr.dauphine.qcm.component.service.IQuestionnaireService;
import fr.dauphine.qcm.model.Questionnaire;
import fr.dauphine.qcm.model.Result;
import fr.dauphine.qcm.model.User;

@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService {

	@Autowired
	IResultRepository resultRepository;

	@Autowired
	IQuestionnaireRepository questionnaireRepository;

	@Override
	@Transactional(readOnly = true)
	public Result getResultByUserAndQuestionnaireId(User user,
			Long questionnaireId) {
		Result result = resultRepository.loadByUserIdAndQuestionnaireId(user
				.getId(), questionnaireId);

		if (result != null) {
			result.getQuestionnaire().shuffleQuestions();
		}

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Questionnaire getQuestionnaireById(Long questionnaireId, User user) {
		Questionnaire questionnaire = questionnaireRepository.loadForUser(
				questionnaireId, user.getId());

		if (questionnaire != null) {
			questionnaire.shuffleQuestions();
		}

		return questionnaire;
	}

	@Override
	@Transactional
	public void saveAnswers(Result result) {
		resultRepository.save(result);
	}
}
