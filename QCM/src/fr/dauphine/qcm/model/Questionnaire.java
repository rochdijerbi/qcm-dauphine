package fr.dauphine.qcm.model;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Questionnaire extends AbstractEntity {

	private static final long serialVersionUID = -6216254847946147375L;

	@NotBlank
	private String title;

	private Date start;

	private Date end;

	@OneToMany(mappedBy = "questionnaire")
	private List<Question> questions = new ArrayList<Question>();

	@OneToMany(mappedBy = "questionnaire")
	private List<Result> results = new ArrayList<Result>();

	@Formula(value = "(SELECT COUNT(*) FROM Result r WHERE r.questionnaire_id = id)")
	private int resultsSize;

	@ManyToMany
	private List<Tag> tags = new ArrayList<Tag>();

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
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

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Tag> getTags() {
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
}
