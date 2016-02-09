import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

/**
 * Created by jens on 2/9/16.
 */
public class Game {
	protected Scene root;
	protected EntityManager entityManager;

	protected int framesPerSecond;

	public Game(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public void init(Stage primaryStage) {
		root = new Scene(new Group());
		primaryStage.setTitle("Planes");
		primaryStage.setScene(root);
		Canvas gameField = new Canvas(root.getWidth(), root.getHeight());
		entityManager = new EntityManager(gameField);
	}
}
