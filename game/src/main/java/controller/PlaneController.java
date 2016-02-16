package controller;

import entities.Plane;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * A controller used to control the planes.
 */
public class PlaneController extends Controller {
	protected Plane plane;
	protected double turnSpeed = 0.03;

	/**
	 * Bind the controller to a plane.
	 * @param plane plane that must be controlled.
	 */
	public PlaneController(Plane plane) {
		this.plane = plane;
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

	@Override
	public double getOrientation() {
		return plane.getOrientation();
	}
}
