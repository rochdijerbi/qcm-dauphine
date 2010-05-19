package fr.dauphine.qcm.model;

import static org.apache.commons.collections.CollectionUtils.select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.AssertTrue;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.collections.CollectionUtils;

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

	@AssertTrue(message = "you can not take this questionnaire right now")
	public boolean isPeriodCorrect() {
		Date today = Calendar.getInstance().getTime(), start = getQuestionnaire()
				.getStart(), end = getQuestionnaire().getEnd();

		return (start == null || start.before(today))
				&& (end == null || end.after(today));
	}

	@AssertTrue(message = "you have to answer all the questions")
	public boolean isNumberOfAnswersCorrect() {
		int count = CollectionUtils.selectRejected(getAnswers(),
				new BeanPropertyValueEqualsPredicate("id", null)).size();
		
		System.out.println(count + " <=> " + getQuestionnaire().getQuestions().size());
		
		return count == getQuestionnaire().getQuestions().size();
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
