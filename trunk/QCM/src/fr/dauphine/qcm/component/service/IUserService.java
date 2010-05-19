package fr.dauphine.qcm.component.service;

import java.util.List;

import fr.dauphine.qcm.exception.FunctionalException;
import fr.dauphine.qcm.model.User;

public interface IUserService {

	User checkCredentials(User user) throws FunctionalException;

	User createAccount(User user) throws FunctionalException;

	User getById(Long id);

	List<User> getall();

	User updateAccount(User user);
}
