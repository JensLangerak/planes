package learner.neural_network;

/**
 * Created by jens on 10/13/16.
 */
public class NeuralNetworkBuilder {
	protected NeuralNetwork network;

	public NeuralNetworkBuilder(int numberStartNodes, int numberEndNodes) {
		network = new NeuralNetwork(numberStartNodes, numberEndNodes);
	}

	public NeuralNetworkBuilder addLayer(int nodes) {

		return this;
	}


}
