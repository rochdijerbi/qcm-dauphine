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

	public T save(T entity);

	public void saveOrUpdate(T entity);

	public void update(T entity);

	public void delete(T entity);

	public void evict(T entity);

	public void refresh(T entity);
	
	public T merge(T entity);
	
	public T unproxy(T entity);
}
