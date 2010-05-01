package fr.dauphine.qcm.component.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.dauphine.qcm.component.repository.IUserRepository;
import fr.dauphine.qcm.model.User;

@Repository
public final class UserRepositoryImpl extends AbstractRepositoryImpl<User>
		implements IUserRepository {

	public User loadByLogin(String login) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));

		return (User) criteria.uniqueResult();
	}
}
