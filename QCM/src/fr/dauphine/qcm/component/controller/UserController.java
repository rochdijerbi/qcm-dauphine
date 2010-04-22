package fr.dauphine.qcm.component.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dauphine.qcm.component.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/user.do")
	public String displayProfilePage(@RequestParam(value = "id") Long id,
			ModelMap model) {
		model.put("user", userService.getById(id));
		
		return "user";
	}
}
