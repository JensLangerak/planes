package learner.neural_network;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by jens on 10/18/16.
 */
public class NeuralNetworkTest {
	NeuralNetwork network;

	@Before
	public void createNetwork() {
		NeuralNetworkBuilder builder = new NeuralNetworkBuilder(2);
		Stack<Float> weights = new Stack();
		weights.push(-0.5f);
		weights.push(-0.4f);
		weights.push(-0.3f);
		weights.push(-0.2f);
		weights.push(-0.1f);
		weights.push(0f);
		weights.push(0.1f);
		weights.push(0.2f);
		weights.push(0.3f);
		weights.push(0.4f);
		weights.push(0.5f);
		weights.push(0.6f);
		weights.push(0.35f);
		weights.push(0.4f);
		weights.push(0.45f);
		weights.push(0.5f);
		weights.push(0.55f);
		weights.push(0.6f);
		weights.push(0.65f);
		weights.push(0.7f);

		builder.addLayer(4, weights);
		network = builder.build(3, weights);


	}

	@Test
	public void testCalculateNetwork() throws Exception {
		float[] res = network.calculateNetwork(new float[]{1f, 0.4f, 0.2f});
		assertEquals(0.96f, res[0], 0.01f);
		assertEquals(0.88f, res[1], 0.01f);
	}
}