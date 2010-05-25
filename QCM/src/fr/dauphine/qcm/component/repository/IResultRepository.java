package fr.dauphine.qcm.component.repository;

import fr.dauphine.qcm.model.Result;

public interface IResultRepository extends IAbstractRepository<Result> {

	/**
	 * Renvoie les résultats d'un utilisateur à un questionnaire
	 * 
	 * @param userId
	 *            , questionnaireId
	 * @return Résultat du questionnaire
	 */
	Result loadByUserIdAndQuestionnaireId(Long userId, Long questionnaireId);

	/**
	 * Renvoie le nombre de résultats
	 * 
	 * @return Nombre de résultats
	 */
	Long getNbResults();

}
