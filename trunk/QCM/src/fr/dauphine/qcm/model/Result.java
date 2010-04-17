package fr.dauphine.qcm.model;

import static org.apache.commons.collections.CollectionUtils.select;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;

@Entity
public class Result extends AbstractEntity implements Comparable<Result> {

	private static final long serialVersionUID = -6462534272160040377L;

	@ManyToOne(optional = false)
	private Questionnaire questionnaire;

	@ManyToOne(optional = false)
	private User user;

	@ManyToMany
	private List<Answer> answers = new ArrayList<Answer>();

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	@SuppressWarnings("unchecked")
	public List<Answer> getCorrectAnswers() {
		return (List<Answer>) select(getAnswers(),
				new BeanPropertyValueEqualsPredicate("correct", true));
	}

	@Override
	public int compareTo(Result o) {
		return getCorrectAnswers().size() - o.getCorrectAnswers().size();
	}
}
