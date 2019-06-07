package org.itsimulator.germes.app.service.transform.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.infra.util.Checks;
import org.itsimulator.germes.app.infra.util.CommonUtil;
import org.itsimulator.germes.app.infra.util.ReflectionUtil;
import org.itsimulator.germes.app.model.entity.base.AbstractEntity;
import org.itsimulator.germes.app.model.transform.Transformable;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default transformation engine that uses reflection to transform objects
 * 
 * @author Vitaly Dragun
 *
 */
@Named
public class SimpleDTOTransformer implements Transformer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDTOTransformer.class);
	
	private final FieldProvider provider;
	
	@Inject
	public SimpleDTOTransformer(@DBSource FieldProvider provider) {
		this.provider = provider;
	}

	@Override
	public <T extends AbstractEntity, P extends Transformable<T>> P transform(final T entity, final Class<P> dtoClass) {
		checkParams(entity, dtoClass);
		
		return handleTransformation(entity, ReflectionUtil.createInstance(dtoClass));
	}	

	@Override
	public <T extends AbstractEntity, P extends Transformable<T>> void transform(final T entity, final P dto) {
		checkParam(entity, "Source transformation object is not initialized");
		checkParam(dto, "Destination object is not initialized");
		
		handleTransformation(entity, dto);
	}

	@Override
	public <T extends AbstractEntity, P extends Transformable<T>> T untransform(final P dto, final Class<T> entityClass) {
		checkParams(dto, entityClass);
		
		// Create entity instance
		T entity = ReflectionUtil.createInstance(entityClass);
		// Copy all the similar fields
		ReflectionUtil.copyFields(dto, entity, provider.getFieldNames(dto.getClass(), entityClass));
		// Some custom transformation logic
		dto.untransform(entity);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("SimpleDTOTransformer.transform: {} entity object",
					CommonUtil.toString(entity));	
		}
		
		return entity;
	}
	
	private <T extends AbstractEntity, P extends Transformable<T>> P handleTransformation(final T entity, final P dto) {
		// Copy all the similar fields
		ReflectionUtil.copyFields(entity, dto, provider.getFieldNames(entity.getClass(), dto.getClass()));
		// Some custom transformation logic
		dto.transform(entity);
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("SimpleDTOTransformer.transform: {} DTO object",
					CommonUtil.toString(dto));	
		}
		
		return dto;
	}
	
	private void checkParams(final Object param, Class<?> clz) {
		checkParam(param, "Source transformation object is not inirtialized");
		checkParam(clz, "No target class is defined for transformation");
	}
	
	private void checkParam(final Object param, final String errorMessage) {
		Checks.checkParam(param != null, errorMessage);
	}

}
