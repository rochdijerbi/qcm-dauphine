package fr.dauphine.qcm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Entite persistente abstraite.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable,
		Identifiable<Long> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -8796045861878061257L;

	/**
	 * Identifiant technique.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Date de modification.
	 */
	@Version
	private Date datemodif;

	/**
	 * Date de creation.
	 */
	private Date datecreate = new Date();

	/**
	 * Set id.
	 * 
	 * @param id
	 *            Identifiant technique
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set datemodif.
	 * 
	 * @param datemodif
	 *            Date de modification
	 */
	public void setDatemodif(Date datemodif) {
		this.datemodif = datemodif;
	}

	/**
	 * Get datemodif.
	 * 
	 * @return Date de modification
	 */
	public Date getDatemodif() {
		return datemodif;
	}

	/**
	 * Get datecreate.
	 * 
	 * @return Date de creation
	 */
	public Date getDatecreate() {
		return datecreate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return obj != null && (obj instanceof AbstractEntity)
				&& equals((AbstractEntity) obj);
	}

	/**
	 * Verifie l'egalite de deux entites persistentes.
	 * 
	 * @param entity
	 *            L'autre entite
	 * @return Vrai si les entites sont egales, faux sinon
	 */
	private boolean equals(AbstractEntity entity) {
		return getId() != null && entity.getId() != null
				&& getId().equals(entity.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (getId() == null) {
			return 0;
		}

		return getId().hashCode();
	}
}
