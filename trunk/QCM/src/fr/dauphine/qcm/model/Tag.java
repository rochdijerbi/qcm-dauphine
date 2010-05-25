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

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6038034486016678022L;

	/**
	 * Identifiant
	 */
	@Id
	private String id;

	/**
	 * Questionnaires
	 */
	@ManyToMany(mappedBy = "tags")
	private List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();

	/**
	 * Nombre de questionnaires pour ce tag
	 */
	@Formula(value = "(SELECT COUNT(*) FROM Questionnaire_Tag qt WHERE qt.tags_id = id)")
	private int questionnairesSize;

	/**
	 * Constructeur Tag
	 */
	public Tag() {
	}

	/**
	 * Constructeur Tag
	 * 
	 * @param id
	 */
	public Tag(String id) {
		setId(id);
	}

	/**
	 * Get id
	 * 
	 * @return Identifiant
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set id
	 * 
	 * @param id
	 *            Identifiant
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Get questionnaires
	 * 
	 * @return Questionnaires
	 */
	public List<Questionnaire> getQuestionnaires() {
		return questionnaires;
	}

	/**
	 * Set questionnaires
	 * 
	 * @param questionnaires
	 *            Questionnaires
	 */
	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	/**
	 * Get id
	 * 
	 * @return Identifiant
	 */
	public int getQuestionnairesSize() {
		return questionnairesSize;
	}

	/**
	 * Set questionnairesSize
	 * 
	 * @param questionnairesSize
	 *            Nombre de questionnaires ayant le tag
	 */
	public void setQuestionnairesSize(int questionnairesSize) {
		this.questionnairesSize = questionnairesSize;
	}

	/**
	 * toString
	 * 
	 * @return Id
	 */
	@Override
	public String toString() {
		return getId();
	}

	/**
	 * Verifie l'egalite de deux objets.
	 * 
	 * @param entity
	 *            L'autre entite
	 * @return Vrai si les entites sont egales, faux sinon
	 */
	@Override
	public boolean equals(Object obj) {
		return obj != null && (obj instanceof Tag) && equals((Tag) obj);
	}

	/**
	 * Verifie l'egalite de deux tags.
	 * 
	 * @param entity
	 *            L'autre entite
	 * @return Vrai si les entites sont egales, faux sinon
	 */
	private boolean equals(Tag tag) {
		return getId() != null && tag.getId() != null
				&& getId().equals(tag.getId());
	}

	/**
	 * Retourne un hascode pour l'Id
	 * 
	 * @return un hascode pour l'Id
	 */
	@Override
	public int hashCode() {
		if (getId() == null) {
			return 0;
		}

		return getId().hashCode();
	}

	/**
	 * Compare deux tags
	 * 
	 * @param o
	 *            L'autre Tag
	 * @return int
	 */
	@Override
	public int compareTo(Tag o) {
		return getId().compareToIgnoreCase(o.getId());
	}

}
