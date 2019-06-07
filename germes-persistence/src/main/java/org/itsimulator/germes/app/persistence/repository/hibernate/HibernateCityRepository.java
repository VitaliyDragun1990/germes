package org.itsimulator.germes.app.persistence.repository.hibernate;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@DBSource
public class HibernateCityRepository extends BaseHibernateRepository implements CityRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateCityRepository.class);

	@Inject
	public HibernateCityRepository(SessionFactoryBuilder builder) {
		super(builder);
	}

	@Override
	public void save(City city) {
		execute(session -> session.saveOrUpdate(city));
	}

	@Override
	public void saveAll(List<City> cities) {
		execute(session -> {
			for (int i = 0; i < cities.size(); i++) {
				session.persist(cities.get(i));
				if (i % getBatchSize() == 0 || i == cities.size() - 1) {
					session.flush();
					session.clear();
				}
			}
		});
	}

	@Override
	public City findById(int cityId) {
		return query(session -> session.get(City.class, cityId));
	}

	@Override
	public void delete(int cityId) {
		execute(session -> {
			City city = session.get(City.class, cityId);
			if (city != null) {
				session.delete(city);
			}
		});
	}

	@Override
	public List<City> findAll() {
		return query(session -> session.createNamedQuery(City.QUERY_FIND_ALL, City.class).getResultList());
	}

	@Override
	public void deleteAll() {
		execute(session -> {
			// Delete all the stations first
			session.getNamedQuery(Station.QUERY_DELETE_ALL).executeUpdate();
			// Delete all the cities
			int deletedCount = session.getNamedQuery(City.QUERY_DELETE_ALL).executeUpdate();
			LOGGER.debug("Deleted {} cities", deletedCount);
		});
	}
}
