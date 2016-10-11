package planes.entities;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jens on 2/15/16.
 */
public class EntityTest {

	Entity test;
	@Before
	public void setUp() throws Exception {
		test = new Plane(2,0,3, new Vector2D(0,0), 0);
	}

	@After
	public void tearDown() throws Exception {

	}


	@Test
	public void testSetVelX() throws Exception {
		assertEquals(0, test.getVelX(), 0.01);
		assertEquals(1.2, test.setVelX(1.2), 0.01);
		assertEquals(1.2, test.getVelX(), 0.01);
	}

	@Test
	public void testSetVelXToBig() throws Exception {
		assertEquals(0, test.getVelX(), 0.01);
		assertEquals(2, test.setVelX(3.2), 0.01);
		assertEquals(2, test.getVelX(), 0.01);
	}

	@Test
	public void testSetVelXToSmall() throws Exception {
		assertEquals(0, test.getVelX(), 0.01);
		assertEquals(0, test.setVelX(-3.2), 0.01);
		assertEquals(0, test.getVelX(), 0.01);
	}

	@Test
	public void testConstructNonZeroMinX() throws Exception {
		test = new Plane(2,1,2, new Vector2D(0,0), 0);
		assertEquals(1, test.getVelX(), 0.01);
	}

	@Test
	public void testSetVelOr() throws Exception {
		assertEquals(0, test.getVelOr(), 0.01);
		assertEquals(1.2, test.setVelOr(1.2), 0.01);
		assertEquals(1.2, test.getVelOr(), 0.01);
	}

	@Test
	public void testSetVelOrNeg() throws Exception {
		assertEquals(0, test.getVelOr(), 0.01);
		assertEquals(-1.2, test.setVelOr(-1.2), 0.01);
		assertEquals(-1.2, test.getVelOr(), 0.01);
	}

	@Test
	public void testSetVelOrToBig() throws Exception {
		assertEquals(0, test.getVelOr(), 0.01);
		assertEquals(3, test.setVelOr(3.2), 0.01);
		assertEquals(3, test.getVelOr(), 0.01);
	}

	@Test
	public void testSetVelOrToSmall() throws Exception {
		assertEquals(0, test.getVelOr(), 0.01);
		assertEquals(-3, test.setVelOr(-3.2), 0.01);
		assertEquals(-3, test.getVelOr(), 0.01);
	}


	@Test
	public void testGetPos() throws Exception {
		assertEquals(new Vector2D(0 ,0), test.getPos());
		test.setPos(new Vector2D(3, 2));
		assertEquals(new Vector2D(3 ,2), test.getPos());
	}

	@Test
	public void testGetOrientation() throws Exception {
		assertEquals(0, test.getOrientation(), 0.01);
		test.setOrientation(1);
		assertEquals(1, test.getOrientation(), 0.01);
	}


}

