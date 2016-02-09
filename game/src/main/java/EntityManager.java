import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
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
		entities.add(new Plane(10, 1, new Vector2D(300, 300), (float) (0.1 * Math.PI)));
		entities.get(0).draw(gameField.getGraphicsContext2D());


	}
}
