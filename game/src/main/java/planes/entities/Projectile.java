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


	public Projectile(Vector2D pos, double orientation) {
		this(pos, orientation, 10);
	}

	public Projectile(Vector2D pos, double orientation, float speed) {
		super(speed, speed, 0, pos,  orientation);
		timeToLive = 100;
		dead = false;
	}

	@Override
	public void drawEntity(GraphicsContext gc, Vector2D start, double relOrientation) {		gc.setStroke(color);
		gc.setLineWidth(10);
		Vector2D dir = getDir(relOrientation);
		Vector2D end = start.add(5, dir);
		gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
	}

	@Override
	public void move() {
		super.move();
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
	public void handleCollision() {
		this.dead = true;
	}
}
