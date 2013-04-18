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

/**
 * @author Tristan
 *
 */
public class AntBrainTest {

	AntBrain brain;
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
	
		brain = new AntBrain("Test Brain", "clever1.brain");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	

	/**
	 * Test method for {@link theAntsPowerOf6.model.AntBrain#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("name of the brain" , "Test Brain", brain.getName());
	}
	


	/**
	 * Test method for {@link theAntsPowerOf6.model.AntBrain#getInstruction(int)}.
	 */
	@Test
	public void testGetInstruction() {
		assertEquals("State 0", "Turn Left 1", brain.getInstruction(0).toString());
	}
	

}
