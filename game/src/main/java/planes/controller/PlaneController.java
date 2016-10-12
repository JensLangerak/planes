package planes.controller;

import planes.entities.Plane;
import planes.EntityManager;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import planes.entities.Projectile;

/**
 * A controller used to control the planes.
 */
public class PlaneController extends Controller {
	protected Plane plane;
	protected double turnSpeed = 0.03;
	protected EntityManager entityManager;

	/**
	 * Bind the controller to a plane.
	 * @param plane plane that must be controlled.
	 */
	public PlaneController(Plane plane, EntityManager entityManager) {
		this.plane = plane;
		this.entityManager = entityManager;
	}

	/**
	 * Turn the plane to the left.
	 */
	public void goLeft() {
		plane.setVelOr(-turnSpeed);
	}

	/**
	 * Turn the plain to the right.
	 */
	public void goRight() {
		plane.setVelOr(turnSpeed);
	}

	/**
	 * Return the turnspeed.
	 * @return turnspeed
	 */
	public double getTurnSpeed() {
		return turnSpeed;
	}


	/**
	 * Fly straight, stop turning.
	 */
	public void goStraight() {
		plane.setVelOr(0.0);
	}

	public Vector2D getPos() {
		return plane.getPos();
	}

	/**
	 * try to fire. If succeed add the projectile to the entityManage.
	 */
	public void fire() {
		Projectile projectile = plane.fire();
		if (projectile != null) {
			entityManager.addEntity(projectile);
		}
	}

	@Override
	public double getOrientation() {
		return plane.getOrientation();
	}
}
