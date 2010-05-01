package fr.dauphine.qcm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Formula;

@Entity
public class Tag implements Serializable, Comparable<Tag>, Identifiable<String> {

	private static final long serialVersionUID = 6038034486016678022L;

	@Id
	private String id;

	@ManyToMany(mappedBy = "tags")
	private List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();

	@Formula(value = "(SELECT COUNT(*) FROM Questionnaire_Tag qt WHERE qt.tags_id = id)")
	private int questionnairesSize;

	public Tag() {
	}

	public Tag(String id) {
		setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && (obj instanceof Tag) && equals((Tag) obj);
	}

	private boolean equals(Tag tag) {
		return getId() != null && tag.getId() != null
				&& getId().equals(tag.getId());
	}

	@Override
	public int hashCode() {
		if (getId() == null) {
			return 0;
		}

		return getId().hashCode();
	}

	@Override
	public int compareTo(Tag o) {
		return getId().compareToIgnoreCase(o.getId());
	}

}
