package org.itsimulator.germes.app.rest.dto.base;

import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.transform.Transformable;

/**
 * Base class for all DTO classes
 * 
 * @author Vitaly Dragun
 *
 */
public abstract class BaseDTO<T extends AbstractEntity> implements Transformable<T> {

	/**
	 * Unique entity identifier
	 */
	private int id;

	/**
	 * Should be overridden in the derived classes if the additional transformation
	 * logic domain model -> DTO is needed. Overridden methods should call
	 * super.transform()
	 */
	public void transform(T entity) {
		this.id = entity.getId();
	}

	/**
	 * Should be overridden in the derived classes if the additional transformation
	 * logic DTO -> domain model is needed.
	 */
	public T untransform(T entity) {
		entity.setId(getId());
		return entity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
