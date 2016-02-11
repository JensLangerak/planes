package controller;

import entities.Plane;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public class PlaneController extends Controller {
	Plane plane;


	public PlaneController(Plane plane) {
		this.plane = plane;
	}

	public void goLeft() {
		plane.setVelOr(-0.03);
	}

	public void goRight() {
		plane.setVelOr(0.03);
	}


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
