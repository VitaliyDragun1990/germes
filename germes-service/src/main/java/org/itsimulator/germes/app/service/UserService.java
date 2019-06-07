package org.itsimulator.germes.app.service;

import java.util.List;
import java.util.Optional;

import org.itsimulator.germes.app.model.entity.person.User;

/**
 * Provides API for the user management
 * @author Vitaly Dragun
 *
 */
public interface UserService {
	
	/**
	 * Saves (creates or deletes) specified user instance
	 * @param user
	 */
	void save(User user);
	
	/**
	 * Returns user with specified identifier boxed into Optional
	 * @param userId
	 * @return
	 */
	Optional<User> findById(int userId);
	
	/**
	 * Returns user with specified username boxed into Optional
	 * @param username unique identifier
	 * @return
	 */
	Optional<User> findUserByUserName(String username);
	
	/**
	 * Deletes user with specified identifier
	 * @param userId
	 */
	void delete(int userId);
	
	/**
	 * Returns all the users
	 * @return
	 */
	List<User> findAll();

}
