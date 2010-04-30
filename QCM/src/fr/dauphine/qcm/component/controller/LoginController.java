package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.setUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.dauphine.qcm.component.service.IUserService;
import fr.dauphine.qcm.exception.FunctionalException;
import fr.dauphine.qcm.model.User;

/**
 * Page de connexion
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	/**
	 * Service User
	 */
	@Autowired
	private IUserService userService;

	/**
	 * Affiche la page de connexion
	 * 
	 * @return La vue et le modèle (un utilisateur vide)
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelMap displayLoginPage() {
		return new ModelMap("user", new User());
	}

	/**
	 * Traite le formulaire de connexion
	 * 
	 * @param user
	 *            Formulaire
	 * @param result
	 *            Résultat de traitement du formulaire
	 * @param session
	 *            Session web
	 * @return La vue à afficher
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String handleLoginForm(@Valid @ModelAttribute("user") User user,
			BindingResult result, HttpSession session) {
		String view = "login";

		if (!result.hasErrors()) {
			try {
				setUser(session, userService.checkCredentials(user));
				view = "redirect:/";

			} catch (FunctionalException e) {
				result
						.addError(new FieldError("user", "login", e
								.getMessage()));
			}
		}

		return view;
	}
}
