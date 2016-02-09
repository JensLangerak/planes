import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by jens on 2/9/16.
 */
public class Game {
	protected Group root;
	protected Scene scene;
	protected EntityManager entityManager;
	protected Timeline gameLoop;

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
		createGameLoop();

	}

	protected final void createGameLoop() {
		KeyFrame oneFrame = new KeyFrame(Duration.millis(1000/framesPerSecond),
				new EventHandler() {

					@Override
					public void handle(Event event) {
						entityManager.update();
						entityManager.draw();

					}
				}); // oneFrame

		// sets the game world's game loop (Timeline)
		gameLoop = new Timeline(framesPerSecond, oneFrame);
		gameLoop.setCycleCount(Animation.INDEFINITE);
	}

	public void startGameLoop() {
		gameLoop.play();
	}
}
