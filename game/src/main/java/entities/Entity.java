package entities;

import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jcp.xml.dsig.internal.MacOutputStream;

/**
 * Class that represents a moveable entity in a 2d plane.
 * Created by jens on 2/9/16.
 */
public abstract class Entity {
	protected double maxVelX;
	protected double maxVelOr;
	protected double minVelX;

	protected Vector2D pos;
	protected double orientation;

	protected double vel;
	protected double velOr;

	/**
	 * Create a new entity.
	 * @param maxVelX max forward speed
	 * @param minVelX min forward speed
	 * @param maxVelOr max turning speed
	 * @param pos start position
	 * @param orientation start orientation
	 */
	public Entity(double maxVelX, double minVelX, double maxVelOr, Vector2D pos, double orientation) {
		this.maxVelOr = maxVelOr;
		this.maxVelX = maxVelX;
		this.pos = pos;
		this.orientation = orientation;
		this.minVelX = minVelX;

		this.vel = (minVelX <= 0) ? 0 : minVelX;
		this.velOr = 0;
	}

	/**
	 * Draw a picture of the entity on the GraphicsContext.
	 * @param gc graphicContext to draw.
	 * @param upperLeft coordinates of the upper left point of the plane.
	 * @param orientation orientation of the screen.
	 */
	public abstract void draw(GraphicsContext gc, Vector2D center, Vector2D screenCenter, double orientation);

	/**
	 * Move the entity one tick.
	 */
	public abstract void move();

	/**
	 * Set velocity and check if the velocity is in range minVel and maxVel.
	 * If new velocity > maxVel than new vel is maxVel
	 * If new velocity < minVel than new vel is minVel
	 * @param vel the new velocity
	 * @return the set velocity.
	 */
	public double setVelX(double vel) {
		this.vel = (vel > maxVelX) ? maxVelX : (vel < minVelX) ? minVelX : vel;
		return this.vel;
	}

	/**
	 * Set turn speed and check if the turn speed is in range -max turn speed and max turn speed
	 * If new turn speed > maxVelOr than new turn speed is maxVelOr
	 * If new turn speed < -maxVelOr than new turn speed is -maxVelOr
	 * @param velOr the new turn speed
	 * @return the set turn speed.
	 */
	public double setVelOr(double velOr) {
		this.velOr = (velOr > maxVelOr) ? maxVelOr : (velOr < -maxVelOr) ? -maxVelOr : velOr;
		return this.velOr;
	}

	public Vector2D getPos() {
		return pos;
	}

	public double getOrientation() {
		return orientation;
	}

	public Vector2D rotate(Vector2D point, double theta) {
		Vector2D temp = new Vector2D(point.getX(), -point.getY());
		double x = temp.getX() * Math.cos(theta) - temp.getY() * Math.sin(theta);
		double y = temp.getX() * Math.sin(theta) + temp.getY() * Math.cos(theta);
		return new Vector2D(x, -y);
	}
}
