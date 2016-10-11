package planes.controller;

import planes.entities.Plane;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by jens on 2/16/16.
 */
public class PlaneControllerTest {

	Plane planeMock;
	PlaneController controller;

	@Before
	public void setUp() throws Exception {
		planeMock = mock(Plane.class);
		controller = new PlaneController(planeMock, null);
	}

	@Test
	public void testGoLeft() throws Exception {
		ArgumentCaptor<Double> argument = ArgumentCaptor.forClass(Double.class);
		controller.goLeft();

		verify(planeMock, times(1)).setVelOr(argument.capture());
		verifyNoMoreInteractions(planeMock);
		assertEquals(-controller.getTurnSpeed(), argument.getValue(), 0.01);
	}

	@Test
	public void testGoRight() throws Exception {
		ArgumentCaptor<Double> argument = ArgumentCaptor.forClass(Double.class);
		controller.goRight();

		verify(planeMock, times(1)).setVelOr(argument.capture());
		verifyNoMoreInteractions(planeMock);
		assertEquals(controller.getTurnSpeed(), argument.getValue(), 0.01);
	}

	@Test
	public void testGoStraight() throws Exception {
		ArgumentCaptor<Double> argument = ArgumentCaptor.forClass(Double.class);
		controller.goStraight();

		verify(planeMock, times(1)).setVelOr(argument.capture());
		verifyNoMoreInteractions(planeMock);
		assertEquals(0, argument.getValue(), 0.01);
	}

	@Test
	public void testGetPos() throws Exception {
		Vector2D vector = new Vector2D(1,3);
		when(planeMock.getPos()).thenReturn(vector);
		assertEquals(vector, controller.getPos());
	}

	@Test
	public void testGetOrientation() throws Exception {
		double orientation = 2;
		when(planeMock.getOrientation()).thenReturn(orientation);
		assertEquals(orientation, controller.getOrientation(), 0.001);
	}
}