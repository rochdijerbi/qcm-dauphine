package fr.dauphine.qcm.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Reponse a une question.
 */
@Entity
public class Answer extends AbstractEntity {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1711643553304958326L;

	/**
	 * Libelle.
	 */
	@NotBlank
	private String label;
	
	/**
	 * Reponse correcte.
	 */
	private boolean correct;
	
	// TODO: VŽrifier si on s'en sert encore
	@Transient
	private boolean checked;
	
	/**
	 * Question.
	 */
	@ManyToOne(optional = false)
	private Question question;

	/**
	 * Set label.
	 * @param label Libelle
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get label.
	 * @return Libelle
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set correct.
	 * @param correct Reponse correcte
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	/**
	 * Get correct.
	 * @return Reponse correcte
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * Set question.
	 * @param question Question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * Get question.
	 * @return Question
	 */
	public Question getQuestion() {
		return question;
	}	

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getLabel();
	}
}
