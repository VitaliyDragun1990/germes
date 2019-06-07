package org.itsimulator.germes.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.infra.exception.flow.ValidationException;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.criteria.range.RangeCriteria;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.itsimulator.germes.app.persistence.repository.StationRepository;
import org.itsimulator.germes.app.service.GeographicService;

/**
 * Default and managed (by CDI container) implementation of the {@link GeographicService}
 * 
 * @author Vitaly Dragun
 *
 */
@Named
public class GeographicServiceImpl implements GeographicService {
	
	private final CityRepository cityRepository;
	private final StationRepository stationRepository;
	private final Validator validator;

	@Inject
	public GeographicServiceImpl(@DBSource CityRepository cityRepository, @DBSource StationRepository stationRepository) {
		this.cityRepository = cityRepository;
		this.stationRepository = stationRepository;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public List<City> findCities() {
		return cityRepository.findAll();
	}

	@Override
	public Optional<City> findCityById(int id) {
		return Optional.ofNullable(cityRepository.findById(id));
	}

	@Override
	public List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria) {
		return stationRepository.findAllByCriteria(criteria);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void saveCity(City city) {
		Set constrainViolations = validator.validate(city);
		if (!constrainViolations.isEmpty()) {
			throw new ValidationException("City validation failure", constrainViolations);
		}
		
		cityRepository.save(city);
	}
	
	@Override
	public void saveCities(List<City> cities) {
		cityRepository.saveAll(cities);
	}

	@Override
	public void deleteCities() {
		cityRepository.deleteAll();
	}

	@Override
	public void deleteCity(int cityId) {
		cityRepository.delete(cityId);
	}
}
