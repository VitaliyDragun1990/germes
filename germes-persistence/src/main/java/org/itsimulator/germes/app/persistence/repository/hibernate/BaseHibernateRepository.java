package org.itsimulator.germes.app.persistence.repository.hibernate;

import java.util.function.Consumer;
import java.util.function.Function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.itsimulator.germes.app.infra.exception.PersistenceException;
import org.itsimulator.germes.app.persistence.hibernate.SessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all Hibernate-based repository implementations
 * 
 * @author Vitaly Dragun
 *
 */
public class BaseHibernateRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseHibernateRepository.class);
	private final SessionFactory sessionFactory;

	public BaseHibernateRepository(SessionFactoryBuilder builder) {
		sessionFactory = builder.getSessionFactory();
	}

	/**
	 * Returns currently configured size of the batch insert/update operation
	 * 
	 * @return
	 */
	protected int getBatchSize() {
		return sessionFactory.getSessionFactoryOptions().getJdbcBatchSize();
	}

	/**
	 * Execute any session-provided command inside database transaction
	 * 
	 * @param action
	 */
	protected void execute(Consumer<Session> action) {
		Transaction tx = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			action.accept(session);
			tx.commit();
		} catch (Exception ex) {
			handleError(tx, ex);
			throw new PersistenceException(ex);
		}
	}

	private void handleError(Transaction tx, Exception ex) {
		LOGGER.error(ex.getMessage(), ex);
		if (tx != null && tx.isActive()) {
			try {
				tx.rollback();
			} catch (Exception e) {
				ex.addSuppressed(e);
			}
		}
	}

	/**
	 * Executes any session-provided query and returns query result
	 * 
	 * @param func
	 * @return result of the query
	 */
	protected <R> R query(Function<Session, R> func) {
		try (Session session = sessionFactory.openSession()) {
			return func.apply(session);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			throw new PersistenceException(ex);
		}
	}
}
