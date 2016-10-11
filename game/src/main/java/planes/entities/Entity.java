package planes.entities;

import planes.collision_maps.Body;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;
import java.util.Set;

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

	protected Color color = Color.RED;

	protected double vel;
	protected double velOr;

	protected boolean dead;

	protected ArrayList<Body> collisionMesh;

	/**
	 * Create a new entity.
	 * @param maxVelX max forward speed
	 * @param minVelX min forward speed
	 * @param maxVelOr max turning speed
	 * @param pos start position
	 * @param orientation start orientation
	 */
	public Entity(double maxVelX,
				  double minVelX,
				  double maxVelOr,
				  Vector2D pos,
				  double orientation) {
		this.maxVelOr = maxVelOr;
		this.maxVelX = maxVelX;
		this.pos = pos;
		this.orientation = orientation;
		this.minVelX = minVelX;

		this.vel = minVelX <= 0 ? 0 : minVelX;
		this.velOr = 0;

		dead = false;
	}

	/**
	 * Draw a picture of the entity on the GraphicsContext.
	 * @param gc graphicContext to draw.
	 * @param center coordinates of the centre of the screen.
	 * @param screenCenterOffset pixel center of the screen.
	 *            So center is the position in the game
	 *                              that must be at the position screenCenterOffset.
	 * @param orientation orientation of the screen.
	 */
	public void draw(GraphicsContext gc,
							  Vector2D center,
							  Vector2D screenCenterOffset,
							  double orientation) {
		gc.setStroke(color);
		Vector2D start = new Vector2D(1, this.pos);
		start = start.subtract(1, center);
		start = this.rotate(start, orientation);
		start = start.add(screenCenterOffset);
		double relOrientation = (this.orientation - orientation) % (2 * Math.PI);
		drawEntity(gc, start, relOrientation);
	}
	public abstract void drawEntity(GraphicsContext gc, Vector2D start, double relOrientation);

	public void performMove() {
		move();
		updateCollisionMesh();
	}
	/**
	 * Move the entity one tick.
	 */
	public void move() {
		Vector2D dir = getDir(this.orientation);
		this.pos = this.pos.add(this.vel, dir);

		orientation += velOr;
		orientation = orientation < 0 ? orientation + 2 * Math.PI : orientation % (2 * Math.PI);

	}

	/**
	 * Set velocity and check if the velocity is in range minVel and maxVel.
	 * If new velocity > maxVel than new vel is maxVel
	 * If new velocity < minVel than new vel is minVel
	 * @param vel the new velocity
	 * @return the set velocity.
	 */
	public double setVelX(double vel) {
		this.vel = vel > maxVelX ? maxVelX : vel < minVelX ? minVelX : vel;
		return this.vel;
	}

	/**
	 * Getter for the velocity.
	 * @return the velocity.
	 */
	public double getVelX() {
		return this.vel;
	}
	/**
	 * Set turn speed and check if the turn speed is in range -max turn speed and max turn speed.
	 * If new turn speed > maxVelOr than new turn speed is maxVelOr
	 * If new turn speed < -maxVelOr than new turn speed is -maxVelOr
	 * @param velOr the new turn speed
	 * @return the set turn speed.
	 */
	public double setVelOr(double velOr) {
		this.velOr = velOr > maxVelOr ? maxVelOr : velOr < -maxVelOr ? -maxVelOr : velOr;
		return this.velOr;
	}

	/**
	 * Getter for the turn speed.
	 * @return the turning speed.
	 */
	public double getVelOr() {
		return this.velOr;
	}

	/**
	 * Set the position of this entity.
	 * @param pos a vector the represents the position.
	 */
	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	/**
	 * Return the position of this entity.
	 * @return a vector the represents the position.
	 */
	public Vector2D getPos() {
		return pos;
	}

	/**
	 * Return the orientation of this entity.
	 * @return a double the represents the orientation.
	 */
	public double getOrientation() {
		return orientation;
	}

	/**
	 * Set the orientation of this entity.
	 * @param orientation a double the represents the orientation.
	 */
	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}

	/**
	 * Rotate a point around the center with theta rad.
	 * @param point point to rotate.
	 * @param theta rads to rotate.
	 * @return the rotated point.
	 */
	protected Vector2D rotate(Vector2D point, double theta) {
		Vector2D temp = new Vector2D(point.getX(), -point.getY());
		double x = temp.getX() * Math.cos(theta) - temp.getY() * Math.sin(theta);
		double y = temp.getX() * Math.sin(theta) + temp.getY() * Math.cos(theta);
		return new Vector2D(x, -y);
	}

	public ArrayList<Body> getCollisionMesh() {
		return collisionMesh;
	}

	public void setCollisionMesh(ArrayList<Body> collisionMesh) {
		this.collisionMesh = collisionMesh;
	}

	public abstract void updateCollisionMesh();
	
	public boolean collide(Entity other) {
		for (Body body : collisionMesh) {
			for (Body otherBody : other.getCollisionMesh()) {
				if (body.collide(otherBody)) {
					return true;
				}
			}
		}
		return false;
	}

	public void checkCollisions(Set<Entity> entities) {
		for (Entity other: entities) {
			if (other == this) {
				continue;
			} else {
				if (collide(other)) {
					handleCollision();
				}
			}

		}
	}

	/**
	 * Create vector based on the orientation.
	 * @param orientation the orientation of the plane.
	 * @return vector that represents the orientation.
	 */
	protected Vector2D getDir(double orientation) {
		return new Vector2D(
				-(float) Math.sin(orientation),
				(float) Math.cos(orientation)).normalize();
	}

	public boolean isDead() {
		return dead;
	}

	//TEMP for testing
	public void handleCollision() {

	}



}
