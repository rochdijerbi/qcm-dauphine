package fr.dauphine.qcm.component.repository;

import java.util.List;

import fr.dauphine.qcm.model.Questionnaire;

public interface IQuestionnaireRepository extends
		IAbstractRepository<Questionnaire> {

	Questionnaire loadForUser(Long questionnaireId, Long id);

	List<Questionnaire> paginateListQuestionnaire(Integer page, boolean admin);
	
	Long getNbQuestionnaires();

	List<Questionnaire> getLastQuestionnaires(boolean admin);

	List<Questionnaire> getPopularQuestionnaires(boolean admin);

	Long getNbQuestionnairesValid(boolean admin);
	
}
