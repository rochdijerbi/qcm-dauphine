package fr.dauphine.qcm.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Answer extends AbstractEntity {

	private static final long serialVersionUID = 1711643553304958326L;

	@NotBlank
	private String label;
	
	private boolean correct;
	
	@ManyToOne(optional = false)
	private Question question;

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Question getQuestion() {
		return question;
	}
}
