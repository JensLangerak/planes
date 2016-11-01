package planes;

import planes.controller.PlaneController;
import planes.controller.Player;
import planes.entities.Entity;
import planes.entities.Plane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage the entities in the game.
 */
public class EntityManager {

	protected Canvas gameField;
	protected List<Entity> entities;
	protected Player player;

	public EntityManager(Canvas gameField) {
		this.gameField = gameField;

		entities = new ArrayList<>();

	}

	/**
	 * Create the player. Probably a temp function for testing purposes.
	 */
	public void createPlayer() {
		Plane plane = new Plane(5, 0, 1, new Vector2D(300, 300), 0);
		plane.setVelX(2);
		PlaneController crl = new PlaneController(plane, this);
		gameField.setFocusTraversable(true);
		Plane plane2 = new Plane(5, 0, 1, new Vector2D(200, 100), 0);
		plane2.setVelX(0);
		player = new Player(crl, gameField);
		entities.add(plane);
		entities.add(plane2);
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
		//move the entities.
		for (Entity entity : entities) {
			entity.performMove();
		}


		//remove dead entities.
		ArrayList<Entity> deadList = new ArrayList<>();
		for (Entity entity : entities) {
			entity.checkCollisions(entities);
			if (entity.isDead()) {
				deadList.add(entity);
			}
		}

		for (Entity entity:deadList) {
			entities.remove(entity);
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
		double orientation = 0;
		for (Entity entity : entities) {
			entity.draw(gameField.getGraphicsContext2D(), center, screenCenter, orientation);
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	//TODO find solution that does not require to return the entities array (and without cloning)
	public List<Entity> getEntities() {
		return entities;
	}

}
