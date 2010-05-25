package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.getUser;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dauphine.qcm.component.service.IQuestionnaireService;
import fr.dauphine.qcm.model.User;

@Controller
public class ResultController {

	@Autowired
	private IQuestionnaireService questionnaireService;


	/**
	 * Affiche les résultats
	 * 
	 * @return Vue des résultats
	 */
	@RequestMapping("/result/{id}")
	public String displayResult(@PathVariable("id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null) {
			return "redirect:/login";

		} else {
			model.put(IModelConstants.RESULT, questionnaireService
					.getResultByUserAndQuestionnaireId(user, id));

			return "result";
		}
	}
}
