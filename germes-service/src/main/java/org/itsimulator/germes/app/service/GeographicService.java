package org.itsimulator.germes.app.service;

import java.util.List;
import java.util.Optional;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;



public interface GeographicService {

	/**
	 * Returns list of existing cities
	 */
	List<City> findCities();
	
	/**
	 * Searches for the {@link City} with the given id.
	 * 
	 * @param id the unique identifier to look {@link City} with.
	 * @return the {@link Optional} containing {@link City} instance if any,
	 * or empty one otherwise.
	 */
	Optional<City> findCityById(int id);
	
	/**
	 * Returns list of {@link Station} objects which were found with specified criteria.
	 * @param criteria {@link StationCriteria} object which holds filtering information to search for {@link Station}
	 * @param rangeCriteria special pagination criteria
	 * @return list of found {@link Station} objects if any, or empty list otherwise.
	 */
	List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria);

	/**
	 * Saves specified city instance
	 */
	void saveCity(City city);
	
	/**
	 * Saves specified {@link City} instances
	 * @param cities City instances to save
	 */
	void saveCities(List<City> cities);
	
	/**
	 * Removes all the cities
	 */
	void deleteCities();

	/**
	 * Removes {@link City} entity with specified identifier
	 * @param cityId unique identifier
	 */
	void deleteCity(int cityId);
}
