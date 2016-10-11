package planes.collision_maps.primitive;

import planes.collision_maps.Body;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/16/16.
 */
public class Circle implements Body {

	protected Vector2D center;
	protected double size;

	/**
	 * Create a new circle.
	 * @param center coordinates of the center.
	 * @param size radius of the circle.
	 */
	public Circle(Vector2D center, double size) {
		this.center = center;
		this.size = size;
	}

	/**
	 * Set the center.
	 * @param center new center of the circle.
	 */
	public void setCenter(Vector2D center) {
		this.center = center;
	}

	/**
	 * Get the center of the circle.
	 * @return the center of the circle.
	 */
	public Vector2D getCenter() {
		return center;
	}

	/**
	 * Get the radius of the circle.
	 * @return the radius.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Set the radius of the circle.
	 * @param size the radius of the circle. Must be larger than 0.
	 */
	public void setSize(double size) {
		if (size < 0) {
			throw new IllegalArgumentException("Negative size");
		}
		this.size = size;
	}

	@Override
	public boolean collide(Body other) {
		return other.collide(this);
	}

	@Override
	public boolean collide(Line other) {
		return other.collide(this);
	}

	@Override
	public boolean collide(Circle other) {
		double distance = this.center.distance(other.getCenter());
		double r = this.size + other.size;
		return distance <= r;
	}
}
