package fr.dauphine.qcm.component.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dauphine.qcm.component.service.IQuestionnaireService;
import fr.dauphine.qcm.component.service.IUserService;

/**
 * Page d'accueil
 */
@Controller
public class IndexController {

	@Autowired
	private IQuestionnaireService questionnaireService;
	
	@Autowired
	private IUserService userService;
	
	/**
	 * Affiche la page d'accueil
	 * 
	 * @return Vue de la page d'accueil
	 */
	@RequestMapping("/")
	public String displayIndexPage(HttpSession session, ModelMap model) {
		model.put("nbQuestionnaires", questionnaireService.getNbQuestionnaires());
		model.put("nbTakenQCM", questionnaireService.getNbResults());
		model.put("listLastQCM", questionnaireService.getLastQuestionnaires());
		model.put("nbUsers", userService.getNbUsers());
		
		
		return "index";
	}
}
