import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Created by jens on 2/9/16.
 */
public abstract class Entity {
	protected float maxVelX;
	protected float maxVelOr;

	protected Vector2D pos;
	protected float orientation;

	protected Vector2D vel;
	protected float velOr;

	public Entity(float maxVelX, float maxVelOr, Vector2D pos, float orientation) {
		this.maxVelOr = maxVelOr;
		this.maxVelX = maxVelX;
		this.pos = pos;
		this.orientation = orientation;

		this.vel = new Vector2D(0,0);
	}

	public abstract void draw(GraphicsContext gc);

}
