package org.itsimulator.germes.app.persistence.repository;

import java.util.List;

import org.itsimulator.germes.app.model.entity.geography.City;

/**
 * Defines CRUD methods to access {@link City} objects in the persistent storage
 * 
 * @author Vitaly Dragun
 *
 */
public interface CityRepository {
	
	/**
	 * Saves(creates or modifies) specified city instance
	 */
	void save(City city);
	
	/**
	 * Saves specified city instances
	 * @param cities {@link City} instances to save
	 */
	void saveAll(List<City> cities);
	
	/**
	 * Returns city with specified identifier. If no city exists with such identifier
	 * then null is returned
	 * @param cityId unique identifier
	 * @return {@link City} instance with such identifier or null otherwise
	 */
	City findById(int cityId);
	
	/**
	 * Deletes city with specified identifier
	 */
	void delete(int cityId);
	
	/**
	 * Returns all cities
	 * @return
	 */
	List<City> findAll();
	
	/**
	 * Delete all the cities
	 */
	void deleteAll();

}
