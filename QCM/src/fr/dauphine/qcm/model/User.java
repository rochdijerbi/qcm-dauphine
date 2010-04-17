package fr.dauphine.qcm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class User extends AbstractEntity {

	private static final long serialVersionUID = 2282025136214205189L;

	private String email;
	
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<Result> results = new ArrayList<Result>();

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public List<Result> getResults() {
		return results;
	}
	
	public void addResult(Result result) {
		result.setUser(this);
		getResults().add(result);
	}
}
