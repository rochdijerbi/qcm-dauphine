package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.getUser;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.dauphine.qcm.component.service.IQuestionnaireService;
import fr.dauphine.qcm.model.User;

@Controller
public class ResultController {

	@Autowired
	private IQuestionnaireService questionnaireService;

	@RequestMapping(value = "/result.do")
	public String displayResult(@RequestParam(value = "id") Long id,
			HttpSession session, ModelMap model) {
		User user = getUser(session);

		if (user == null) {
			return "redirect:/login.do";

		} else {
			model.put("result", questionnaireService
					.getResultByUserAndQuestionnaireId(user, id));

			return "result";
		}
	}
}
