import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by jens on 2/9/16.
 */
public class Game {
	protected Scene scene;

	protected int framesPerSecond;

	public Game(int framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public void init(Stage primaryStage) {
		scene = new Scene(new Group());
		primaryStage.setTitle("Planes");
		primaryStage.setScene(scene);
	}
}
