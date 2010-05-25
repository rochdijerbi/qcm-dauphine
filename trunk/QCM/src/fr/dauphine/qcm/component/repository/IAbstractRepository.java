package fr.dauphine.qcm.component.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Depot generique.
 * 
 * @param <T>
 *            Classe entite
 */
public interface IAbstractRepository<T> {

	/**
	 * Retourne toutes les entites de type <code>T</code>.
	 * 
	 * @return La liste des entites de type <code>T</code>
	 */
	public List<T> loadAll();

	/**
	 * Retourne l'entite correspondant a la cle.
	 * 
	 * @param primaryKey
	 *            La cle
	 * @return L'entite
	 */
	public T load(Serializable primaryKey);

	/**
	 * Sauvegarde l'entité de type <code>T</code>.
	 * 
	 * @return l'entité de type <code>T</code>
	 */
	public T save(T entity);

	/**
	 * Sauvegarde l'entité de type <code>T</code>.
	 */
	public void saveOrUpdate(T entity);

	/**
	 * Sauvegarde l'entité de type <code>T</code>.
	 */
	public void update(T entity);

	/**
	 * Supprime l'entité de type <code>T</code>.
	 */
	public void delete(T entity);

	/**
	 * Déconnecte l'entité de type <code>T</code>.
	 */
	public void evict(T entity);

	/**
	 * Recharge l'entité de type <code>T</code>.
	 */
	public void refresh(T entity);
	
	/**
	 * Merge l'entité de type <code>T</code>.
	 */
	public T merge(T entity);
	
	/**
	 * Enlève les références Hibernate de l'entité de type <code>T</code>.
	 * Posait problème dans la session Spring
	 */
	public T unproxy(T entity);
}
