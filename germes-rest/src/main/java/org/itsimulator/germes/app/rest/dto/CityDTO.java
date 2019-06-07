package org.itsimulator.germes.app.rest.dto;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.rest.dto.base.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Holds city state for the client-server communication
 * 
 * @author Vitaly Dragun
 *
 */
@ApiModel(description = "City with transport stations to book and purchase tickets")
public class CityDTO extends BaseDTO<City> {

	private String name;

	/**
	 * Name of the district where city is placed
	 */
	private String district;

	/**
	 * Name of the region where the district is located. Region is top-level area in
	 * the country
	 */
	private String region;

	@ApiModelProperty(value = "Name of the city", required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(value = "Name of the city's district. Empty for region center.", required = false)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@ApiModelProperty(value = "Name of the city's region", required = true)
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}
