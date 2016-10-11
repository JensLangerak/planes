package planes.collision_maps;

import planes.collision_maps.primitive.Circle;
import planes.collision_maps.primitive.Line;

/**
 * Interface for the primitive figures.
 *
 */
public interface Intersect {
	/**
	 * Check if this intersect collides with a line.
	 * @param other line that might intersect with this object.
	 * @return true if line intersect with this object.
	 */
	boolean collide(Line other);

	/**
	 * Check if this intersect collides with a circle.
	 * @param other circle that might intersect with this object.
	 * @return true if circle intersect with this object.
	 */
	boolean collide(Circle other);
}
