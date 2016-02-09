import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public class Plane extends Entity {
	private static float sizeWing = 20;
	private static float sizePlane = 20;
	private static float thickWing = 5;
	private static float thickPlane = 5;
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
		start.normalize();
		Vector2D dir = new Vector2D(
				(float)Math.cos(this.orientation),
				-(float)Math.sin(this.orientation));
		dir.normalize();
		start.subtract( -sizePlane / 3 * 2, dir);
		Vector2D end = new Vector2D(1, start);
		end.add(sizePlane/3, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	protected void drawWing(GraphicsContext gc) {
		gc.setLineWidth(thickWing);
		Vector2D start = new Vector2D(1, this.pos);
		start.normalize();
		double or = this.orientation + 0.5 * Math.PI;
		Vector2D dir = new Vector2D(
				(float)Math.cos(or),
				-(float)Math.sin(or));
		dir.normalize();
		start.subtract( -sizeWing * 2, dir);
		Vector2D end = new Vector2D(1, start);
		end.add(sizeWing/2, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
}
