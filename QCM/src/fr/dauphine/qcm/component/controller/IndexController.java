package fr.dauphine.qcm.component.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dauphine.qcm.component.service.IUserService;

@Controller
public class IndexController {

	@Autowired
	IUserService userService;
	
	@RequestMapping(value = {"/"})
	public String displayHomePage() {
		userService.getAll(); // test
		return "index";
	}
}
