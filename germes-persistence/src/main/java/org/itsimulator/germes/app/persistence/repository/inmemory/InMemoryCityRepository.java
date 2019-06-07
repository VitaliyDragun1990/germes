package org.itsimulator.germes.app.persistence.repository.inmemory;

import java.util.ArrayList;
import java.util.List;

import org.itsimulator.germes.app.infra.util.CommonUtil;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.persistence.repository.CityRepository;

public class InMemoryCityRepository implements CityRepository {
	
	/**
	 * Internal list of cities
	 */
	private final List<City> cities;
	
	/**
	 * Auto-increment counter for entity id generation
	 */
	private volatile int counter = 0;
	private volatile int stationCounter = 0;
	
	public InMemoryCityRepository() {
		cities = new ArrayList<>();
	}

	@Override
	public synchronized void save(final City city) {
		if (!cities.contains(city)) {
			city.setId(++counter);
			cities.add(city);
		}
		city.getStations().forEach(station -> {
			if (station.getId() == 0) {
				station.setId(++stationCounter);
			}
		});
	}
	

	@Override
	public synchronized void saveAll(List<City> cities) {
		cities.forEach(this::save);
	}

	@Override
	public City findById(final int cityId) {
		return cities.stream().filter(city -> city.getId() == cityId).findFirst().orElse(null);
	}

	@Override
	public void delete(final int cityId) {
		cities.removeIf(city -> city.getId() == cityId);
	}

	@Override
	public List<City> findAll() {
		return CommonUtil.getSafeList(cities);
	}

	@Override
	public void deleteAll() {
		cities.clear();
		
	}

}
