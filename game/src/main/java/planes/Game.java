package planes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.util.Duration;

/**
 * Gameobject that manage the game.
 */
//TODO might want to change this to singleton
public class Game {
	protected Group root;
	protected Scene scene;
	protected EntityManager entityManager;
	protected Timeline gameLoop;

	protected int framesPerSecond;
	protected static volatile Game instance = null;

	/**
	 * Create game.

	 */
	protected Game() {

	}

	public static Game getInstance() {
		if (instance == null) {
			synchronized (Game.class) {
				if (instance == null) {
					Game.instance = new Game();
				}
			}
		}
		return Game.instance;
	}
	/**
	 * Create the game, but do not start it.
	 * @param primaryStage Place to draw the game upon.
	 * @param framesPerSecond number of thicks in one second.
	 */
	public void init(Stage primaryStage, int framesPerSecond) {
		root = new Group();
		Canvas gameField = new Canvas(800, 800);
		this.framesPerSecond = framesPerSecond;

		entityManager = new EntityManager(gameField);
		root.getChildren().add(gameField);
		scene = new Scene(root);

		primaryStage.setTitle("Planes");
		primaryStage.setScene(scene);
		createGameLoop();
		entityManager.createPlayer();
	}

	/**
	 * Create a gameloop that loops framesPerSecond times a second.
	 * Update the game and draw the game.
	 */
	protected final void createGameLoop() {
		KeyFrame oneFrame = new KeyFrame(Duration.millis(1000.0 / ((double) framesPerSecond)),
				event -> {
					entityManager.update();

					//TODO do not always redraw, (high speed simulation)
					entityManager.draw();

				});

		// sets the game world's game loop (Timeline)
		gameLoop = new Timeline(framesPerSecond, oneFrame);
		gameLoop.setCycleCount(Animation.INDEFINITE);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Start the game.
	 */
	public void startGameLoop() {
		gameLoop.play();
	}
}
