import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public abstract class Entity {
	protected double maxVelX;
	protected double maxVelOr;

	protected Vector2D pos;
	protected double orientation;

	protected double vel;
	protected double velOr;

	public Entity(double maxVelX, double maxVelOr, Vector2D pos, double orientation) {
		this.maxVelOr = maxVelOr;
		this.maxVelX = maxVelX;
		this.pos = pos;
		this.orientation = orientation;

		this.vel = 0;
		this.velOr = 0;
	}

	public abstract void draw(GraphicsContext gc);
	public abstract void move();
}
