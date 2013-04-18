/**
 * 
 */
package theAntsPowerOf6.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import theAntsPowerOf6.model.Ant;
import theAntsPowerOf6.model.AntColor;
import theAntsPowerOf6.model.Position;

/**
 * @author Tristan
 *
 */
public class AntTest {

	Ant ant = new Ant(1, AntColor.Red, new Position(1, 1));
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#Ant(int, theAntsPowerOf6.model.AntColor, theAntsPowerOf6.model.Position)}.
	 */
	@Test
	public void testAnt() {
		fail("Not yet implemented");
		
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#getState()}.
	 */
	@Test
	public void testGetState() {
		assertEquals("Initial state must be zero", 0, ant.getState());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#setState(int)}.
	 */
	@Test
	public void testSetState() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals("Ant ID is equal", 1, ant.getId());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#isColour()}.
	 */
	@Test
	public void testIsColour() {
		assertEquals("Ant colour is equal", AntColor.Red, ant.isColour());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#getResting()}.
	 */
	@Test
	public void testGetResting() {
		assertEquals("Ant resting is zero", 0, ant.getResting());;
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#setResting()}.
	 */
	@Test
	public void testSetResting() {
		ant.setResting();		
		assertEquals("It should be 14", 14, ant.getResting());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#decResting()}.
	 */
	@Test
	public void testDecResting() {
		ant.setResting();
		ant.decResting();
		assertEquals("It should be 13", 13, ant.getResting());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#getDirection()}.
	 */
	@Test
	public void testGetDirection() {
		ant.setDirection(4);
		assertEquals("Result is 4", 4, ant.getDirection());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#setDirection(int)}.
	 */
	@Test
	public void testSetDirection() {
		ant.setDirection(1);
		assertEquals("Result is 1", 1, ant.getDirection());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#hasfood()}.
	 */
	@Test
	public void testHasfood() {
		assertFalse(ant.hasfood());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#setHasfood(boolean)}.
	 */
	@Test
	public void testSetHasfood() {
		ant.setHasfood(true);
		assertTrue(ant.hasfood());
	}

	

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#move(theAntsPowerOf6.model.Position)}.
	 */
	@Test
	public void testMove() {
		ant.move(new Position(2,2));
		assertEquals("Position should be 2,2", new Position(2,2), ant.getPosition());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Ant#getPosition()}.
	 */
	@Test
	public void testGetPosition() {
		ant.move(new Position(2,2));
		assertEquals("Position should be 3,3", new Position(3,3), ant.getPosition());
	}

	

}
