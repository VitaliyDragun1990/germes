package org.itsimulator.germes.app.service.transform;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.transform.Transformable;

/**
 * Represents transformation engine for converting business entities
 * into DTO objects
 * 
 * @author Vitaly Dragun
 *
 */
public interface Transformer {
	
	/**
	 * Converts specified entity into DTO object
	 * 
	 * @param entity entity instance to convert from
	 * @param dtoClass Class of the specified DTO object to convert to
	 * @return instance of the specified DTO
	 */
	<T extends AbstractEntity, P extends Transformable<T>> P transform(T entity, Class<P> dtoClass);
	
	/**
	 * Converts specified entity into existing DTO object
	 * @param entity specified entity to convert from
	 * @param dto specified DTO to convert to
	 */
	<T extends AbstractEntity, P extends Transformable<T>> void transform(T entity, P dto);
	
	/**
	 * Converts specified DTO object into business entity
	 * 
	 * @param dto DTO instance to convert from
	 * @param entityClass Class of the specified entity to convert to
	 * @return instance of the specified entity
	 */
	<T extends AbstractEntity, P extends Transformable<T>> T untransform(P dto, Class<T> entityClass);

}
