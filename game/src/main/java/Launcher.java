/**
 * Created by jens on 2/9/16.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Game game = new Game(60);
		game.init(primaryStage);

		primaryStage.show();
	}
}
