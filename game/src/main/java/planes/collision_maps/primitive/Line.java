package planes.collision_maps.primitive;

import planes.collision_maps.Intersect;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/16/16.
 */
public class Line implements Intersect {
	protected Vector2D start;
	protected Vector2D end;

	protected Vector2D left;
	protected Vector2D right;

	/**
	 * Create a new line. Start en End must not be equal.
	 * @param start Start point of the line.
	 * @param end End point of the line.
	 */
	public Line(Vector2D start, Vector2D end) {
		checkDifferentPoints(start, end);
		this.start = start;
		this.end = end;
		setLeftRight();
	}

	/**
	 * Set the start point.
	 * @param start a new start point. Unequal to end.
	 */
	public void setStart(Vector2D start) {
		checkDifferentPoints(start, this.end);
		this.start = start;
		setLeftRight();
	}

	/**
	 * Set the end point.
	 * @param end a new end point. Unequal to start.
	 */
	public void setEnd(Vector2D end) {
		checkDifferentPoints(this.start, end);
		this.end = end;
		setLeftRight();
	}

	/**
	 * Check what the left and what the right point is.
	 */
	private void setLeftRight() {
		if (start.getX() < end.getX()) {
			left = start;
			right = end;
		} else if (end.getX() < start.getX() || end.getY() < start.getY()) {
			left = end;
			right = start;
		} else {
			left = start;
			right = end;
		}
	}

	/**
	 * Check if two points are different are different.
	 * @param start first point.
	 * @param end second point.
	 */
	protected void checkDifferentPoints(Vector2D start, Vector2D end) {
		double dx = Math.abs(start.getX() - end.getX());
		double dy = Math.abs(start.getY() - end.getY());
		if (dx + dy < 0.1) {
			throw new IllegalArgumentException("Points are the same.");
		}
	}

	@Override
	public boolean collide(Line other) {
		boolean oneSideLine1 = (this.closestPointTo(other.left).getX() < other.left.getX())
				^ (this.closestPointTo(other.right).getX() < other.right.getX());

		boolean oneSideLine2 = (other.closestPointTo(this.left).getX() < this.left.getX())
				^ (other.closestPointTo(this.right).getX() < this.right.getX());
		return oneSideLine1 && oneSideLine2;
	}

	@Override
	public boolean collide(Circle other) {
		double distance = this.realDistance(other.getCenter());
		return distance <= other.getSize();
	}

	/**
	 * Check if a line can be drawn that is orthogonal to the line and goes through the point.
	 * @param point point to check.
	 * @return true if point is orthogonal to the line.
	 */
	protected boolean orthogonalToLine(Vector2D point) {
		Vector2D projection = closestPointTo(point);
		if (projection.getX() < left.getX() || projection.getX() > right.getX()) {
			return false;
		}
		return true;
	}

	/**
	 * Calculate the distance from the point to the line. Always returns a positive number.
	 * @param point point to which the distance must be calculated.
	 * @return the distance.
	 */
	public double realDistance(Vector2D point) {
		Vector2D projection = closestPointTo(point);

		if (projection.getX() < left.getX()) {
			return left.distance(point);
		} else if (projection.getX() > right.getX()) {
			return right.distance(point);
		}
		return projection.distance(point);
	}

	/**
	 * Calculate the distance from a point to a infinite version of the line.
	 * So the line does not start/stop in end or start.
	 * @param point a point.
	 * @return the distance.
	 */
	protected double lineDistance(Vector2D point) {
		Vector2D projection = closestPointTo(point);
		return projection.distance(point);

	}

	/**
	 * Get the closest point on the infinite version of the line to the point.
	 * So this point lays on a line that passes through start end end.
	 * @param point point that should be projected on a line.
	 * @return projection of point on the infinite version of the line.
	 */
	protected Vector2D closestPointTo(Vector2D point) {
		/* ax+by+c=0 */
		double b = right.getX() - left.getX();
		double a = left.getY() - right.getY();
		double c = left.getX() * right.getY() - right.getX() * left.getY();


		double resX = (b * (b * point.getX() - a * point.getY()) - a * c) / (a * a + b * b);
		double resY = (a * ((-b) * point.getX() + a * point.getY()) - b * c) / (a * a + b * b);
		return new Vector2D(resX, resY);
	}

	/**
	 * Return the start point.
	 * @return the start point.
	 */
	public Vector2D getStart() {
		return start;
	}

	/**
	 * Return the end point.
	 * @return the end point.
	 */
	public Vector2D getEnd() {
		return end;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Line) {
			Line o = (Line) other;
			return o.left.equals(this.left) && o.right.equals(this.right);
		}
		return false;
	}

}
