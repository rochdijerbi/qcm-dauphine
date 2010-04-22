package fr.dauphine.qcm.model;

import static java.util.Collections.shuffle;
import static org.apache.commons.collections.CollectionUtils.find;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Question extends AbstractEntity {

	private static final long serialVersionUID = -2496095094253773982L;

	@NotBlank
	private String label;

	@ManyToOne(optional = false)
	private Questionnaire questionnaire;

	@OneToMany(mappedBy = "question")
	private List<Answer> answers = new ArrayList<Answer>();

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void addAnswer(Answer answer) {
		answer.setQuestion(this);
		getAnswers().add(answer);
	}

	public void shuffleAnswers() {
		shuffle(getAnswers());
	}

	public Answer getCorrectAnswer() {
		return (Answer) find(getAnswers(),
				new BeanPropertyValueEqualsPredicate("correct", true));
	}
	
	@Override
	public String toString() {
		return getLabel();
	}
}
