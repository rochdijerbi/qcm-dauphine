package fr.dauphine.qcm.model;

import static java.util.Collections.shuffle;
import static org.apache.commons.collections.CollectionUtils.find;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Question d'un questionnaire.
 */
@Entity
public class Question extends AbstractEntity {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -2496095094253773982L;

	/**
	 * Nombre de reponses par question.
	 */
	private static final int NUMBER_OF_ANSWERS = 4;

	/**
	 * Libelle.
	 */
	@NotBlank
	private String label;

	/**
	 * Questionnaire.
	 */
	@ManyToOne(optional = false)
	private Questionnaire questionnaire;

	/**
	 * Reponses.
	 */
	@Valid
	@Size(min = NUMBER_OF_ANSWERS, max = NUMBER_OF_ANSWERS)
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<Answer>();

	/**
	 * Set label.
	 * 
	 * @param label
	 *            Libelle
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get label.
	 * 
	 * @return Libelle
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Set questionnaire.
	 * 
	 * @param questionnaire
	 *            Questionnaire
	 */
	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	/**
	 * Set questionnaire.
	 * 
	 * @return Questionnaire
	 */
	public Questionnaire getQuestionnaire() {
		return questionnaire;
	}

	/**
	 * Set answers.
	 * 
	 * @param answers
	 *            Reponses
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * Get answers.
	 * 
	 * @return Reponses
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * Ajoute une reponse.
	 * 
	 * @param answer
	 *            Reponse.
	 */
	public void addAnswer(Answer answer) {
		answer.setQuestion(this);
		getAnswers().add(answer);
	}

	/**
	 * Melange les reponses.
	 */
	public void shuffleAnswers() {
		shuffle(getAnswers());
	}

	/**
	 * Retourne la reponse correcte a la question.
	 * 
	 * @return Reponse correcte a la question
	 */
	public Answer getCorrectAnswer() {
		return (Answer) find(getAnswers(),
				new BeanPropertyValueEqualsPredicate("correct", true));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getLabel();
	}

	/**
	 * Cree une nouvelle question contenant des reponses.
	 * 
	 * @return Nouvelle question
	 */
	public static Question createEmpty() {
		Question question = new Question();

		for (int i = 0; i < NUMBER_OF_ANSWERS; i++) {
			Answer answer = new Answer();

			// On coche la premiere reponse comme correcte par defaut
			if (i == 0) {
				answer.setCorrect(true);
			}

			question.addAnswer(answer);
		}

		return question;
	}
}
