package entities;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by jens on 2/16/16.
 */
@RunWith(value = Parameterized.class)
public class PlaneTestMove {

	private Plane plane;
	private double speed;
	private double rotation;
	private Vector2D finalPosition;
	private double finalRotation;

	public PlaneTestMove(Plane plane, double speed, double rotation, Vector2D finalPosition, double finalRotation) {
		this.plane = plane;
		this.speed = speed;
		this.rotation = rotation;
		this.finalPosition = finalPosition;
		this.finalRotation = finalRotation;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ new Plane(2, 0, 2, new Vector2D(0,0), 0), 0, 0, new Vector2D(0,0), 0},
				{ new Plane(2, 0, 2, new Vector2D(0,0), 0), 1, 0, new Vector2D(0,1), 0},
				{ new Plane(2, 0, 2, new Vector2D(0,0), 0), 0, 1, new Vector2D(0,0), 1},
				{ new Plane(2, 0, 2, new Vector2D(0,0), 0), 1, 1, new Vector2D(0,1), 1},
				{ new Plane(2, 0, 5, new Vector2D(-1,2), Math.PI * 0.5), 2, 3, new Vector2D(-3,2),
						(Math.PI * 0.5 + 3) },
				{ new Plane(2, 0, 5, new Vector2D(-1,2), Math.PI), 2, -1, new Vector2D(-1,0),
						(Math.PI -1) },
				{ new Plane(2, 0, 5, new Vector2D(4,3), 5), 1.2, -3, new Vector2D(5.1507,3.3404), 2}

		});

	}

	@Test
	public void testMove() throws Exception {
		this.plane.setVelX(speed);
		this.plane.setVelOr(rotation);
		this.plane.move();
		assertEquals(finalPosition.getX(), this.plane.getPos().getX(), 0.01);
		assertEquals(finalPosition.getY(), this.plane.getPos().getY(), 0.01);
		assertEquals(finalRotation, this.plane.getOrientation(), 0.01);
	}

}