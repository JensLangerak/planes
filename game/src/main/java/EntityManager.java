import controller.Agent;
import controller.PlaneController;
import controller.Player;
import entities.Entity;
import entities.Plane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.scene.paint.Color;
import java.util.HashMap;

/**
 * Manage the entities in the game.
 */
public class EntityManager {

	protected Canvas gameField;
	protected HashMap<Entity, Agent> entities;
	protected Player player;

	public EntityManager(Canvas gameField) {
		this.gameField = gameField;

		entities = new HashMap<>();

	}

	/**
	 * Create the player. Probably temp function.
	 */
	public void createPlayer() {
		Plane plane = new Plane(5, 0, 1, new Vector2D(300, 300), 0);
		plane.setVelX(2);
		PlaneController crl = new PlaneController(plane);
		gameField.setFocusTraversable(true);
		Plane plane2 = new Plane(5, 0, 1, new Vector2D(200, 100), 0);
		plane2.setVelX(0);
		player = new Player(crl, gameField);
		entities.put(plane, player);
		entities.put(plane2, null);
	}

	/**
	 * Clear the screen.
	 */
	public void clear() {
		gameField.getGraphicsContext2D()
				.clearRect(0, 0, gameField.getWidth(), gameField.getHeight());
	}

	/**
	 * Update all entities, but do not draw them.
	 */
	public void update() {
		for (Entity entity : entities.keySet()) {
			entity.move();
		}
	}

	/**
	 * Draw all the entities.
	 */
	public void draw() {
		this.clear();
		GraphicsContext gc = gameField.getGraphicsContext2D();
		gc.setFill(Color.rgb(220, 220, 220));
		gc.fillRect(0, 0, gameField.getWidth(), gameField.getHeight());
		Vector2D center = player.getPos();
		Vector2D screenCenter =
				new Vector2D(0.5 * gameField.getWidth(), 0.5 * gameField.getHeight());
		//double orientation = player.getOrientation() +  Math.PI;
		for (Entity entity : entities.keySet()) {
			entity.draw(gameField.getGraphicsContext2D(), center, screenCenter, 0);
		}
	}
}
