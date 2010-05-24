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

/**
 * Service des questionnaires (implementation).
 */
@Service
@Transactional(readOnly = true)
public class QuestionnaireServiceImpl implements IQuestionnaireService {

	/**
	 * Depot des resultats.
	 */
	@Autowired
	IResultRepository resultRepository;

	/**
	 * Depot des questionnaires.
	 */
	@Autowired
	IQuestionnaireRepository questionnaireRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Result getResultByUserAndQuestionnaireId(User user,
			Long questionnaireId) {
		Result result = resultRepository.loadByUserIdAndQuestionnaireId(user
				.getId(), questionnaireId);

		if (result != null) {
			result.getQuestionnaire().shuffleQuestions();
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveAnswers(Result result) {
		resultRepository.save(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveQuestionnaire(Questionnaire questionnaire) {
		questionnaireRepository.saveOrUpdate(questionnaire);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Questionnaire getQuestionnaireById(Long id) {
		Questionnaire questionnaire = questionnaireRepository.load(id);

		if (questionnaire != null) {
			questionnaire.getTags().size(); // Lazy

			questionnaire.shuffleQuestions();

			questionnaire = questionnaireRepository.unproxy(questionnaire);
		}

		return questionnaire;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Questionnaire getQuestionnaireByIdAndUser(Long id, User user) {
		Questionnaire questionnaire = questionnaireRepository.loadForUser(id,
				user.getId());

		if (questionnaire != null) {
			questionnaire.getTags().size(); // Lazy

			questionnaire.shuffleQuestions();

			questionnaire = questionnaireRepository.unproxy(questionnaire);
		}

		return questionnaire;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Questionnaire> getListQuestionnaire(Integer page, boolean admin) {
		return loadTags(questionnaireRepository.paginateListQuestionnaire(page,
				admin));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbQuestionnaires() {
		return questionnaireRepository.getNbQuestionnaires();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbResults() {
		return resultRepository.getNbResults();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Questionnaire> getLastQuestionnaires(boolean admin) {
		return loadTags(questionnaireRepository.getLastQuestionnaires(admin));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Questionnaire> getPopularQuestionnaires(boolean admin) {
		return loadTags(questionnaireRepository.getPopularQuestionnaires(admin));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getNbQuestionnairesValid(boolean admin) {
		return questionnaireRepository.getNbQuestionnairesValid(admin);
	}

	/**
	 * Charge la propriete "tags" de chaque bean de la liste, qui est en lazy
	 * loading.
	 */
	private List<Questionnaire> loadTags(List<Questionnaire> questionnaires) {
		for (Questionnaire questionnaire : questionnaires) {
			questionnaire.getTags().size(); // Lazy
		}

		return questionnaires;
	}
}
