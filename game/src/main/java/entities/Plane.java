package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public class Plane extends Entity {
	private static int sizeWing = 30;
	private static int sizeTail = 15;
	private static int sizePlane = 30;
	private static int thickWing = 13;
	private static int thickPlane = 10;
	private Color color = Color.RED;

	public Plane(double maxVelX, double minVelX, double maxVelOr, Vector2D pos, double orientation) {
		super(maxVelX, minVelX, maxVelOr, pos, orientation);
		setVelX(1);
	}

	@Override
	public void draw(GraphicsContext gc, Vector2D center, Vector2D screenCenter, double orientation) {
		gc.setStroke(color);
		Vector2D start = new Vector2D(1, this.pos);
		start = start.subtract(1, center);
		start = this.rotate(start, orientation);
		start = start.add(screenCenter);
		double relOrientation = (this.orientation - orientation) % (2 * Math.PI);
		drawPlane(gc, start, relOrientation);
		drawWing(gc, start, relOrientation);
	}

	protected void drawPlane(GraphicsContext gc, Vector2D relStart, double relOrientation) {
		gc.setLineWidth(thickPlane);
		Vector2D dir = getDir(relOrientation);
		relStart = relStart.add( sizePlane / 3 * 1, dir);
		Vector2D end = relStart.subtract(sizePlane, dir);
		gc.strokeLine(relStart.getX(), relStart.getY(), end.getX(), end.getY());
		drawTail(gc, end, relOrientation);
	}

	private void drawTail(GraphicsContext gc, Vector2D end, double relOrientation) {
		Vector2D start = new Vector2D(1, end);
		double or = (relOrientation + 0.5 * Math.PI) % (2 * Math.PI);;
		Vector2D dir = getDir(or);
		start = start.subtract(sizeTail / 2, dir);
		end = new Vector2D(1, start);
		end = end.add(sizeTail, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	protected void drawWing(GraphicsContext gc, Vector2D start, double relOrientation) {
		gc.setLineWidth(thickWing);
		double or = (relOrientation + 0.5 * Math.PI) % (2 * Math.PI);
		Vector2D dir = getDir((float) or);
		start = start.subtract(sizeWing / 2, dir);
		Vector2D end = new Vector2D(1, start);
		end = end.add(sizeWing, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	public void move() {
		Vector2D dir = getDir(this.orientation);
		this.pos = this.pos.add(this.vel, dir);

		orientation += velOr % (2 * Math.PI);
	}

	private Vector2D getDir(double orientation) {
		return new Vector2D(
				-(float)Math.sin(orientation),
				(float)Math.cos(orientation)).normalize();
	}
}
