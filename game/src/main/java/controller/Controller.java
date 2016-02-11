package controller;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public abstract class Controller {
	public abstract Vector2D getPos();

	public abstract double getOrientation();
}
