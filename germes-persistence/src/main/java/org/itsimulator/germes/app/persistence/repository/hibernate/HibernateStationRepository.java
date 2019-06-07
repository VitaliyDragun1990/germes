package org.itsimulator.germes.app.persistence.repository.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.itsimulator.germes.app.persistence.repository.StationRepository;

@Named
@DBSource
public class HibernateStationRepository extends BaseHibernateRepository implements StationRepository {

	@Inject
	public HibernateStationRepository(SessionFactoryBuilder builder) {
		super(builder);
	}

	@Override
	public List<Station> findAllByCriteria(StationCriteria stationCriteria) {
		return query(session -> {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Station> query = criteriaBuilder.createQuery(Station.class);
			Root<Station> root = query.from(Station.class);

			List<Predicate> predicates = new ArrayList<>();
			if (stationCriteria.getTransportType() != null) {
				predicates.add(criteriaBuilder.equal(root.get(Station.FIELD_TRANSPORT_TYPE),
						stationCriteria.getTransportType()));
			}
			if (!StringUtils.isEmpty(stationCriteria.getName())) {
				predicates.add(criteriaBuilder.equal(root.get(Station.FIELD_CITY).get(City.FIELD_NAME),
						stationCriteria.getName()));
			}

			query.select(root).where(predicates.toArray(new Predicate[] {}));

			return session.createQuery(query).getResultList();
		});
	}

}
