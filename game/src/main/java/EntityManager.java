import javafx.scene.canvas.Canvas;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.ArrayList;

/**
 * Created by jens on 2/9/16.
 */
public class EntityManager {

	protected Canvas gameField;
	protected ArrayList<Entity> entities;

	public EntityManager(Canvas gameField) {
		this.gameField = gameField;

		entities = new ArrayList<>();
		entities.add(new Plane(10, 1, new Vector2D(300, 300), (float) (0 * Math.PI)));
		entities.get(0).draw(gameField.getGraphicsContext2D());
	}

	public void clear() {
		gameField.getGraphicsContext2D().clearRect(0, 0, gameField.getWidth(), gameField.getHeight());
	}

	public void update() {
		for (Entity entity : entities) {
			entity.move();
		}
	}
	public void draw() {
		this.clear();
		for (Entity entity : entities) {
			entity.draw(gameField.getGraphicsContext2D());
		}
	}
}
