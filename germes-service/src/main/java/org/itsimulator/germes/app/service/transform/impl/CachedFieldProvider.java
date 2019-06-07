package org.itsimulator.germes.app.service.transform.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.itsimulator.germes.app.infra.cdi.DBSource;
import org.itsimulator.germes.app.infra.util.ReflectionUtil;

/**
 * This class caches field names for each transformation pair
 * 
 * @author Vitaly Dragun
 *
 */
@Named
@DBSource
public class CachedFieldProvider extends FieldProvider {
	
	/**
	 * Mapping between transformation pair(concatenated class names) and field list
	 */
	private final Map<String, List<String>> cache;
	
	public CachedFieldProvider() {
		this.cache = new HashMap<>();
	}

	/**
	 * Returns list of similar field names for source/destination classes
	 */
	@Override
	public List<String> getFieldNames(Class<?> source, Class<?> dest) {
		String key = source.getSimpleName() + dest.getSimpleName();
		return cache.computeIfAbsent(key, k -> ReflectionUtil.findSimilarFields(source, dest));
	}
	
	

}
