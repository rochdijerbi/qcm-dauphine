package fr.dauphine.qcm.component.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dauphine.qcm.component.service.ITagService;

@Controller
public class TagController {

	@Autowired
	private ITagService tagService;

	@RequestMapping("/tag")
	public String displayTagCloud(ModelMap model) {
		model.put("tags", tagService.getAll());
		return "tag";
	}
}
