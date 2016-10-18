package learner.neural_network;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 10/18/16.
 */
public class NodeTest {
	Node testNode;

	@Before
	public void setup() {
		testNode = new Node();
	}

	@Test
	public void testAddValue() throws Exception {
		assertEquals(0f, testNode.getValue(), 0.1f);
		testNode.addValue(0.82f);
		assertEquals(0.82f, testNode.getValue(), 0.1f);
		testNode.addValue(-0.4f);
		assertEquals(0.42f, testNode.getValue(), 0.1f);
	}

	@Test
	public void testGetValueMax() throws Exception {
		testNode.addValue(1.82f);
		assertEquals(1.00f, testNode.getValue(), 0.1f);

	}

	@Test
	public void testGetValueMin() throws Exception {
		testNode.addValue(-0.82f);
		assertEquals(0f, testNode.getValue(), 0.1f);

	}

	@Test
	public void testAddValueOverLimitMax() throws Exception {
		testNode.addValue(1.82f);
		assertEquals(1f, testNode.getValue(), 0.1f);
		testNode.addValue(-0.4f);
		assertEquals(1f, testNode.getValue(), 0.1f);
		testNode.addValue(-0.43f);
		assertEquals(0.99f, testNode.getValue(), 0.1f);

	}

	@Test
	public void testAddValueOverLimitMin() throws Exception {
		testNode.addValue(-1.82f);
		assertEquals(0f, testNode.getValue(), 0.1f);
		testNode.addValue(+0.4f);
		assertEquals(0f, testNode.getValue(), 0.1f);
		testNode.addValue(+0.43f);
		assertEquals(0.01f, testNode.getValue(), 0.1f);

	}

	@Test
	public void testClearValue() throws Exception {
		testNode.addValue(+1.82f);
		testNode.clearValue();
		assertEquals(0f, testNode.getValue(), 0.1f);

	}

	@Test
	public void testUpdateDest() throws Exception {
		Node nodeDest1 = new Node();
		Node nodeDest2 = new Node();
		testNode.setEdgeWeight(nodeDest1, 1.2f);
		testNode.setEdgeWeight(nodeDest2, -0.3f);
		testNode.addValue(0.5f);
		nodeDest2.addValue(1.f);

		testNode.updateDest();

		assertEquals(0.6f, nodeDest1.getValue(), 0.01f);
		assertEquals(0.85f, nodeDest2.getValue(), 0.01f);
	}
}