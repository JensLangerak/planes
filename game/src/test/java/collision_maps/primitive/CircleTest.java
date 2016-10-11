package collision_maps.primitive;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 2/29/16.
 */
public class CircleTest {
	Circle circle;

	@Before
	public void setUp() throws Exception {
		circle = new Circle(new Vector2D(0, 0), 3);
	}

	@Test
	public void testSetCenter() throws Exception {
		assertEquals(circle.getCenter().getX(), 0.0, 0.001);
		assertEquals(circle.getCenter().getY(), 0.0, 0.001);
		circle.setCenter(new Vector2D(3, -2));
		assertEquals(circle.getCenter().getX(), 3.0, 0.001);
		assertEquals(circle.getCenter().getY(), -2.0, 0.001);
	}

	@Test
	public void testGetCenter() throws Exception {
		assertEquals(circle.getCenter().getX(), 0.0, 0.001);
		assertEquals(circle.getCenter().getY(), 0.0, 0.001);
	}

	@Test
	public void testGetSize() throws Exception {
		assertEquals(circle.getSize(), 3.0, 0.001);
	}

	@Test
	public void testSetSize() throws Exception {
		circle.setSize(2.1);
		assertEquals(circle.getSize(), 2.1, 0.001);
	}

	@Test
	public void testSetSize0() throws Exception {
		circle.setSize(0);
		assertEquals(circle.getSize(), 0, 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetSizeNegative() throws Exception {
		circle.setSize(-2);
	}

	@Test
	public void testCollideLineMiss() throws Exception {
		Line line = new Line(new Vector2D(2, 3), new Vector2D(5,8));
		circle = new Circle(new Vector2D(-2, 3), 3.9);
		assertFalse(circle.collide(line));
	}

	@Test
	public void testCollideLineDubbleIntersect() throws Exception {
		Line line = new Line(new Vector2D(2, 3), new Vector2D(12,13));
		circle = new Circle(new Vector2D(6, 6), 2);
		assertTrue(circle.collide(line));
	}

	@Test
	public void testCollideLineSingleIntersect() throws Exception {
		Line line = new Line(new Vector2D(2, 3), new Vector2D(6,8));
		circle = new Circle(new Vector2D(1, 1), 3);
		assertTrue(circle.collide(line));
	}

	@Test
	public void testCollideLineSingleIntersect2() throws Exception {
		Line line = new Line(new Vector2D(2, 3), new Vector2D(6,8));
		circle = new Circle(new Vector2D(8, 8), 3);
		assertTrue(circle.collide(line));
	}


	@Test
	public void testCollideLineInCircle() throws Exception {
		Line line = new Line(new Vector2D(2, 2), new Vector2D(3,3));
		circle = new Circle(new Vector2D(2, 3), 3);
		assertTrue(circle.collide(line));
	}
	@Test
	public void testCollideCircle() throws Exception {
		circle = new Circle(new Vector2D(2, 3), 1);
		Circle circle2 = new Circle(new Vector2D(-1, -2), 5);
		assertTrue(circle.collide(circle2));
	}
	@Test
	public void testCollideCircleInside() throws Exception {
		circle = new Circle(new Vector2D(2, 3), 1);
		Circle circle2 = new Circle(new Vector2D(2, 3), 4);
		assertTrue(circle.collide(circle2));
		assertTrue(circle2.collide(circle));
	}

	@Test
	public void testCollideCircleMiss() throws Exception {
		circle = new Circle(new Vector2D(2, 3), 1);
		Circle circle2 = new Circle(new Vector2D(20, 30), 4);
		assertFalse(circle.collide(circle2));

	}
}