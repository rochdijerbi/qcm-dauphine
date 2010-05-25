package fr.dauphine.qcm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class User extends AbstractEntity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2282025136214205189L;

	/**
	 * Taille maximum pour l'image
	 */
	private static final int IMG_MAX_SIZE = 1000000;

	/**
	 * Login
	 */
	@NotBlank
	@Column(unique = true)
	private String login;

	/**
	 * Mot de passe
	 */
	@NotBlank
	private String password;

	/**
	 * Administrateur
	 */
	@Column(nullable = false)
	private boolean admin;

	/**
	 * Photo
	 */
	@Lob
	@Column(length = IMG_MAX_SIZE)
	private byte[] photo;

	/**
	 * Résultats
	 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Result> results = new ArrayList<Result>();

	/**
	 * Nombre de questionnaires pris par l'utilisateur
	 */
	@Formula(value = "(SELECT COUNT(*) FROM Result r WHERE r.user_id = id)")
	private int resultsSize;

	/**
	 * Util upload photo
	 */
	// Not saved into the database
	@Transient
	private CommonsMultipartFile uploadPhoto;

	/**
	 * Set login
	 * 
	 * @param login
	 *            Login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Get login
	 * 
	 * @return Login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Set password
	 * 
	 * @param password
	 *            Mot de Passe
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get password
	 * 
	 * @return Mot de Passe
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set results
	 * 
	 * @param results
	 *            Résultats
	 */
	public void setResults(List<Result> results) {
		this.results = results;
	}

	/**
	 * Get results
	 * 
	 * @return Résultats
	 */
	public List<Result> getResults() {
		return results;
	}

	/**
	 * Set resultsSize
	 * 
	 * @param resultsSize
	 *            Nombre de questionnaires pris par l'utilisateur
	 */
	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

	/**
	 * Get resultsSize
	 * 
	 * @return Nombre de questionnaires pris par l'utilisateur
	 */
	public int getResultsSize() {
		return resultsSize;
	}

	/**
	 * Incremente le nombre de résultats
	 */
	public void incrementResultsSize() {
		resultsSize++;
	}

	/**
	 * Set admin
	 * 
	 * @param admin
	 *            Administrateur
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
	 * Get admin
	 * 
	 * @return Administrateur
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * Méthode toString
	 * 
	 * @return Login
	 */
	@Override
	public String toString() {
		return getLogin();
	}

	/**
	 * Get photo
	 * 
	 * @return Photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * Set photo
	 * 
	 * @param photo
	 *            Photo
	 */
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	/**
	 * Get uploadPhoto
	 * 
	 * @return Util photo
	 */
	public CommonsMultipartFile getUploadPhoto() {
		return uploadPhoto;
	}

	/**
	 * Set uploadPhoto
	 * 
	 * @param uploadPhoto
	 *            Util photo
	 */
	public void setUploadPhoto(CommonsMultipartFile uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}
}
