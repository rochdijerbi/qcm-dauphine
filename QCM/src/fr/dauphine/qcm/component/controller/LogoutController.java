package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.unsetUser;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping(value = "/logout.do")
	public String handleLogoutRequest(HttpSession session) {
		unsetUser(session);
		return "redirect:/";
	}
}
