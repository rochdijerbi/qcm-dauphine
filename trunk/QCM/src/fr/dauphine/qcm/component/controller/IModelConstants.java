package fr.dauphine.qcm.component.controller;

/**
 * Cles des differents beans stockes dans le modele.
 */
public interface IModelConstants {

	/**
	 * Resultat d'un questionnaire.
	 */
	String RESULT = "result";
	
	/**
	 * Questionnaire.
	 */
	String QUESTIONNAIRE  = "questionnaire";
	
	/**
	 * Utilisateur (ou liste d'utilisateurs)
	 */
	String USER = "user";
	
	/**
	 * Tag recherche
	 */
	String TAG = "tag";
	
	/**
	 * Liste de questionnaires
	 */
	String LIST_QUESTIONNAIRE = "listQuestionnaire";
	
	/**
	 * Nombre total de questionnaires
	 */
	String NB_QUESTIONNAIRES = "nbQuestionnaires";
	
	/**
	 * Page courante
	 */
	String PAGE = "page";
	
	/**
	 * Nombre de resultats affiches par page
	 */
	String NB_RESULTS = "nbResults";
	
	/**
	 * Nombre de resultats affiches par page
	 */
	int RESULTS_BY_PAGE = 2;
	
	/**
	 * Nombre de derniers QCM ˆ afficher
	 */
	int NB_RESULTS_LAST_QCM = 5;
	
	/**
	 * Nombre de qcm populaires ˆ afficher
	 */
	int NB_RESULTS_POPULAR_QCM = 5;
	
	/**
	 * Code d'erreur
	 */
	String CODE = "code";
	
	/**
	 * Liste des tags
	 */
	String TAGS = "tags";
}
