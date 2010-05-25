package fr.dauphine.qcm.component.controller;

import static fr.dauphine.qcm.util.UserUtil.getUser;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.dauphine.qcm.component.service.ITagService;
import fr.dauphine.qcm.model.User;

@Controller
public class TagController {

	@Autowired
	private ITagService tagService;

	@RequestMapping("/tag")
	public String displayTagCloud(ModelMap model) {
		model.put(IModelConstants.TAGS, tagService.getAll());
		return "tag";
	}
	
	@RequestMapping("/tag/{idTag}/{page}")
	public String displayQuestionnaireList(@PathVariable("idTag") String idTag, @PathVariable("page") Integer page,
			HttpSession session, ModelMap model) {
		User userCourant = getUser(session);
		
		if(userCourant != null) {
			model.put("listQuestionnaireByTag", tagService.getListQuestionnaireByTag(idTag, page, userCourant.isAdmin()));
			model.put(IModelConstants.NB_QUESTIONNAIRES, tagService.getNbQuestionnairesValid(idTag, userCourant.isAdmin()));
		}
		else {
			model.put("listQuestionnaireByTag", tagService.getListQuestionnaireByTag(idTag, page, false));
			model.put(IModelConstants.NB_QUESTIONNAIRES, tagService.getNbQuestionnairesValid(idTag, false));
		}
		
		model.put(IModelConstants.PAGE, page);
		model.put("nbResults",IModelConstants.RESULTS_BY_PAGE);
		model.put(IModelConstants.TAG, idTag);
		
		return "taglist";
	}
		
}
