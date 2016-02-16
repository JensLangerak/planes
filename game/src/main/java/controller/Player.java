package controller;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Bind keyboard inputs to a controller.
 */
public class Player extends Agent {
	protected PlaneController plane;
	protected boolean left;
	protected boolean right;

	public Player(PlaneController planeController, Node scene) {
		this.plane = planeController;


		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.A) {
				left = true;
				event.consume();

			} else if (event.getCode() == KeyCode.D) {
				right = true;
				event.consume();
			}
			controlPlane();
		});

		scene.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			if (event.getCode() == KeyCode.A) {
				left = false;
				event.consume();

			} else if (event.getCode() == KeyCode.D) {
				right = false;
				event.consume();
			}
			controlPlane();

		});
	}

	/**
	 * Check which way the plane should turn to.
	 */
	protected void controlPlane() {
		if (left && !right) {
			plane.goLeft();
		} else if (!left && right) {
			plane.goRight();
		} else {
			plane.goStraight();
		}
	}

	/**
	 * Get player position.
	 * @return vector the represents the position.
	 */
	public Vector2D getPos() {
		return plane.getPos();
	}

	/**
	 * Get player rotation.
	 * @return double the represents the orientation.
	 */
	public double getOrientation() {
		return plane.getOrientation();
	}
}
