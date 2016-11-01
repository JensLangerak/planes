package planes.controller.AI;

import learner.neural_network.NeuralNetwork;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import planes.Game;
import planes.controller.Agent;
import planes.controller.PlaneController;
import planes.entities.Entity;
import planes.entities.Plane;
import planes.entities.Projectile;

import java.util.List;

/**
 * Created by jens on 10/24/16.
 */
public class SimpleAI extends Agent implements AI {
	protected PlaneController plane;
	protected NeuralNetwork network;
	protected float[] prevValues;


	public SimpleAI(PlaneController plane, NeuralNetwork network) {
		this.plane = plane;
		//TODO check network start and end size
		this.network = network;
		prevValues = new float[6];

	}

	@Override
	public void step() {
		float[] startValues = getStartValues();
		float[] endValues = network.calculateNetwork(startValues);

		performAction(endValues);

	}

	private void performAction(float[] endValues) {
		float steer = endValues[0] - endValues[1];
		if (steer > 0.1) {
			plane.goRight();
		} else if (steer < 0.1) {
			plane.goLeft();
		} else {
			plane.goStraight();
		}

		if (endValues[2] > 0.5) {
			plane.fire();
		}
	}

	protected float[] getStartValues() {
		float[] startValues = new float[getStartNodes()];
		Vector2D centerRefPoint = plane.getPos().add(10, Entity.getDir(plane.getOrientation()));

		Vector2D leftRefPoint  = plane.getPos()
									  .add(10,
										   Entity.getDir(
												   (plane.getOrientation() - 0.5 * Math.PI)
														   % (2 * Math.PI)));
		Vector2D rightRefPoint = plane.getPos()
									  .add(10,
										   Entity.getDir(
												   (plane.getOrientation() + 0.5 * Math.PI)
														   % (2 * Math.PI)));
		List<Entity> entityList = Game.getInstance().getEntityManager().getEntities();
		startValues[0]  = getClosestPlane(entityList, leftRefPoint);
		startValues[1]  = getClosestPlane(entityList, centerRefPoint);
		startValues[2]  = getClosestPlane(entityList, rightRefPoint);
		startValues[3]  = getClosestProjectile(entityList, leftRefPoint);
		startValues[4]  = getClosestProjectile(entityList, centerRefPoint);
		startValues[5]  = getClosestProjectile(entityList, rightRefPoint);
		startValues[6]  = prevValues[0];
		startValues[7]  = prevValues[1];
		startValues[8]  = prevValues[2];
		startValues[9]  = prevValues[3];
		startValues[10] = prevValues[4];
		startValues[11] = prevValues[5];
		startValues[12] = 1;

		for (int i = 0; i < prevValues.length; i++) {
			prevValues[i] = startValues[i];
		}

		return startValues;
	}

	@Override
	public int getStartNodes() {
		return 13;
	}

	@Override
	public int getEndNodes() {
		return 3;
	}

	protected float getClosestPlane(List<Entity> entityList, Vector2D pos) {
		return entityList.stream()
						 //get all the planes, except the current one
						 .filter(e -> e instanceof Plane && !e.equals(this.plane.getPlane()))
						 .map(e -> e.getPos().distance(pos)) //calculate the distance
						 .min((d1, d2) -> Double.compare(d1, d2)) //get the min distance
						 .get().floatValue();
	}

	protected float getClosestProjectile(List<Entity> entityList, Vector2D pos) {
		return entityList.stream()
						 //get all the projectiles that are not fired by this plane
						 .filter(
								 e -> e instanceof Projectile
										 && !((Projectile) e).getOwner()
															 .equals(this.plane.getPlane()))
						 .map(e -> e.getPos().distance(pos)) //calculate the distance
						 .min((d1, d2) -> Double.compare(d1, d2)) //get the min distance
						 .get().floatValue();
	}
}
