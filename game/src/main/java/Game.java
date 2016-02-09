import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

/**
 * Created by jens on 2/9/16.
 */
public class Game {
	protected Group root;
	protected Scene scene;
	protected EntityManager entityManager;

	protected int framesPerSecond;

	public Game(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public void init(Stage primaryStage) {
		root = new Group();
		Canvas gameField = new Canvas(600, 600);

		entityManager = new EntityManager(gameField);
		root.getChildren().add(gameField);
		scene = new Scene(root);

		primaryStage.setTitle("Planes");
		primaryStage.setScene(scene);

	}
}
