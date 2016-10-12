package planes.entities;

import javafx.scene.paint.Color;
import planes.collision_maps.primitive.Circle;
import javafx.scene.canvas.GraphicsContext;
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

	protected int fireTimer = 0;
	protected int maxFireTime = 30;
	protected int hitThick = 30;
	int score = 0;

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
	public void drawEntity(GraphicsContext gc, Vector2D start, double relOrientation) {
		drawPlane(gc, start, relOrientation);
		drawWing(gc, start, relOrientation);

		//TODO test code for hit, should be changed.
		if (hitThick < 30) {
			hitThick++;
		} else {
			this.color = Color.RED;
		}
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
	public void updateCollisionMesh() {
		fireTimer = (fireTimer >= maxFireTime) ? maxFireTime : fireTimer + 1;
		this.collisionMesh = new ArrayList<>();
		this.collisionMesh.add(new Circle(this.pos, 20));
	}

	/**
	 * Fire a projectile. Projectile is spawned just before the plane and is aimed at the current
	 * direction of the plane.
	 * @return a projectile if the plane could fire, else null
	 */
	public Projectile fire() {

		if (fireTimer == maxFireTime) {
			Vector2D firePos = this.pos.add(26, getDir(orientation));
			fireTimer = 0;
			Projectile projectile = new Projectile(firePos, orientation);
			projectile.setOwner(this);
			return projectile;

		} else {
			return null;
		}
	}

	@Override
	public void handleCollision(Entity other) {
		System.out.println("collision");
		this.color = Color.BLUE;
		hitThick = 0;
		score -= 10;
		System.out.println(score);
	}

	/**
	 * Increase the score with points.
	 * @param points points that must be added to the score.
	 */
	public void addScore(int points) {
		score += points;
		System.out.println(score);
	}
}
