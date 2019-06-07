package org.itsimulator.germes.app.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.itsimulator.germes.app.model.entity.person.User;

/**
 * Defines CRUD methods to access {@link User} objects in the persistence storage.
 * @author Vitaly Dragun
 *
 */
public interface UserRepository {
	
	/**
	 * Saves (creates or modifies) specified user instance
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
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);
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
