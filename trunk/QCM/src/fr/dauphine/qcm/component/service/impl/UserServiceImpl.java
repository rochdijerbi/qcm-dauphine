package fr.dauphine.qcm.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.dauphine.qcm.component.repository.IUserRepository;
import fr.dauphine.qcm.component.service.IUserService;
import fr.dauphine.qcm.model.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userRepository.loadAll();
	}
}
