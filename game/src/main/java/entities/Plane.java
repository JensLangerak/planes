package entities;

import collision_maps.primitive.Circle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;

/**
 * Class that represents a plane.
 */
public class Plane extends Entity {
	protected double sizeWing = 30;
	protected double sizeTail = 15;
	protected double sizePlane = 30;
	protected double thickWing = 13;
	protected double thickPlane = 10;
	protected Color color = Color.RED;

	/**
	 * Create a new plane.
	 * @param maxVelX Max speed of the plane.
	 * @param minVelX Min speed of the plane.
	 * @param maxVelOr Max turning speed of the plane.
	 * @param pos Start position.
	 * @param orientation Start orientation.
	 */
	public Plane(double maxVelX,
				 double minVelX,
				 double maxVelOr,
				 Vector2D pos,
				 double orientation) {
		super(maxVelX, minVelX, maxVelOr, pos, orientation);
	}

	@Override
	public void draw(GraphicsContext gc,
					 Vector2D center,
					 Vector2D screenCenter,
					 double orientation) {
		gc.setStroke(color);
		Vector2D start = new Vector2D(1, this.pos);
		start = start.subtract(1, center);
		start = this.rotate(start, orientation);
		start = start.add(screenCenter);
		double relOrientation = (this.orientation - orientation) % (2 * Math.PI);
		drawPlane(gc, start, relOrientation);
		drawWing(gc, start, relOrientation);
	}

	/**
	 * Draw the body of the plane.
	 * @param gc place to draw upon.
	 * @param relStart center point of the plane drawing. (same location as the wings)
	 * @param relOrientation direction of the plane drawing.
	 */
	protected void drawPlane(GraphicsContext gc, Vector2D relStart, double relOrientation) {
		gc.setLineWidth(thickPlane);
		Vector2D dir = getDir(relOrientation);
		relStart = relStart.add(sizePlane / 3.0 * 1.0, dir);
		Vector2D end = relStart.subtract(sizePlane, dir);
		gc.strokeLine(relStart.getX(), relStart.getY(), end.getX(), end.getY());
		drawTail(gc, end, relOrientation);
	}

	/**
	 * Draw the tail wing of the plane.
	 * @param gc place to draw upon.
	 * @param end location of the tail of the plane in the drawing.
	 * @param relOrientation direction of the plane drawing.
	 */
	private void drawTail(GraphicsContext gc, Vector2D end, double relOrientation) {
		Vector2D start = new Vector2D(1, end);
		double or = (relOrientation + 0.5 * Math.PI) % (2 * Math.PI);
		Vector2D dir = getDir(or);
		start = start.subtract(sizeTail / 2.0, dir);
		end = new Vector2D(1, start);
		end = end.add(sizeTail, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	/**
	 * Draw the wings.
	 * @param gc place to draw upon.
	 * @param start location of the wings on the drawings.
	 * @param relOrientation direction of the plane drawing.
	 */
	protected void drawWing(GraphicsContext gc, Vector2D start, double relOrientation) {
		gc.setLineWidth(thickWing);
		double or = (relOrientation + 0.5 * Math.PI) % (2 * Math.PI);
		Vector2D dir = getDir((float) or);
		start = start.subtract(sizeWing / 2.0, dir);
		Vector2D end = new Vector2D(1, start);
		end = end.add(sizeWing, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	@Override
	public void move() {
		Vector2D dir = getDir(this.orientation);
		this.pos = this.pos.add(this.vel, dir);

		orientation += velOr;
		orientation = orientation < 0 ? orientation + 2 * Math.PI : orientation % (2 * Math.PI);
	}

	@Override
	public void updateCollisionMesh() {
		this.collisionMesh = new ArrayList<>();
		this.collisionMesh.add(new Circle(this.pos, 25));
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
}
