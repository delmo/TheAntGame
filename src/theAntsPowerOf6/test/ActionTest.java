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

import theAntsPowerOf6.model.Action;
import theAntsPowerOf6.model.Condition;
import theAntsPowerOf6.model.Direction;
import theAntsPowerOf6.model.Instruction;
import theAntsPowerOf6.model.Turn;

/**
 * @author Tristan
 *
 */
public class ActionTest {

	Action action1;
	Action action2;
	Action action3;
	Action action4;
	Action action5;
	Action action6;
	Action action7;
	Action action8;
	Action action9;
	Action action10;
	
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
		action1 = new Action("Sense Ahead 1 3 Food");
		action2 = new Action("Move 2 0");
		action3 = new Action("PickUp 8 0");
		action4 = new Action("Flip 3 4 5");
		action5 = new Action("Turn Left 0");
		action6 = new Action("Turn Right 0");
		action7 = new Action("Drop 0");
		action8 = new Action("Mark 1 5");
		action9 = new Action("Unmark 1 3");
		action10 = new Action("Ryan 8 0");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link theAntsPowerOf6.model.Action#Action(java.lang.String)}.
	 */
	@Test
	public void testAction() {
		assertEquals("action1", Instruction.Sense, action1.getInstruction());
		assertEquals("action1", 1, action1.getSt1());
		assertEquals("action1", 3, action1.getSt2());
		assertEquals("action1", Direction.Ahead, action1.getDir());
		assertEquals("action1", Condition.Rock, action1.getCon());
		
		assertEquals("action2", Instruction.Move, action1.getInstruction());
		assertEquals("action2", 2, action1.getSt1());
		assertEquals("action2", 0, action1.getSt2());
		
		assertEquals("action3", Instruction.PickUp, action1.getInstruction());
		assertEquals("action3", 8, action1.getSt1());
		assertEquals("action3", 0, action1.getSt2());
		
		assertEquals("action4", Instruction.Flip, action1.getInstruction());
		assertEquals("action4", 3, action1.getNumber());
		assertEquals("action4", 4, action1.getSt1());
		assertEquals("action4", 5, action1.getSt2());
		
		assertEquals("action5", Instruction.Turn, action1.getInstruction());
		assertEquals("action5", Turn.Left, action1.getTurn());
		assertEquals("action5", 0, action1.getDir());
		
		assertEquals("action6", Instruction.Turn, action1.getInstruction());
		assertEquals("action6", Turn.Right, action1.getTurn());
		assertEquals("action6", 0, action1.getDir());
		
		assertEquals("action7", Instruction.Drop, action1.getInstruction());
		assertEquals("action7", 0, action1.getSt1());
		
		assertEquals("action8", Instruction.Mark, action1.getInstruction());
		assertEquals("action8", 5, action1.getSt1());
		assertEquals("action8", 1, action1.getMark());
		
		assertEquals("action9", Instruction.Unmark, action1.getInstruction());
		assertEquals("action9", 3, action1.getSt1());
		assertEquals("action9", 1, action1.getMark());
		
		
	}
	
}
