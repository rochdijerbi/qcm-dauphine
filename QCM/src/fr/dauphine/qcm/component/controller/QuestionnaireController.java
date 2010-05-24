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
import fr.dauphine.qcm.util.UserUtil;

@Controller
@SessionAttributes( { "result", "questionnaire" })
public class QuestionnaireController {

	@Autowired
	private IQuestionnaireService questionnaireService;

	/**
	 * Nombre de resultats par page.
	 */
	private static final int NB_RESULTS_BY_PAGE = 2;

	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.GET)
	public String displayQuestionnairePage(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null) {
			return "redirect:/login";

		} else {
			Result result = new Result();
			result.setUser(user);
			result.setQuestionnaire(questionnaireService
					.getQuestionnaireByIdAndUser(id, user));

			model.put("result", result);
			return "questionnaire/view";
		}
	}

	@RequestMapping(value = "/questionnaire/{id}", method = RequestMethod.POST)
	public String processAnswersForm(
			@Valid @ModelAttribute("result") Result result,
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

	@RequestMapping(value = "/questionnaire/create", method = RequestMethod.GET)
	public String displayQuestionnaireCreationPage(HttpSession session,
			ModelMap model) {
		User user = getUser(session);

		if (user == null || !user.isAdmin()) {
			return "redirect:/login";

		} else {
			model.put("questionnaire", Questionnaire.createEmpty());
			return "questionnaire/edit";
		}
	}

	@RequestMapping(value = "/questionnaire/{id}/edit", method = RequestMethod.GET)
	public String displayQuestionnaireEditionPage(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null || !user.isAdmin()) {
			return "redirect:/login";

		} else {
			model.put("questionnaire", questionnaireService
					.getQuestionnaireById(id));

			return "questionnaire/edit";
		}
	}

	@RequestMapping(value = { "/questionnaire/create",
			"/questionnaire/{id}/edit" }, method = RequestMethod.POST)
	public String handleQuestionnaireCreationAndModificationForm(
			@Valid @ModelAttribute("questionnaire") Questionnaire questionnaire,
			BindingResult binding, SessionStatus status) {

		if (binding.hasErrors()) {
			return "questionnaire/edit";

		} else {
			questionnaireService.saveQuestionnaire(questionnaire);
			status.setComplete();

			return "redirect:/questionnaire/" + questionnaire.getId();
		}
	}

	@RequestMapping("/questionnaire/addTag/{tag}")
	public String addTag(
			@ModelAttribute("questionnaire") Questionnaire questionnaire,
			@PathVariable("tag") String tagLabel) {

		questionnaire.getTags().add(new Tag(tagLabel));
		return "questionnaire/tags";
	}

	@RequestMapping("/questionnaire/deleteTag/{tag}")
	public String deleteTag(
			@ModelAttribute("questionnaire") Questionnaire questionnaire,
			@PathVariable("tag") String tagLabel) {

		questionnaire.getTags().remove(new Tag(tagLabel));
		return "questionnaire/tags";
	}

	@RequestMapping("/questionnaire/addQuestion")
	public String addQuestion(
			@ModelAttribute("questionnaire") Questionnaire questionnaire) {

		questionnaire.addQuestion(Question.createEmpty());
		return "questionnaire/edit";
	}

	@RequestMapping("/questionnaire/questionnairelist/{page}")
	public String handleQuestionnaireList(@PathVariable("page") Integer page,
			HttpSession session, ModelMap model) {
		User userCourant = UserUtil.getUser(session);

		if (userCourant != null) {
			model.put("listQuestionnaire", questionnaireService
					.getListQuestionnaire(page, userCourant.isAdmin()));
			model.put("nbQuestionnaires", questionnaireService
					.getNbQuestionnairesValid(userCourant.isAdmin()));
		} else {
			model.put("listQuestionnaire", questionnaireService
					.getListQuestionnaire(page, false));
			model.put("nbQuestionnaires", questionnaireService
					.getNbQuestionnairesValid(false));
		}
		model.put("nbResults", NB_RESULTS_BY_PAGE);
		model.put("page", page);

		return "questionnairelist";
	}
}
