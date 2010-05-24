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

	private static final long serialVersionUID = -6216254847946147375L;

	@NotBlank
	private String title;
	
	@NotBlank
	private String description;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/YYYY")
	private Date start;

	@DateTimeFormat(iso = ISO.DATE, pattern = "dd/MM/YYYY")
	private Date end;

	@Valid
	@Size(min = 1)
	@OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
	private List<Question> questions = new ArrayList<Question>();

	@OneToMany(mappedBy = "questionnaire")
	private List<Result> results = new ArrayList<Result>();

	@Formula(value = "(SELECT COUNT(*) FROM Result r WHERE r.questionnaire_id = id)")
	private int resultsSize;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Tag> tags = new TreeSet<Tag>();

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void addQuestion(Question question) {
		question.setQuestionnaire(this);
		getQuestions().add(question);
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public List<Result> getResults() {
		return results;
	}

	public void shuffleQuestions() {
		for (Question q : getQuestions()) {
			q.shuffleAnswers();
		}

		shuffle(getQuestions());
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

	public int getResultsSize() {
		return resultsSize;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@AssertTrue
	public boolean isPeriodValid() {
		return getStart() == null || getEnd() == null
				|| getStart().before(getEnd());
	}

	@Override
	public String toString() {
		return getTitle();
	}

	public static Questionnaire createEmpty() {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.addQuestion(Question.createEmpty());
		return questionnaire;
	}
}
