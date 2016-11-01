package planes.entities;

import planes.collision_maps.primitive.Circle;
import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;

/**
 * Created by jens on 10/11/16.
 */
public class Projectile extends Entity {
	protected int timeToLive;
	protected Plane owner = null;

	/**
	 * Create a new projectile, with speed 10.
	 * @param pos the position where the projectile must be spawned.
	 * @param orientation the orientation of the projectile.
	 */
	public Projectile(Vector2D pos, double orientation) {
		this(pos, orientation, 10);
	}

	/**
	 * Create a new projectile.
	 * @param pos the position where the projectile must be spawned.
	 * @param orientation the orientation of the projectile.
	 * @param speed the speed of the projectile.
	 */
	public Projectile(Vector2D pos, double orientation, float speed) {
		super(speed, speed, 0, pos,  orientation);
		timeToLive = 100;
		dead = false;
	}

	/**
	 * Set the agent that fired this projectile, used for score.
	 * @param owner the agent that fires this projectile.
	 */
	public void setOwner(Plane owner) {
		this.owner = owner;
	}

	@Override
	public void drawEntity(GraphicsContext gc, Vector2D start, double relOrientation) {
		gc.setStroke(color);
		gc.setLineWidth(10);
		Vector2D dir = getDir(relOrientation);
		Vector2D end = start.add(5, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	@Override
	public void move() {
		super.move();

		//after some time the projectile must disappear.
		timeToLive--;
		if (timeToLive <= 0) {
			dead = true;
		}
	}

	@Override
	public void updateCollisionMesh() {
		this.collisionMesh = new ArrayList<>();
		this.collisionMesh.add(new Circle(this.pos, 5));
	}

	@Override
	public void handleCollision(Entity collided) {
		if (owner != null && collided instanceof Plane) {
			owner.addScore(10);
		}
		this.dead = true;
	}

	public Plane getOwner() {
		return owner;
	}
}
