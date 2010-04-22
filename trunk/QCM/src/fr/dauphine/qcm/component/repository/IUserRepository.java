package fr.dauphine.qcm.component.repository;

import fr.dauphine.qcm.model.User;

public interface IUserRepository extends IAbstractRepository<User> {

	User loadByLogin(String login);

}
