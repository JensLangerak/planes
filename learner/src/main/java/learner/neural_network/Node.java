package learner.neural_network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jens on 10/13/16.
 */
public class Node {
	protected float value;
	protected Map<Node, Float> edges;

	protected Node() {
		edges = new HashMap<>();
	}

	/**
	 * Increase the value of this node.
	 * @param value
	 */
	protected void addValue(float value) {
		this.value += value;
	}

	/**
	 * Return the node value, is a value between 1 and 0.
	 * @return the node value.
	 */
	protected float getValue() {
		return value > 0 ? value < 1 ? value : 1 : 0;
	}

	/**
	 * Add an out going edge to this node.
	 * @param dest destination node
	 * @param weight weight of the edge
	 */
	public void setEdgeWeight(Node dest, float weight) {
		edges.put(dest, weight);
	}

	/**
	 * Update the value of the nodes that are connected to this node.
	 */
	public void updateDest() {
		for (Map.Entry<Node, Float> entry : edges.entrySet()) {
			entry.getKey().addValue(this.getValue() * entry.getValue());
		}
	}

	/**
	 * Set the value to 0.
	 */
	public void clearValue() {
		this.value = 0;
	}



}

