package org.itsimulator.germes.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Validation;
import javax.validation.Validator;

import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.infra.exception.flow.ValidationException;
import org.itsimulator.germes.app.model.entity.person.User;
import org.itsimulator.germes.app.persistence.repository.UserRepository;
import org.itsimulator.germes.app.service.UserService;

/**
 * Default and managed (by CDI container) implementation of the
 * {@link UserService}
 * 
 * @author Vitaly Dragun
 *
 */
@Named
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final Validator validator;

	@Inject
	public UserServiceImpl(@DBSource UserRepository userRepository) {
		this.userRepository = userRepository;
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void save(User user) {
		Set constrainViolations = validator.validate(user);
		if (!constrainViolations.isEmpty()) {
			throw new ValidationException("User validation failure", constrainViolations);
		}
		userRepository.save(user);
	}

	@Override
	public Optional<User> findById(int userId) {
		return userRepository.findById(userId);
	}

	@Override
	public Optional<User> findUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void delete(int userId) {
		userRepository.delete(userId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
