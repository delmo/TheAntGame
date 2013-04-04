package theAntsPowerOf6.model;

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
	
	public Action(String line){
		input = line;
		st1=-1;
		st2=-1;
		mark=-1;
		number=-1;
		instruction = null;
		dir = null;
		con = null;
		String important = input.split("\t")[0];
		String[] inputs = important.split(" ");
		if(inputs.length == 0){
			return;
		}
		String command = inputs[0];
		try{
			if(command.equals("Sense")){
				instruction = Instruction.Sense;
				dir = parseDirection(inputs[1]);
				st1 = parseState(inputs[2]);
				st2 = parseState(inputs[3]);
				con = parseCondition(inputs[4]);
				
				if(con == Condition.Marker){
					mark = parseMarker(inputs[5]);
				}
			}else if(command.equals("PickUp")){
				instruction = Instruction.PickUp;
				st1 = parseState(inputs[1]);
				st2 = parseState(inputs[2]);
			}else if(command.equals("Drop")){
				instruction = Instruction.Drop;
				st1 = parseState(inputs[1]);				
			}else if(command.equals("Move")){
				instruction = Instruction.Move;
				st1 = parseState(inputs[1]);
				st2 = parseState(inputs[2]);
			}else if(command.equals("Flip")){
				instruction = Instruction.Flip;
				number = parseNumber(inputs[1]);
				st1 = parseState(inputs[2]);
				st2 = parseState(inputs[3]);
			}else if(command.equals("Turn")){
				instruction = Instruction.Turn;
				turn = parseTurn(inputs[1]);
				st1 = parseState(inputs[2]);
			}else if(command.equals("Mark")){
				instruction = Instruction.Mark;
				mark = parseMarker(inputs[1]);
				st1 = parseState(inputs[2]);
			}else if(command.equals("Unmark")){
				instruction = Instruction.Unmark;
				mark = parseMarker(inputs[1]);
				st1 = parseState(inputs[2]);
			}else{
				throw new Exception("Instruction cannot parse.");
			}
		}catch(Exception e){
			System.out.println("Cannot parse the command: " + e.getMessage());
		}
	}

	public int getSt1() {
		return st1;
	}

	public int getSt2() {
		return st2;
	}

	public int getMark() {
		return mark;
	}

	public int getNumber() {
		return number;
	}

	public String getInput() {
		return input;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public Direction getDir() {
		return dir;
	}

	public Condition getCon() {
		return con;
	}

	public Turn getTurn() {
		return turn;
	}

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
}
