package fr.dauphine.qcm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Formula;


@Entity
public class Tag extends AbstractEntity {

	private static final long serialVersionUID = 6038034486016678022L;

	private String label;

	@ManyToMany(mappedBy = "tags")
	private List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
	
	@Formula(value = "(SELECT COUNT(*) FROM Questionnaire_Tag qt WHERE qt.tags_id = id)")
	private int questionnairesSize;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public int getQuestionnairesSize() {
		return questionnairesSize;
	}

	public void setQuestionnairesSize(int questionnairesSize) {
		this.questionnairesSize = questionnairesSize;
	}
	
	@Override
	public String toString() {
		return getLabel();
	}
}
