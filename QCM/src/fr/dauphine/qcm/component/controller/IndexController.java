package fr.dauphine.qcm.component.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page d'accueil
 */
@Controller
public class IndexController {

	/**
	 * Affiche la page d'accueil
	 * 
	 * @return Vue de la page d'accueil
	 */
	@RequestMapping("/")
	public String displayIndexPage() {
		// test
		return "index";
	}
}
