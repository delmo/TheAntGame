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

import theAntsPowerOf6.model.AntBrain;
import theAntsPowerOf6.model.AntColor;
import theAntsPowerOf6.model.Colony;

/**
 * @author Tristan
 *
 */
public class ColonyTest {

	Colony col;
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
		AntColor color = AntColor.Black;
		AntBrain brain = new AntBrain("intelligent", "clever2.brain");
		col = new Colony("Red Team", brain, color);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

	/**
	 * Test method for {@link theAntsPowerOf6.model.Colony#getColonyName()}.
	 */
	@Test
	public void testGetColonyName() {
		assertEquals("Red Team", "Red Team", col.getColonyName());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Colony#setColonyName(java.lang.String)}.
	 */
	@Test
	public void testSetColonyName() {
		col.setColonyName("Black Team");
		assertEquals("Change to black", "Black Team", col.getColonyName());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Colony#getColonyBrain()}.
	 */
	@Test
	public void testGetColonyBrain() {
		assertEquals("Get the clever2.brain", new AntBrain("intelligent", "clever2.brain"), col.getColonyBrain());
	}

	
	/**
	 * Test method for {@link theAntsPowerOf6.model.Colony#getConlonyColor()}.
	 */
	@Test
	public void testGetConlonyColor() {
		AntColor c = AntColor.Red;
		col.setConlonyColor(c);
		assertEquals("Change to red color", AntColor.Red, col.getConlonyColor());
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Colony#setConlonyColor(theAntsPowerOf6.model.AntColor)}.
	 */
	@Test
	public void testSetConlonyColor() {
		AntColor c = AntColor.Black;
		col.setConlonyColor(c);
		assertEquals("Change to Black color", AntColor.Black, col.getConlonyColor());
	}
	

}
