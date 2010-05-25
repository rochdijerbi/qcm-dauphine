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
import org.hibernate.annotations.Formula;

@Entity
public class Result extends AbstractEntity implements Comparable<Result> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -6462534272160040377L;

	/**
	 * Questionnaire
	 */
	@ManyToOne(optional = false)
	private Questionnaire questionnaire;

	/**
	 * Utilisateur
	 */
	@ManyToOne(optional = false)
	private User user;

	/**
	 * Réponses
	 */
	@ManyToMany
	private List<Answer> answers = new ArrayList<Answer>();

	/**
	 * Nombre de réponses correctes
	 */
	@Formula("(SELECT COUNT(*) FROM Result_Answer ra, Answer a WHERE ra.result_id = id AND a.id = ra.answers_id AND a.correct = 1)")
	private Integer nbCorrectAnswers;

	/**
	 * Get nbCorrectAnswers.
	 * 
	 * @return Nombre de réponses correctes
	 */
	public Integer getNbCorrectAnswers() {
		return nbCorrectAnswers;
	}

	/**
	 * Set nbCorrectAnswers
	 * 
	 * @param nbCorrectAnswers
	 *            Nombre de réponses correctes
	 */
	public void setNbCorrectAnswers(Integer nbCorrectAnswers) {
		this.nbCorrectAnswers = nbCorrectAnswers;
	}

	/**
	 * Set questionnaire
	 * 
	 * @param questionnaire
	 *            Questionnaire
	 */
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	/**
	 * Get questionnaire.
	 * 
	 * @return Questionnaire
	 */
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	/**
	 * Set user
	 * 
	 * @param user
	 *            Utilisateur
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Get user.
	 * 
	 * @return Utilisateur
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Set answers
	 * 
	 * @param answers
	 *            Réponses
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * Get answers.
	 * 
	 * @return Réponses
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * Valide l'intervalle des dates.
	 */
	@AssertTrue(message = "you can not take this questionnaire right now")
	public boolean isPeriodCorrect() {
		Date today = Calendar.getInstance().getTime(), start = getQuestionnaire()
				.getStart(), end = getQuestionnaire().getEnd();

		return (start == null || start.before(today))
				&& (end == null || end.after(today));
	}

	/**
	 * Valide le nombre de réponses correctes.
	 */
	@AssertTrue(message = "you have to answer all the questions")
	public boolean isNumberOfAnswersCorrect() {
		int count = CollectionUtils.selectRejected(getAnswers(),
				new BeanPropertyValueEqualsPredicate("id", null)).size();

		System.out.println(count + " <=> "
				+ getQuestionnaire().getQuestions().size());

		return count == getQuestionnaire().getQuestions().size();
	}

	/**
	 * Get answers
	 * 
	 * @return answers Réponses
	 */
	@SuppressWarnings("unchecked")
	public List<Answer> getCorrectAnswers() {
		return (List<Answer>) select(getAnswers(),
				new BeanPropertyValueEqualsPredicate("correct", true));
	}

	/**
	 * Compare deux résultats
	 * 
	 * @param o
	 * @return int
	 */
	@Override
	public int compareTo(Result o) {
		return o.getNbCorrectAnswers() - getNbCorrectAnswers();
	}
}
