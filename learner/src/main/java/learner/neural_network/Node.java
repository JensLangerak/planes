package learner.neural_network;

/**
 * Created by jens on 10/13/16.
 */
public class Node {
	protected float value;

	//TODO
	protected Node() {

	}

	protected void addValue(float value) {
		this.value += value;
	}

	/**
	 * Return the node value, is a value between 1 and 0.
	 * @return the node value.
	 */
	protected float getValue() {
		return (value > 0) ? (value < 1) ? value : 1 : 0;
	}


}
