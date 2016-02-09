import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public class Plane extends Entity {
	private static float sizeWing = 30;
	private static float sizeTail = 15;
	private static float sizePlane = 30;
	private static float thickWing = 13;
	private static float thickPlane = 10;
	private Color color = Color.RED;

	public Plane(float maxVelX, float maxVelOr, Vector2D pos, float orientation) {
		super(maxVelX, maxVelOr, pos, orientation);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(color);
		drawPlane(gc);
		drawWing(gc);
	}

	protected void drawPlane(GraphicsContext gc) {
		gc.setLineWidth(thickPlane);
		Vector2D start = new Vector2D(1, this.pos);
		Vector2D dir = new Vector2D(
				(float)Math.cos(this.orientation),
				-(float)Math.sin(this.orientation));
		dir = dir.normalize();
		start = start.subtract( sizePlane / 3 * 1, dir);
		Vector2D end = start.add(sizePlane, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
		drawTail(gc, end);
	}

	private void drawTail(GraphicsContext gc, Vector2D end) {
		Vector2D start = new Vector2D(1, end);
		double or = this.orientation + 0.5 * Math.PI;
		Vector2D dir = new Vector2D(
				(float)Math.cos(or),
				-(float)Math.sin(or));
		dir = dir.normalize();
		start = start.subtract(sizeTail / 2, dir);
		end = new Vector2D(1, start);
		end = end.add(sizeTail, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());


	}

	protected void drawWing(GraphicsContext gc) {
		gc.setLineWidth(thickWing);
		Vector2D start = new Vector2D(1, this.pos);
		double or = this.orientation + 0.5 * Math.PI;
		Vector2D dir = new Vector2D(
				(float)Math.cos(or),
				-(float)Math.sin(or));
		dir = dir.normalize();
		start = start.subtract(sizeWing / 2, dir);
		Vector2D end = new Vector2D(1, start);
		end = end.add(sizeWing, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
}
