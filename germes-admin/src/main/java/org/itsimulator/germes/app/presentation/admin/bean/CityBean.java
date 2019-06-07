package org.itsimulator.germes.app.presentation.admin.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.transform.Transformable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * {@link CityBean} is value holder of the city data for admin project
 * 
 * @author Vitaly Dragun
 *
 */
@ManagedBean(name = "currentCity")
@ViewScoped
@ToString
@Getter
@Setter
public class CityBean implements Transformable<City> {
	
	private int id;
	private String name;
	private String district;
	private String region;
	
	public void clear() {
		setName("");
		setDistrict("");
		setRegion("");
		setId(0);
	}
	
	@Override
	public void transform(City p) {
		// No need for additional processing
	}

	@Override
	public City untransform(City city) {
		return city;
	}
}
