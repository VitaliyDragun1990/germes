package org.itsimulator.germes.app.persistence.repository;

import java.util.List;

import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;

/**
 * Defines CRUD methods to access {@link Station} objects in the persistent storage
 * 
 * @author Vitaly Dragun
 *
 */
public interface StationRepository {
	
	/**
	 * Returns all the stations that match specific criteria
	 * 
	 * @param stationCriteria search criteria to find specific {@link Station} objects
	 * @return list of all the matching stations, or empty one otherwise
	 */
	List<Station> findAllByCriteria(StationCriteria stationCriteria);

}
