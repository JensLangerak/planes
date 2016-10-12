package planes.controller;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Abstract class that has to be implemented by controllers.
 * A controller must be used to control the entities.
 */
public abstract class Controller {
	/**
	 * Return the position of the entity.
	 * @return a vector that contains the position of the entity.
	 */
	public abstract Vector2D getPos();

	/**
	 * Return the orientation of the entity.
	 * @return a double that represents the orientation.
	 */
	public abstract double getOrientation();
}
