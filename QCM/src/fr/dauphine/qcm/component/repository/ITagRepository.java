package fr.dauphine.qcm.component.repository;

import java.util.Collection;
import java.util.List;

import fr.dauphine.qcm.model.Questionnaire;
import fr.dauphine.qcm.model.Tag;

public interface ITagRepository extends IAbstractRepository<Tag> {

	Collection<Tag> loadAllOrderedByQuestionnairesSize();

	List<Questionnaire> getListQuestionnaireByTag(String idTag, Integer page,
			boolean isAdmin);

	Long getListNbQuestionnairesValid(String idTag,
			boolean isAdmin);

}
