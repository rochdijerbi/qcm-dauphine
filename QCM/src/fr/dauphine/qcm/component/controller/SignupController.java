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

@Controller
@RequestMapping("/signup")
public class SignupController {

	@Autowired
	private IUserService userService;


	/**
	 * Affiche l'inscription
	 * 
	 * @return Vue de l'inscription
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelMap displaySignupPage() {
		return new ModelMap(IModelConstants.USER, new User());
	}

	/**
	 * Enregistre l'inscription
	 * 
	 * @return Vue de l'acceuil
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String handleSignupForm(
			@Valid @ModelAttribute(IModelConstants.USER) User user,
			BindingResult result, HttpSession session) {
		String view = "signup";

		if (!result.hasErrors()) {
			try {
				setUser(session, userService.createAccount(user));
				view = "redirect:/";

			} catch (FunctionalException e) {
				result.addError(new FieldError(IModelConstants.USER, "login", e
						.getMessage()));
			}
		}

		return view;
	}
}
