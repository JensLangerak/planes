package learner.neural_network;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jens on 10/13/16.
 */
public class NeuralNetwork {
	Node[] startNodes;
	Node[] endNodes;
	List<Node[]> hiddenLayer;

	public NeuralNetwork(int numberStartNodes, int numberEndNodes) {
		startNodes = new Node[numberStartNodes];
		endNodes = new Node[numberEndNodes];
	}

	public Node[] getStartNodes() {
		return startNodes;
	}

	public Node[] getEndNodes() {
		return endNodes;
	}

	public void clearNetwork() {

	}

	public void reCalculateNetwork() {

	}
}

