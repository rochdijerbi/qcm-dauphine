package fr.dauphine.qcm.model;

import java.io.Serializable;

/**
 * Interface qui caracterise un bean possedant une cle primaire de type
 * <code>T</code>.
 * 
 * @param <T>
 *            Type de la cle primaire
 */
public interface Identifiable<T extends Serializable> {

	/**
	 * Get id.
	 * 
	 * @return Cle primaire
	 */
	T getId();

	/**
	 * Set id.
	 * 
	 * @param id
	 *            Cle primaire
	 */
	void setId(T id);
}
