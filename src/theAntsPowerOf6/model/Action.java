package theAntsPowerOf6.model;

/** 
 * Action Class is used to hold information about the behaviour of an ant.
 * @author Tristan
 * @version 0.03
 */

public class Action {

	private int st1;
	private int st2;
	private int mark;
	private int number;
	private String input;
	private Instruction instruction;
	private Direction dir;
	private Condition con;
	private Turn turn;
	
	
	/**
	 * This method is used to read the String behaviour of the ant brain.
	 * @param line is the String line of text which includes states, marker number, instruction, direction and condition.  
	 */
	public Action(String line){
		input = line;
		st1=-1;
		st2=-1;
		mark=-1;
		number=-1;
		instruction = null;
		dir = null;
		con = null;
		String important = input.split("\t")[0];// remove any tab
		String[] inputs = important.split(" "); //split all keywords
		if(inputs.length == 0){
			return;
		}
		String command = inputs[0]; //get the command and compare to Instruction enum
		try{
			//Sense sensedir st1 st2 cond
			if(command.equals("Sense")){
				instruction = Instruction.Sense;
				dir = parseDirection(inputs[1]);
				st1 = parseState(inputs[2]); //which state to go
				st2 = parseState(inputs[3]); //which state to go
				con = parseCondition(inputs[4]);
				
				if(con == Condition.Marker){
					mark = parseMarker(inputs[5]);
				}
			//PickUp st1 st2
			}else if(command.equals("PickUp")){
				instruction = Instruction.PickUp;
				st1 = parseState(inputs[1]);
				st2 = parseState(inputs[2]);
			//Drop st
			}else if(command.equals("Drop")){
				instruction = Instruction.Drop;
				st1 = parseState(inputs[1]);
			//Move st1 st2
			}else if(command.equals("Move")){
				instruction = Instruction.Move;
				st1 = parseState(inputs[1]);
				st2 = parseState(inputs[2]);
			//Flip p st1 st2
			}else if(command.equals("Flip")){
				instruction = Instruction.Flip;
				number = parseNumber(inputs[1]);
				st1 = parseState(inputs[2]);
				st2 = parseState(inputs[3]);
			//Turn lr st
			}else if(command.equals("Turn")){
				instruction = Instruction.Turn;
				turn = parseTurn(inputs[1]);
				st1 = parseState(inputs[2]);
			//Mark i st
			}else if(command.equals("Mark")){
				instruction = Instruction.Mark;
				mark = parseMarker(inputs[1]);
				st1 = parseState(inputs[2]);
			//Unmark i st
			}else if(command.equals("Unmark")){
				instruction = Instruction.Unmark;
				mark = parseMarker(inputs[1]);
				st1 = parseState(inputs[2]);
			}else{
				throw new Exception("Instruction cannot parse." + command);
			}
		}catch(Exception e){
			System.out.println("Cannot parse the command: " + e.getMessage());
		}
	}

	/**
	 * @return state 1
	 */
	public int getSt1() {
		return st1;
	}

	/**
	 * @return state 2
	 */
	public int getSt2() {
		return st2;
	}

	/**
	 * @return Marker number
	 */
	public int getMark() {
		return mark;
	}

	/**
	 * @return direction to turn
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @return raw instruction and state
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @return Instruction
	 * Sense, Mark, Unmark, PickUp, Drop, Turn, Move, Flip;
	 */
	public Instruction getInstruction() {
		return instruction;
	}

	/**
	 * @return Direction
	 * Here, Ahead, LeftAhead,  RightAhead;
	 */
	public Direction getDir() {
		return dir;
	}

	/**
	 * @return Condition
	 * Friend,Foe,FriendWithFood, FoeWithFood, Food, Rock, Marker, FoeMarker, Home, FoeHome;
	 */
	public Condition getCon() {
		return con;
	}

	/**
	 * @return Turn
	 * Left, Right
	 */
	public Turn getTurn() {
		return turn;
	}

