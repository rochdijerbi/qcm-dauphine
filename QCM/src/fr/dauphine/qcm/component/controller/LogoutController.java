package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.unsetUser;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Page de déconnexion
 */
@Controller
public class LogoutController {

	/**
	 * Déconnecte l'utilisateur
	 * 
	 * @param session
	 *            Session web
	 * @return Redirection vers la page d'accueil
	 */
	@RequestMapping("/logout")
	public String handleLogoutRequest(HttpSession session) {
		unsetUser(session);
		return "redirect:/";
	}
}
