import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

/**
 * Created by jens on 2/9/16.
 */
public class EntityManager {

	protected Canvas gameField;
	protected ArrayList<Entity> entities;

	public EntityManager(Canvas gameField) {
		this.gameField = gameField;
	}
}