	/**
	 * Parse the Turn behaviour if it is left or right.
	 * @param string
	 * @return Turn
	 * @throws Exception
	 * 
	 */
	private Turn parseTurn(String string) throws Exception {
		// TODO Auto-generated method stub
		if(string.equals("Right")){
			return Turn.Right;
		}else if(string.equals("Left")){
			return Turn.Left;
		}else{
			throw new Exception("Turn: invalid turn exception.");	
		}
	}

	/**
	 * Convert string to integer
	 * @param string
	 * @return positive int
	 * @throws Exception
	 */
	private int parseNumber(String string) throws Exception {
		// TODO Auto-generated method stub
		try{
			if(new Integer(string) <= 0){
				throw new Exception("Number is less than zero.");
			}else{
				return new Integer(string);
			}
		}catch(NumberFormatException nfe){
			throw new Exception("Number: not numerical value.");
		}
	}

	/**
	 * Check if its outside the boundary 0 < number >5
	 * @param string
	 * @return int marker value
	 * @throws Exception
	 */
	private int parseMarker(String string) throws Exception {
		// TODO Auto-generated method stub
		try{
			if(new Integer(string) < 0 || new Integer(string) > 5){
				throw new Exception("Marker: out of bounds exception");
			}else{
				return new Integer(string);
			}
		}catch(NumberFormatException nfe){
			throw new Exception("Marker: invalid number exception");
		}
		
	}

	/**
	 * Parse the String if one of these condition is met:
	 * Friend,Foe,FriendWithFood, FoeWithFood, Food, Rock, Marker, FoeMarker,
	 * Home, FoeHome
	 * 
	 * @param string
	 * @return Condition
	 * @throws Exception
	 * 
	 */
	private Condition parseCondition(String string) throws Exception {
		// TODO Auto-generated method stub
		//Friend,Foe,FriendWithFood, FoeWithFood, 
		//Food, Rock, Marker, FoeMarker, Home, FoeHome;
		if(string.equals("Friend")){
			return Condition.Friend;
		}else if(string.equals("Foe")){
			return Condition.Foe;
		}else if(string.equals("FriendWithFood")){
			return Condition.FriendWithFood;
		}else if(string.equals("FoeWithFood")){
			return Condition.FoeWithFood;
		}else if(string.equals("Food")){
			return Condition.Food;
		}else if(string.equals("Rock")){
			return Condition.Rock;
		}else if(string.equals("Marker")){
			return Condition.Marker;
		}else if(string.equals("FoeMarker")){
			return Condition.FoeMarker;
		}else if(string.equals("Home")){
			return Condition.Home;
		}else if(string.equals("FoeHome")){
			return Condition.FoeHome;
		}else{
			throw new Exception("Condition: invalid condition exception.");
		}
	}

	/**
	 * Parse String state to which integer state
	 * @param string
	 * @return number of state
	 * @throws Exception
	 */
	private int parseState(String string) throws Exception {
		// TODO Auto-generated method stub
		try{
			if(new Integer(string) < 0 || new Integer(string) > 9999){
				throw new Exception("State: out of bounds exception.");
			}else{
				return new Integer(string);
			}
		}catch(NumberFormatException nfe){
			throw new Exception("State: invalid state number exception.");
		}
	}

	/**
	 * Parse String direction if they are: Here, Ahead, LeftAhead, or RightAhead.
	 * @param string
	 * @return Direction
	 * @throws Exception
	 * 
	 */
	private Direction parseDirection(String string) throws Exception {
		// TODO Auto-generated method stub
		if(string.equals("Here")){
			return Direction.Here;
		}else if(string.equals("Ahead")){
			return Direction.Ahead;
		}else if(string.equals("RightAhead")){
			return Direction.RightAhead;
		}else if(string.equals("LeftAhead")){
			return Direction.LeftAhead;
		}else{
			throw new Exception("Direction: invalid direction exception.");
		}
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Action [st1=" + st1 + ", st2=" + st2 + ", mark=" + mark
				+ ", number=" + number + ", input=" + input + ", instruction="
				+ instruction + ", dir=" + dir + ", con=" + con + ", turn="
				+ turn + "]";
	}
}
