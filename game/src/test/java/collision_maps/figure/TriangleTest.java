package collision_maps.figure;

import collision_maps.primitive.Circle;
import collision_maps.primitive.Line;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by jens on 10/11/16.
 */
public class TriangleTest {

	@Test
	public void testCollideInside() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3,5));
		Triangle test2 = new Triangle(new Vector2D(2, 2), new Vector2D(4,2), new Vector2D(3,2.5));
		assertTrue(test1.collide(test2));
		assertTrue(test2.collide(test1));
	}

	@Test
	public void testCollide1() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3, 5));
		Triangle test2 = new Triangle(new Vector2D(3,8), new Vector2D(5,2.1), new Vector2D(1,2));
		assertTrue(test1.collide(test2));
		assertTrue(test2.collide(test1));
	}

	@Test
	public void testCollideFalse() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3, 5));
		Triangle test2 = new Triangle(new Vector2D(-3,-8), new Vector2D(-5,-2.1), new Vector2D(-1,-2));
		assertFalse(test1.collide(test2));
		assertFalse(test2.collide(test1));
	}

	@Test
	public void testCollideCircle() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3,5));
		Circle circle = new Circle(new Vector2D(3,2), 0.1);
		assertTrue(test1.collide(circle));
		assertTrue(circle.collide(test1));
	}

	@Test
	public void testCollideCircle2() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3,5));
		Circle circle = new Circle(new Vector2D(3,2), 20);
		assertTrue(test1.collide(circle));
		assertTrue(circle.collide(test1));
	}

	@Test
	public void testCollideCircle3() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3,5));
		Circle circle = new Circle(new Vector2D(0,0), 20);
		assertTrue(test1.collide(circle));
		assertTrue(circle.collide(test1));
	}

	@Test
	public void testCollideCircle4() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3,5));
		Circle circle = new Circle(new Vector2D(4,6), 3);
		assertTrue(test1.collide(circle));
		assertTrue(circle.collide(test1));
	}

	@Test
	public void testCollideCircleFalse() throws Exception {
		Triangle test1 = new Triangle(new Vector2D(1, 1), new Vector2D(5,1), new Vector2D(3,5));
		Circle circle = new Circle(new Vector2D(4,6), 0.5);
		assertFalse(test1.collide(circle));
		assertFalse(circle.collide(test1));
	}

	@Test
	public void testGetLines() throws Exception {
		Triangle test = new Triangle(new Vector2D(1, 1), new Vector2D(3, 1), new Vector2D(2, 2));
		assertTrue(test.getLines().contains(new Line(new Vector2D(1, 1), new Vector2D(3, 1))));
		assertTrue(test.getLines().contains(new Line(new Vector2D(1, 1), new Vector2D(2, 2))));
		assertTrue(test.getLines().contains(new Line(new Vector2D(2, 2), new Vector2D(3, 1))));
		assertEquals(3, test.getLines().size());
	}
}