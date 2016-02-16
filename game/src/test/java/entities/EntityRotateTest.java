package entities;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class EntityRotateTest {
	Entity test;
	@Before
	public void setUp() throws Exception {
		test = new Plane(2,0,3, new Vector2D(0,0), 0);
	}

	private Vector2D start;
	private double rotate;
	private Vector2D expected;

	public EntityRotateTest(Vector2D start, double rotate, Vector2D expected) {
		this.start = start;
		this.rotate = rotate;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ new Vector2D(0,0)     , 0.3             , new Vector2D(0,0) },
				{ new Vector2D(1,0)     , Math.PI * 0.5   , new Vector2D(0,-1) },
				{ new Vector2D(1,0)     , Math.PI         , new Vector2D(-1,0) },
				{ new Vector2D(1,0)     , Math.PI * 1.5   , new Vector2D(0,1) },
				{ new Vector2D(1,0)     , Math.PI * 2     , new Vector2D(1,0) },
				{ new Vector2D(1,0)     , 0               , new Vector2D(1,0) },
				{ new Vector2D(1,0)     , 1               , new Vector2D(0.5403,-0.8415) },
				{ new Vector2D(3.2,-1.4), 4               , new Vector2D(-1.0321,3.3368) }

				});

	}

	@Test
	public void testRotate() throws Exception {
		Vector2D res = test.rotate(start, rotate);
		assertEquals(expected.getX(), res.getX(), 0.001);
		assertEquals(expected.getY(), res.getY(), 0.001);
	}
}
