package fr.dauphine.qcm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class User extends AbstractEntity {

	private static final long serialVersionUID = 2282025136214205189L;
	private static final int IMG_MAX_SIZE = 1000000;

	@NotBlank
	@Column(unique = true)
	private String login;

	@NotBlank
	private String password;

	@Column(nullable = false)
	private boolean admin;

	@Lob
	@Column(length = IMG_MAX_SIZE)
	private byte[] photo;

	@OneToMany(mappedBy = "user")
	private List<Result> results = new ArrayList<Result>();

	@Formula(value = "(SELECT COUNT(*) FROM Result r WHERE r.user_id = id)")
	private int resultsSize;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
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

	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

	public int getResultsSize() {
		return resultsSize;
	}

	public void incrementResultsSize() {
		resultsSize++;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public String toString() {
		return getLogin();
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
