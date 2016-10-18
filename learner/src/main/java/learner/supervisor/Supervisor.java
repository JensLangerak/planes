package learner.supervisor;

import learner.neural_network.NeuralNetwork;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by jens on 10/18/16.
 */
public class Supervisor {
	protected Stack<Float>[] best;
	protected float bestFitness;
	protected Stack<Float>[] currentGeneration;
	protected int generationSize;
	protected int[] networkSize;
	protected Random numberGen;
	protected float minEdgeValue;
	protected float maxEdgeValue;
	protected float mutateChange;
	protected Map<Stack<Float>, Integer> fitnessRate;

	/**
	 * Create a supervisor for the evolution process. This class handles the creation
	 * of the generations.
	 * @param generationSize number of networks in one generation.
	 * @param networkSize Array with the number of nodes per layer.
	 */
	public Supervisor(int generationSize, int[] networkSize, float minEdgeValue, float maxEdgeValue, float mutateChange) {
		this.generationSize = generationSize;
		this.networkSize = networkSize;
		if (networkSize.length < 2) {
			throw new IllegalArgumentException("network must have at least a start and end layer");
		}
		numberGen = new Random(System.currentTimeMillis());
		this.maxEdgeValue = maxEdgeValue;
		this.minEdgeValue = minEdgeValue;
		this.mutateChange = mutateChange;
		fitnessRate = new HashMap<>();
		bestFitness = Integer.MIN_VALUE;
		generateFirstGeneration();

	}

	/**
	 * Generate a random first generation.
	 */
	protected void generateFirstGeneration() {
		Random numberGen = new Random(System.currentTimeMillis());
		int size = calculateNumberOfEdges(networkSize);
		this.currentGeneration = new Stack[generationSize];
		for (int i = 0; i < generationSize; i++) {
			this.currentGeneration[i] = generateRandomStack(size);
		}
	}

	public void nextGeneration() {
		List sortedList = new LinkedList(fitnessRate.entrySet());

		// sort high to low
		Collections.sort(sortedList, (o1, o2) -> ((Comparable) ((Map.Entry) o2).getValue())
				.compareTo(((Map.Entry) o1).getValue()));
		Stack<Float>[] sortedCurrentGeneration = (Stack<Float>[]) sortedList.toArray();
		int maxRandomValue = generationSize / 2 * (generationSize + 1);

		Stack<Float>[] nextGen = new Stack[generationSize];
		for (int i = 0; i < generationSize; i++) {
			int parent1 = calculateParentIndex(numberGen.nextInt(maxRandomValue), maxRandomValue);
			int parent2 = calculateParentIndex(numberGen.nextInt(maxRandomValue), maxRandomValue);
			nextGen[i] = breed(sortedCurrentGeneration[parent1], sortedCurrentGeneration[parent2]);
		}

		this.currentGeneration = nextGen;

	}

	private int calculateParentIndex(int randomValue, int maxRandomValue) {
		int index = 0;
		while (maxRandomValue > 0) {
			maxRandomValue = maxRandomValue - generationSize + index;
			index++;
		}
		return index;
	}

	/**
	 * Generate a random stack.
	 * @param size the size of the stack.
	 * @return a random stack.
	 */
	protected Stack<Float> generateRandomStack(int size) {
		Stack<Float> res = new Stack<>();
		for (int i = 0; i < size; i++) {
			res.push(getRandom(minEdgeValue, maxEdgeValue));
		}
		return res;
	}

	/**
	 * Get a random number between
	 * @param min the minimum value of the random number.
	 * @param max the maximum number of the value.
	 *
	 * @return a random number between min and max;
	 */
	protected float getRandom(float min, float max) {
		return (max - min) * numberGen.nextFloat() - min;
	}

	/**
	 * Calculate the number of edges in the network, this is the stack size.
	 * @param networkSize an array representation of the network.
	 * @return the number of edges in the network.
	 */
	protected int calculateNumberOfEdges(int[] networkSize) {
		int currentLayer = networkSize[0];
		int size = 0;
		for (int i = 1; i < networkSize.length; i++) {
			size = size + (currentLayer * networkSize[i]);
			currentLayer = networkSize[i];
		}
		return size;
	}

	/**
	 * Create a child. For each edge in the network it has a mutateChange change that the value is
	 * random. If the value is not random it has a 50% change that it is the value from parent1 and
	 * a 50% change that it is that value from parent2.
	 * @param parent1 index of the first parent.
	 * @param parent2 index of the second parent.
	 * @return a new child
	 */
	protected Stack<Float> breed(Stack<Float> parent1, Stack<Float> parent2) {
		Stack<Float> child = new Stack<>();
		Stack<Float> parent1Stack = (Stack<Float>) parent1.clone();
		Stack<Float> parent2Stack = (Stack<Float>) parent2.clone();

		while (!parent1Stack.isEmpty()) {
			float valueParent1 = parent1Stack.pop();
			float valueParent2 = parent2Stack.pop();
			float random = numberGen.nextFloat();
			if (random <= mutateChange) {
				child.push(getRandom(minEdgeValue, maxEdgeValue));
			} else if (random <= 0.5 + 0.5 * mutateChange) {
				child.push(valueParent1);
			} else {
				child.push(valueParent2);
			}
		}
		return child;
	}
}
