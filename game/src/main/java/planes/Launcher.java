/**
 * Created by jens on 2/9/16.
 */

package planes;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Launcher for the game.
 */
public class Launcher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Game game = new Game(60);
		game.init(primaryStage);
		game.startGameLoop();
		primaryStage.show();
	}
}
