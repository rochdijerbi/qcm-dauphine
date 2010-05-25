package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.getUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import fr.dauphine.qcm.component.service.IQuestionnaireService;
import fr.dauphine.qcm.model.Question;
import fr.dauphine.qcm.model.Questionnaire;
import fr.dauphine.qcm.model.Result;
import fr.dauphine.qcm.model.Tag;
import fr.dauphine.qcm.model.User;

@Controller
@SessionAttributes( { IModelConstants.RESULT, IModelConstants.QUESTIONNAIRE })
public class QuestionnaireController {

	@Autowired
	private IQuestionnaireService questionnaireService;
	
	/**
	 * Affiche le questionnaire
	 * 
	 * @return Vue du questionnaire 
	 */
	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.GET)
	public String displayQuestionnairePage(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null) {
			return "redirect:/login";

		} else {
			Result result = new Result();
			result.setUser(user);

			// Les administrateurs peuvent voir tous les questionnaires
			if (user.isAdmin()) {
				result.setQuestionnaire(questionnaireService
						.getQuestionnaireById(id));

			} else {
				result.setQuestionnaire(questionnaireService
						.getQuestionnaireByIdAndUser(id, user));
			}

			model.put(IModelConstants.RESULT, result);
			return "questionnaire/view";
		}
	}

	/**
	 * Sauvegarde et affiche les résultats d'un questionnaire
	 * 
	 * @return Vue des résultats
	 */
	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.POST)
	public String processAnswersForm(
			@Valid @ModelAttribute(IModelConstants.RESULT) Result result,
			BindingResult binding, SessionStatus status, HttpSession session) {

		if (binding.hasErrors()) {
			return "questionnaire/view";

		} else {
			questionnaireService.saveAnswers(result);
			getUser(session).incrementResultsSize();
			status.setComplete();

			return "redirect:/result/" + result.getQuestionnaire().getId();
		}
	}

	/**
	 * Affiche la création d'un questionnaire
	 * 
	 * @return Vue de création d'un questionnaire
	 */
	@RequestMapping(value = "/questionnaire/create", method = RequestMethod.GET)
	public String displayQuestionnaireCreationPage(HttpSession session,
			ModelMap model) {
		User user = getUser(session);

		if (user == null || !user.isAdmin()) {
			return "redirect:/login";

		} else {
			model
					.put(IModelConstants.QUESTIONNAIRE, Questionnaire
							.createEmpty());
			return "questionnaire/edit";
		}
	}

	/**
	 * Affiche un questionnaire en mode modification
	 * 
	 * @return Vue d'édition d'un questionnaire
	 */
	@RequestMapping(value = "/questionnaire/{id}/edit", method = RequestMethod.GET)
	public String displayQuestionnaireEditionPage(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null || !user.isAdmin()) {
			return "redirect:/login";

		} else {
			model.put(IModelConstants.QUESTIONNAIRE, questionnaireService
					.getQuestionnaireById(id));

			return "questionnaire/edit";
		}
	}

	/**
	 * Sauvegarde les modifications d'un questionnaire
	 * 
	 * @return Vue du questionnaire
	 */
	@RequestMapping(value = { "/questionnaire/create",
			"/questionnaire/{id}/edit" }, method = RequestMethod.POST)
	public String handleQuestionnaireCreationAndModificationForm(
			@Valid @ModelAttribute(IModelConstants.QUESTIONNAIRE) Questionnaire questionnaire,
			BindingResult binding, SessionStatus status) {

		if (binding.hasErrors()) {
			return "questionnaire/edit";

		} else {
			questionnaireService.saveQuestionnaire(questionnaire);
			status.setComplete();

			return "redirect:/questionnaire/" + questionnaire.getId();
		}
	}

	/**
	 * Ajoute un tag
	 * 
	 * @return Vue du questionnaire
	 */
	@RequestMapping("/questionnaire/addTag/{tag}")
	public String addTag(
			@ModelAttribute(IModelConstants.QUESTIONNAIRE) Questionnaire questionnaire,
			@PathVariable("tag") String tagLabel) {

		questionnaire.getTags().add(new Tag(tagLabel));
		return "questionnaire/tags";
	}

	/**
	 * Supprime un tag
	 * 
	 * @return Vue du questionnaire
	 */
	@RequestMapping("/questionnaire/deleteTag/{tag}")
	public String deleteTag(
			@ModelAttribute(IModelConstants.QUESTIONNAIRE) Questionnaire questionnaire,
			@PathVariable("tag") String tagLabel) {

		questionnaire.getTags().remove(new Tag(tagLabel));
		return "questionnaire/tags";
	}

	/**
	 * Ajoute une question
	 * 
	 * @return Vue du questionnaire
	 */
	@RequestMapping("/questionnaire/addQuestion")
	public String addQuestion(
			@ModelAttribute(IModelConstants.QUESTIONNAIRE) Questionnaire questionnaire) {

		questionnaire.addQuestion(Question.createEmpty());
		return "questionnaire/edit";
	}

	/**
	 * Supprime une question
	 * 
	 * @return Vue du questionnaire
	 */
	@RequestMapping("/questionnaire/deleteQuestion/{index}")
	public String deleteQuestion(
			@ModelAttribute(IModelConstants.QUESTIONNAIRE) Questionnaire questionnaire,
			@PathVariable("index") Integer index) {

		questionnaire.getQuestions().remove(index.intValue());
		return "questionnaire/edit";
	}


	/**
	 * Affiche la liste des questionnaires
	 * 
	 * @return Vue de la liste des questionnaires
	 */
	@RequestMapping("/questionnaire/questionnairelist/{page}")
	public String displayQuestionnaireList(@PathVariable("page") Integer page,
			HttpSession session, ModelMap model) {
		User userCourant = getUser(session);

		if (userCourant != null) {
			model.put(IModelConstants.LIST_QUESTIONNAIRE, questionnaireService
					.getListQuestionnaire(page, userCourant.isAdmin()));
			model.put(IModelConstants.NB_QUESTIONNAIRES, questionnaireService
					.getNbQuestionnairesValid(userCourant.isAdmin()));
		} else {
			model.put(IModelConstants.LIST_QUESTIONNAIRE, questionnaireService
					.getListQuestionnaire(page, false));
			model.put(IModelConstants.NB_QUESTIONNAIRES, questionnaireService
					.getNbQuestionnairesValid(false));
		}
		model.put(IModelConstants.NB_RESULTS, IModelConstants.RESULTS_BY_PAGE);
		model.put(IModelConstants.PAGE, page);

		return "questionnairelist";
	}
}
