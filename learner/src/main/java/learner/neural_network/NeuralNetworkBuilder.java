package learner.neural_network;

import java.util.*;

/**
 * Created by jens on 10/13/16.
 */
public class NeuralNetworkBuilder {
	protected Node[] startNodes;
	protected Node[] endNodes;
	protected List<Node[]> hiddenLayers;
	protected Node[] lastAddedLayer;

	/**
	 * Create a builder ans specify the number of end nodes.
	 * @param numberEndNodes number of end nodes.
	 */
	public NeuralNetworkBuilder(int numberEndNodes) {
		endNodes = new Node[numberEndNodes];
		for (int i = 0; i < numberEndNodes; i++) {
			endNodes[i] = new Node();
		}
		lastAddedLayer = endNodes;
		hiddenLayers = new LinkedList<>();
	}

	/**
	 * Add a layer. The layer is inserted before the last added layer.
	 * So the neuralnetwork is build from back to front.
	 * @param nodes the number of nodes in this layer.
	 * @param weights stack that should be used for the weights.
	 * @return a NeuralNetworkBuilder
	 */
	public NeuralNetworkBuilder addLayer(int nodes, Stack<Float> weights) {
		Node[] hiddenLayer = new Node[nodes];
		for (int i = 0; i < hiddenLayer.length; i++) {
				hiddenLayer[i] = new Node();
				for (Node dest : lastAddedLayer) {
					hiddenLayer[i].setEdgeWeight(dest, weights.pop());
				}
		}
		lastAddedLayer = hiddenLayer;
		hiddenLayers.add(0, hiddenLayer);
		return this;
	}

	/**
	 * Build the neural network.
	 * @param numberStartNodes specify the number of start nodes.
	 * @param weights edges for the start node to the first hidden layer.
	 * @return a NeuralNetwork.
	 */
	public NeuralNetwork build(int numberStartNodes, Stack<Float> weights) {
		startNodes = new Node[numberStartNodes];
		for (int i = 0; i < numberStartNodes; i++) {
			startNodes[i] = new Node();
			for (Node dest : lastAddedLayer) {
				startNodes[i].setEdgeWeight(dest, weights.pop());
			}
		}
		return new NeuralNetwork(startNodes, endNodes, hiddenLayers);
	}


}
