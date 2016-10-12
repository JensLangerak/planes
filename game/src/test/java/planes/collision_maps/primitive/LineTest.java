package planes.collision_maps.primitive;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 2/29/16.
 */
public class LineTest {

	Line line;
	@Before
	public void setUp() throws Exception {
		line = new Line(new Vector2D(-2, -1), new Vector2D(1, 3));
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testSetStart() throws Exception {
		assertEquals(line.getStart().getX(), -2.0, 0.1);
		assertEquals(line.getStart().getY(), -1.0, 0.1);
		line.setStart(new Vector2D(4, 6));
		assertEquals(line.getStart().getX(), 4, 0.1);
		assertEquals(line.getStart().getY(), 6, 0.1);
	}

	@Test
	public void testSetStart2() throws Exception {
		line = new Line(new Vector2D(-2, -1), new Vector2D(-4, -3));

		assertEquals(line.getStart().getX(), -2.0, 0.1);
		assertEquals(line.getStart().getY(), -1.0, 0.1);
		line.setStart(new Vector2D(4, 6));
		assertEquals(line.getStart().getX(), 4, 0.1);
		assertEquals(line.getStart().getY(), 6, 0.1);
	}

	@Test
	public void testSetEnd() throws Exception {
		assertEquals(line.getEnd().getX(), 1.0, 0.1);
		assertEquals(line.getEnd().getY(), 3.0, 0.1);
		line.setEnd(new Vector2D(-4, -6));
		assertEquals(line.getEnd().getX(), -4, 0.1);
		assertEquals(line.getEnd().getY(), -6, 0.1);
	}

	@Test
	public void testSetEnd2() throws Exception {
		line = new Line(new Vector2D(-2, -1), new Vector2D(-4, -3));

		assertEquals(line.getEnd().getX(), -4.0, 0.1);
		assertEquals(line.getEnd().getY(), -3.0, 0.1);
		line.setEnd(new Vector2D(4, 6));
		assertEquals(line.getEnd().getX(), 4, 0.1);
		assertEquals(line.getEnd().getY(), 6, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckDifferentPoints() throws Exception {
		line = new Line(new Vector2D(2, 3), new Vector2D(2, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckDifferentPointsSetStart() throws Exception {
		line.setStart(new Vector2D(line.getEnd().getX(), line.getEnd().getY()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCheckDifferentPointsSetEnd() throws Exception {
		line.setEnd(new Vector2D(line.getStart().getX(), line.getStart().getY()));
	}


	@Test
	public void testCollideLine() throws Exception {
		line = new Line(new Vector2D(2, 5), new Vector2D(5, 20));
		Line line2 = new Line(new Vector2D(3, 6), new Vector2D(4, 19));
		assertTrue(line.collide(line2));
	}

	@Test
	public void testCollideLineMiss() throws Exception {
		line = new Line(new Vector2D(2, 5), new Vector2D(5, 5));
		Line line2 = new Line(new Vector2D(1, 2), new Vector2D(1, 9));
		assertFalse(line.collide(line2));
	}


	@Test
	public void testRealDistance() throws Exception {
		line = new Line(new Vector2D(2, 5), new Vector2D(5, 10));
		assertEquals(line.realDistance(new Vector2D(-1, 0)), 5.83, 0.1);
	}

	@Test
	public void testRealDistance2() throws Exception {
		line = new Line(new Vector2D(2, 5), new Vector2D(7, 10));
		assertEquals(line.realDistance(new Vector2D(5, 6)), 1.41, 0.1);
	}

}