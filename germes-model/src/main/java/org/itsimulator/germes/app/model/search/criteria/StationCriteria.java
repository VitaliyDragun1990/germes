package org.itsimulator.germes.app.model.search.criteria;

import java.util.Objects;

import org.itsimulator.germes.app.model.entity.transport.TransportType;

/**
 * Filtering criteria to search {@link Station} entities.
 * 
 * @author Vitaly Dragun
 *
 */
public class StationCriteria {

	/**
	 * City name
	 */
	private String name;

	private TransportType transportType;

	/**
	 * Station's address: street, zipCode, building number
	 */
	private String address;

	/**
	 * Returns filtering criteria to search for stations that contain specified name
	 * parameter
	 */
	public static StationCriteria byName(String name) {
		return new StationCriteria(name);
	}

	public StationCriteria() {

	}

	private StationCriteria(final String name) {
		this.name = Objects.requireNonNull(name);
	}

	public StationCriteria(final TransportType transportType) {
		this.transportType = Objects.requireNonNull(transportType);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
