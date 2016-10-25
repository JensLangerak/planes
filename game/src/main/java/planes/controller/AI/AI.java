package planes.controller.AI;

/**
 * Created by jens on 10/24/16.
 */
public interface AI {

	/**
	 * Execute the AI.
	 */
	void step();

	int getStartNodes();

	int getEndNodes();
}
