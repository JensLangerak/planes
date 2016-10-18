package learner.neural_network;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Pack200;

/**
 * Created by jens on 10/13/16.
 */
public class NeuralNetwork {
	protected Node[] startNodes;
	protected Node[] endNodes;
	protected List<Node[]> hiddenLayers;

	public NeuralNetwork(Node[] startNodes, Node[] endNodes, List<Node[]> hiddenLayers) {
		this.startNodes = startNodes;
		this.endNodes = endNodes;
		this.hiddenLayers = hiddenLayers;
	}

	/**
	 * Set all the value in the network to 0.
	 */
	public void clearNetwork() {
		for (Node node : startNodes) {
			node.clearValue();
		}

		for (Node node : endNodes) {
			node.clearValue();
		}

		for (Node[] hiddenLayer : hiddenLayers) {
			for (Node node : hiddenLayer) {
				node.clearValue();
			}
		}
	}

	/**
	 * Set the values of the start nodes.
	 * @param startValues array with the start values.
	 */
	private void setStartNodes(float[] startValues) {
		if (startNodes.length > startValues.length) {
			throw new IllegalArgumentException("number of startValues is not enough");
		}
		for (int i = 0; i < startNodes.length; i++) {
			startNodes[i].addValue(startValues[i]);
		}
	}

	/**
	 * Calculate the values of the node in the network.
	 */
	private void updateNetwork() {
		for (Node node:startNodes) {
			node.updateDest();
		}

		for (Node[] hiddenLayer : hiddenLayers) {
			for (Node node:hiddenLayer) {
				node.updateDest();
			}
		}
	}

	/**
	 * Calculate the end values of the network for the given start values.
	 * @param startValues the values for the start nodes.
	 * @return the values of the end nodes.
	 */
	public float[] calculateNetwork(float[] startValues) {
		clearNetwork();
		setStartNodes(startValues);
		updateNetwork();

		float[] endValues = new float[endNodes.length];
		for (int i = 0; i < endNodes.length; i++) {
			endValues[i] = endNodes[i].getValue();
		}

		return endValues;
	}
}

