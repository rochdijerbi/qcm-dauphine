package fr.dauphine.qcm.model;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Questionnaire extends AbstractEntity {

	private static final long serialVersionUID = -6216254847946147375L;

	@NotBlank
	private String title;

	@OneToMany(mappedBy = "questionnaire")
	private List<Question> questions = new ArrayList<Question>();
	
	@OneToMany(mappedBy = "questionnaire")
	private List<Result> results = new ArrayList<Result>();

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
	
	public void addResult(Result result) {
		result.setQuestionnaire(this);
		getResults().add(result);
	}
	
	public void shuffleQuestions() {
		for (Question q : getQuestions()) {
			q.shuffleAnswers();
		}
		
		shuffle(getQuestions());
	}
}
