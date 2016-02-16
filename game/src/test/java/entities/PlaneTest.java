package entities;

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
public class PlaneTest {

	private double direction;
	private Vector2D expected;

	public PlaneTest(Vector2D expected, double direction) {
		this.direction = direction;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ new Vector2D(0, 1), 0},
				{ new Vector2D(-1, 0), 0.5 * Math.PI},
				{ new Vector2D(0, -1), 1 * Math.PI},
				{ new Vector2D(1, 0), 1.5 * Math.PI},
				{ new Vector2D(0, 1), 2 * Math.PI},
				{ new Vector2D(-0.7071, 0.7071), 0.25 * Math.PI},
				{ new Vector2D(0.8716, -0.4903), 4.2}
		});

	}

	@Test
	public void testGetDir() throws Exception {
		Plane plane = new Plane(0, 0, 0, new Vector2D(0,0), 0);
		Vector2D res = plane.getDir(direction);
		assertEquals(expected.getX(), res.getX(), 0.01);
		assertEquals(expected.getY(), res.getY(), 0.01);
	}
}
