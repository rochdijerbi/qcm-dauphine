package fr.dauphine.qcm.component.service;

import java.util.List;

import fr.dauphine.qcm.exception.FunctionalException;
import fr.dauphine.qcm.model.User;

/**
 * Service des utilisateurs.
 */
public interface IUserService {

	User checkCredentials(User user) throws FunctionalException;

	User createAccount(User user) throws FunctionalException;

	User getById(Long id);

	List<User> getAll();

	User updateAccount(User user);

	/**
	 * Compte le nombre d'utilisateurs.
	 * 
	 * @return Nombre d'utilisateurs
	 */
	Long getNbUsers();
}
