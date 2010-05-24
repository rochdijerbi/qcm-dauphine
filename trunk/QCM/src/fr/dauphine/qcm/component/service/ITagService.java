package fr.dauphine.qcm.component.service;

import java.util.Collection;

import fr.dauphine.qcm.model.Tag;

/**
 * Service des tags.
 */
public interface ITagService {

	/**
	 * Retourne la liste des tags.
	 * 
	 * @return Liste des tags
	 */
	Collection<Tag> getAll();
}
