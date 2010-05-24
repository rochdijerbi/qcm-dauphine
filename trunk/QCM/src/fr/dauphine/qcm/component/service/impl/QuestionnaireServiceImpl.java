package fr.dauphine.qcm.component.service.impl;

import java.util.List;

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
	@Transactional
	public void saveAnswers(Result result) {
		resultRepository.save(result);
	}

	@Override
	@Transactional
	public void saveQuestionnaire(Questionnaire questionnaire) {
		questionnaireRepository.save(questionnaire);
	}

	@Override
	@Transactional(readOnly = true)
	public Questionnaire getQuestionnaireById(Long id) {
		Questionnaire questionnaire = questionnaireRepository.load(id);

		if (questionnaire != null) {
			questionnaire.shuffleQuestions();
		}

		return questionnaire;
	}

	@Override
	@Transactional(readOnly = true)
	public Questionnaire getQuestionnaireByIdAndUser(Long id, User user) {
		Questionnaire questionnaire = questionnaireRepository.loadForUser(id,
				user.getId());

		if (questionnaire != null) {
			questionnaire.shuffleQuestions();
		}

		return questionnaire;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Questionnaire> getListQuestionnaire(Integer page) {
		return questionnaireRepository.paginateListQuestionnaire(page);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long getNbQuestionnaires() {
		return questionnaireRepository.getNbQuestionnaires();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long getNbResults() {
		return resultRepository.getNbResults();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Questionnaire> getLastQuestionnaires() {
		return questionnaireRepository.getLastQuestionnaires();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Questionnaire> getPopularQuestionnaires() {
		return questionnaireRepository.getPopularQuestionnaires();
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNbQuestionnairesValid() {
		return questionnaireRepository.getNbQuestionnairesValid();
	}
}
