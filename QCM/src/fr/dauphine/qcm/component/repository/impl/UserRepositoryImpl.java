package fr.dauphine.qcm.component.repository.impl;

import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IUserRepository;
import fr.dauphine.qcm.model.User;

@Repository
public final class UserRepositoryImpl extends AbstractRepositoryImpl<User>
		implements IUserRepository {

}
