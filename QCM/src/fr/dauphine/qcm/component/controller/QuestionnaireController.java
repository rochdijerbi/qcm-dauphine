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
import fr.dauphine.qcm.model.Result;
import fr.dauphine.qcm.model.User;

@Controller
@RequestMapping("/questionnaire/{id}")
@SessionAttributes("result")
public class QuestionnaireController {

	@Autowired
	private IQuestionnaireService questionnaireService;

	@RequestMapping(method = RequestMethod.GET)
	public String displayQuestionnairePage(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null) {
			return "redirect:/login";

		} else {
			Result result = new Result();
			result.setUser(user);
			result.setQuestionnaire(questionnaireService.getQuestionnaireById(
					id, user));

			model.put("result", result);
			return "questionnaire/view";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
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
}
