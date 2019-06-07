package org.itsimulator.germes.app.model.entity.geography;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Value object that stores geographical coordinate of an object
 * 
 * @author Vitaly Dragun
 *
 */
@Embeddable
public class Coordinate {

	private double x;

	private double y;
	
	Coordinate() {
		this(0.0, 0.0);
	}

	public Coordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Column(name = "X")
	public double getX() {
		return x;
	}

	@Column(name = "Y")
	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	

}
