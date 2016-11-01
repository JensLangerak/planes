package planes.collision_maps.figure;

import planes.collision_maps.Body;
import planes.collision_maps.primitive.Circle;
import planes.collision_maps.primitive.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;

/**
 * Triangle object for hitboxes.
 * Created by jens on 3/14/16.
 */
public class Triangle implements Body {
	private Vector2D[] points;

	/**
	 * Create a new triangle.
	 * @param a first point.
	 * @param b second point.
	 * @param c third point.
	 */
	public Triangle(Vector2D a, Vector2D b, Vector2D c) {
		points = new Vector2D[]{a, b, c};
	}

	/**
	 * Check if two triangle collide.
	 * @param other an other triangle.
	 * @return True if the two triangles collide.
	 */
	public boolean collide(Triangle other) {
		for (Line line : other.getLines()) {
			if (collide(line)) {
				return true;
			}
		}
		return this.pointInFigure(other.getLines().get(0).getStart())
				|| other.pointInFigure(this.getLines().get(0).getStart());
	}

	@Override
	public boolean collide(Line other) {
		ArrayList<Line> lines = this.getLines();
		for (Line line : lines) {
			if (line.collide(other)) {
				return true;
			}
		}
		return pointInFigure(other.getStart());
	}

	/**
	 * Create a line from p2 to p3, check at which side p1 is.
	 * @param p1 point
	 * @param p2 line start point
	 * @param p3 line end point.
	 * @return a positive or negative distance.
	 */
	private double sign(Vector2D p1, Vector2D p2, Vector2D p3) {
		return (p1.getX() - p3.getX()) * (p2.getY() - p3.getY())
				- (p2.getX() - p3.getX()) * (p1.getY() - p3.getY());
	}

	/**
	 * Check if the point is in the triangle.
	 * @param point point to check.
	 * @return true if the point is in the triangle.
	 */
	protected boolean pointInFigure(Vector2D point) {
		boolean b1, b2, b3;

		b1 = sign(point, points[0], points[1]) < 0.0;
		b2 = sign(point, points[1], points[2]) < 0.0;
		b3 = sign(point, points[2], points[0]) < 0.0;

		return b1 == b2 && b2 == b3;
	}

	@Override
	public boolean collide(Circle other) {
		ArrayList<Line> lines = this.getLines();
		for (Line line : lines) {
			if (line.collide(other)) {
				return true;
			}
		}
		return pointInFigure(other.getCenter());
	}

	/**
	 * @return a array of lines that defines this triangle.
	 */
	public ArrayList<Line> getLines() {
		ArrayList<Line> lines = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			lines.add(new Line(points[i], points[(i + 1) % 3]));
		}
		return lines;
	}

	@Override
	public boolean collide(Body other) {
		if (other instanceof Circle) {
			return collide((Circle) other);
		} else if (other instanceof Triangle) {
			return collide((Triangle) other);
		} else {
			throw new NotImplementedException();
		}
	}
}
