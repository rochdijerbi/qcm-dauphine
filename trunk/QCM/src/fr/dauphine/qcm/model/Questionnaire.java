package fr.dauphine.qcm.model;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Questionnaire extends AbstractEntity {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -6216254847946147375L;

	/**
	 * Titre
	 */
	@NotBlank
	private String title;

	/**
	 * Description
	 */
	@NotBlank
	private String description;

	/**
	 * Date de début
	 */
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/YYYY")
	private Date start;

	/**
	 * Date de fin
	 */
	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/YYYY")
	private Date end;

	/**
	 * Questions
	 */
	@Valid
	@Size(min = 1)
	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private List<Question> questions = new ArrayList<Question>();

	/**
	 * Résultats
	 */
	@OneToMany(mappedBy = "questionnaire")
	private List<Result> results = new ArrayList<Result>();

	/**
	 * Nombre de fois que le questionnaire a été pris
	 */
	@Formula(value = "(SELECT COUNT(*) FROM Result r WHERE r.questionnaire_id = id)")
	private int resultsSize;

	/**
	 * Tags
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Tag> tags = new TreeSet<Tag>();

	/**
	 * Set title.
	 * 
	 * @param title
	 *            Titre
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get title.
	 * 
	 * @return Titre
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set description.
	 * 
	 * @param description
	 *            Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get description.
	 * 
	 * @return Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set questions.
	 * 
	 * @param questions
	 *            Questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Get questions.
	 * 
	 * @return Questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * Ajoute une question au questionnaire
	 * 
	 * @param question
	 * 
	 */
	public void addQuestion(Question question) {
		question.setQuestionnaire(this);
		getQuestions().add(question);
	}

	/**
	 * Set results.
	 * 
	 * @param results
	 *            Résultats
	 */
	public void setResults(List<Result> results) {
		this.results = results;
	}

	/**
	 * Get results.
	 * 
	 * @return Résultats
	 */
	public List<Result> getResults() {
		return results;
	}

	/**
	 * Mélange les questions
	 */
	public void shuffleQuestions() {
		for (Question q : getQuestions()) {
			q.shuffleAnswers();
		}

		shuffle(getQuestions());
	}

	/**
	 * Set tags.
	 * 
	 * @param tags
	 *            Tags
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * Get tags.
	 * 
	 * @return Tags
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/**
	 * Set resultsSize.
	 * 
	 * @param resultsSize
	 *            Nombre de fois que le questionnaire a été pris
	 */
	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

	/**
	 * Get resultsSize.
	 * 
	 * @return Nombre de fois que le questionnaire a été pris
	 */
	public int getResultsSize() {
		return resultsSize;
	}

	/**
	 * Get start.
	 * 
	 * @return Date de début
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * Set start.
	 * 
	 * @param start
	 *            Date de début
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * Get end.
	 * 
	 * @return Date de fin
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * Set end.
	 * 
	 * @param end
	 *            Date de fin
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

	/**
	 * Valide les dates de début et de fin des formulaires
	 */
	@AssertTrue(message = "la date de début doit être antérieure à celle de fin")
	public boolean isPeriodValid() {
		return getStart() == null || getEnd() == null
				|| getStart().before(getEnd());
	}

	/**
	 * Méthode toString
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return getTitle();
	}

	/**
	 * Initialisation du questionnaire
	 */
	public static Questionnaire createEmpty() {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.addQuestion(Question.createEmpty());
		return questionnaire;
	}
}
