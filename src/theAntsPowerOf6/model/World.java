package theAntsPowerOf6.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class World {

	// world is empty? what happen
	private Colony blackTeam;
	private Colony redTeam;
	private int width;
	private int height;
	private int rounds;
	private ArrayList<Ant> ants;
	private ArrayList<Move> antMoves;
	private ArrayList<int[]> statistics;
	private Cell[][] cells;
	private AntBrain[] brains;

	public World(Colony black, Colony red, Map map) {
		this.blackTeam = black;
		this.redTeam = red;		
		this.width = map.getWidth();
		this.height = map.getHeight();
		this.cells = new Cell[this.width][this.height];
		this.rounds = 0;
		this.ants = new ArrayList();
		this.antMoves = new ArrayList();
		this.statistics = new ArrayList();
		this.brains = new AntBrain[2];
		this.brains[0] = red.getColonyBrain();
		this.brains[1] = black.getColonyBrain();
		getMap(map);
	}
	
	private void step(int antId) {
		if (isAntAlive(antId)) {
			Position position = lookForAntID(antId);
			System.out.println(position.toString());
			Ant ant = getThisAntAtPosition(position);
			System.out.println("ID:" +ant.getId()+";Resting:"+ant.getResting());
			if (ant.getResting() > 0) {
				ant.setResting();
			} else {
				Action action = getInstruction(ant.isColour(), ant.getState());
				System.out.println(action.getInstruction());
				if (action == null) {
					return;
				}
				// Sense sensedir st1 st2 cond
				if (action.getInstruction() == Instruction.Sense) {
					Position oppPosition = position.sensed_cell(
							ant.getDirection(), action.getDir());
					if (senseTheCell(action.getCon(), ant.isColour(),
							oppPosition, action.getMark())) {
						ant.setState(action.getSt1());
					} else {
						ant.setState(action.getSt2());
					}
				//Mark i st
				} else if (action.getInstruction() == Instruction.Mark) {
					setMarkerAtPosition(ant.isColour(), position,
							action.getMark());
					ant.setState(action.getSt1());
				//Unmark i st
				} else if (action.getInstruction() == Instruction.Unmark) {
					removeMarkerAtPosition(ant.isColour(), position,
							action.getMark());
					ant.setState(action.getSt1());
				//PickUp st1 st2
				} else if (action.getInstruction() == Instruction.PickUp) {
					if ((ant.hasfood()) || (getFoodAtPosition(position) == 0)) {
						ant.setState(action.getSt2());
					} else {
						setFoodAtPosition(getFoodAtPosition(position) - 1,
								position);
						ant.setHasfood(true);
						ant.setState(action.getSt1());
					}
				//Drop st
				} else if (action.getInstruction() == Instruction.Drop) {
					if (ant.hasfood()) {
						ant.setHasfood(false);
						setFoodAtPosition(getFoodAtPosition(position) + 1,
								position);
					}
					ant.setState(action.getSt1());
				//Turn lr st
				} else if (action.getInstruction() == Instruction.Turn) {
					ant.setDirection(Position.toTurn(action.getTurn(),
							ant.getDirection()));
					ant.setState(action.getSt1());
					logMovement(ant, position);
				//Move st1 st2
				} else if (action.getInstruction() == Instruction.Move) {
					Position aheadPosition = position.adjacent_cell(ant
							.getDirection());
					if ((isThisPositionHasAnt(aheadPosition))
							|| (isRockyAtPosition(aheadPosition))) {
						ant.setDirection(action.getSt2());
					} else {
						removeAntAtPosition(position);
						ant.move(aheadPosition);
						ant.setState(action.getSt1());
						checkForSurroundedAnts(aheadPosition);
						logMovement(ant, position);
					}
				//Flip p st1 st2
				} else if (action.getInstruction() == Instruction.Flip) {
					Random random = new Random(100);
					if ((random.nextInt()) == 0) {
						ant.setState(action.getSt1());
					} else {
						ant.setState(action.getSt2());
					}
				}
			}
		}
	}

	public Colony getBlackTeam() {
		return blackTeam;
	}

	public void setBlackTeam(Colony blackTeam) {
		this.blackTeam = blackTeam;
	}

	public Colony getRedTeam() {
		return redTeam;
	}

	public void setRedTeam(Colony redTeam) {
		this.redTeam = redTeam;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTurnCounter() {
		return rounds;
	}

	public void setTurnCounter(int turnCounter) {
		this.rounds = turnCounter;
	}

	public ArrayList<Ant> getAnts() {
		return ants;
	}

	public void setAnts(ArrayList<Ant> ants) {
		this.ants = ants;
	}

	public ArrayList<Move> getAntMoves() {

		return antMoves;
	}

	public void setAntMoves(ArrayList<Move> antMoves) {
		this.antMoves = antMoves;
	}

	public ArrayList<int[]> getStatistics() {
		return this.statistics;
	}

	public void setStatistics() {
		int[] scores = new int[4];
		scores[0] = countAliveAnts(AntColor.Black);
		scores[1] = countScore(AntColor.Black);
		scores[2] = countAliveAnts(AntColor.Red);
		scores[3] = countScore(AntColor.Red);
		this.statistics.add(scores);
	}

	//get cell in specified position
	public Cell getCells(Position position) {
		int x = position.getX();
		int y = position.getY();
		return this.cells[x][y];
	}

	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	public AntBrain[] getBrains() {
		return brains;
	}

	public void setBrains(AntBrain[] brains) {
		this.brains = brains;
	}

	//load the map
	public void getMap(Map map) {
		int id = 0;
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				Position position = new Position(i, j);
				Cell cell = new Cell(map.getWhoIsInMap(i, j), position);
				this.cells[i][j] = cell;
				if (cell.isAntHill(AntColor.Red)) {
					Ant ant = new Ant(id++, AntColor.Red, position);
					this.ants.add(ant);
					cell.setAnt(ant);
				} else if (cell.isAntHill(AntColor.Black)) {
					Ant ant = new Ant(id++, AntColor.Black, position);
					this.ants.add(ant);
					cell.setAnt(ant);
				}
			}
		}
	}
	
	private boolean senseTheCell(Condition condition, AntColor colour,
			Position position, int markerNum) {
		// Friend,Foe,FriendWithFood, FoeWithFood, Food, Rock, Marker,
		// FoeMarker, Home, FoeHome;
		if (condition == Condition.Friend) {
			return isRockyAtPosition(position);
		} else if (condition == Condition.Foe) {
			if (isThisPositionHasAnt(position)) {
				return getThisAntAtPosition(position).isColour() == colour;
			}
		} else if (condition == Condition.FriendWithFood) {
			return checkMarkerAtPosition(colour, position, markerNum);
		} else if (condition == Condition.FoeWithFood) {
			if ((isThisPositionHasAnt(position))
					&& (getThisAntAtPosition(position).isColour() != colour)) {
				return getThisAntAtPosition(position).hasfood();
			} else {
				return false;
			}
		} else if (condition == Condition.Food) {
			return getFoodAtPosition(position) > 0;
		} else if (condition == Condition.Rock) {
			return isAntHillAtPosition(colour.getFoeColor(), position);
		} else if (condition == Condition.Marker) {
			return isAntHillAtPosition(colour, position);
		} else if (condition == Condition.FoeMarker) {
			if ((isThisPositionHasAnt(position))
					&& ((getThisAntAtPosition(position).isColour()) != colour)) {
				return getThisAntAtPosition(position).hasfood();
			} else {
				return false;
			}
		} else if (condition == Condition.Home) {
			return checkMarkerAtPosition(colour.getFoeColor(), position);
		} else if (condition == Condition.FoeHome) {
			if (isThisPositionHasAnt(position)) {
				return getThisAntAtPosition(position).isColour() != colour;
			} else {
				return false;
			}
		}
		return false;

	}
	
	private void markPositionChanged(Position position) {
		getCells(position).setChanged(true);
	}

	public ArrayList<Cell> getAllMarkedCells() {
		ArrayList allMarkedCells = new ArrayList();
		for (int i = 1; i < this.width - 1; i++) {
			for (int j = 1; j < this.height - 1; j++) {
				if (this.cells[i][j].checkMarker()) {
					allMarkedCells.add(this.cells[i][j]);
				}
			}
		}
		return allMarkedCells;
	}

	public ArrayList<Cell> getAllChangedCells() {
		ArrayList allChangedCells = new ArrayList<>();
		for (int i = 1; i < this.width - 1; i++) {
			for (int j = 1; j < this.height - 1; j++) {
				if (this.cells[i][j].isChanged()) {
					allChangedCells.add(this.cells[i][j]);
					this.cells[i][j].setChanged(false);
				}
			}
		}
		return allChangedCells;
	}

	public boolean isAntAlive(int antID) {
		if ((antID > this.ants.size()) || (antID < 0)) {
			return false;
		}
		return this.ants.get(antID) != null;
	}

	private boolean isThisPositionHasAnt(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.hasAnt();
		}
		return false;
	}

	private Ant getThisAntAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.getAnt();
		}
		return null;
	}

	private Position lookForAntID(int antID) {
		if (this.ants.get(antID) != null) {
			return this.ants.get(antID).getPosition();
		}
		return null;
	}

	private void removeAntAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.removeAnt();
		}
	}

	private void killAntAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			if (cell.hasAnt()) {
				Ant ant = cell.getAnt();
				antMoveTo(ant, new Position(-1, -1));
				this.ants.set(ant.getId(), null);
				cell.removeAnt();
			}
		}
	}

	private void antMoveTo(Ant ant, Position position) {
		// TODO Auto-generated method stub
		Cell cell = getCells(position);
		if (cell != null) {
			cell.setAnt(ant);
		}
	}

	public int getFoodAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.getFoodCount();
		}
		return -1;
	}

	private void setFoodAtPosition(int foodNumber, Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.setFoodCount(foodNumber);
			markPositionChanged(position);

		}
	}

	private boolean isRockyAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.isRocky();
		}
		return false;
	}

	private boolean isAntHillAtPosition(AntColor colour, Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.isAntHill(colour);
		}
		return false;
	}

	private void setMarkerAtPosition(AntColor colour, Position position,
			int markerNum) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.setMarker(colour, markerNum);
			markPositionChanged(position);
		}
	}

	private void removeMarkerAtPosition(AntColor colour, Position position,
			int markerNum) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.clearMarker(colour, markerNum);
			markPositionChanged(position);
		}
	}

	private boolean checkMarkerAtPosition(AntColor colour, Position position,
			int markerNum) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.checkMarker(colour, markerNum);
		}
		return false;
	}

	private boolean checkMarkerAtPosition(AntColor colour, Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.checkMarker(colour);
		}
		return false;
	}

	

	private int checkNumAdjacentAnts(AntColor colour, Position position) {
		int adjAnts = 0;
		// 6 direction
		for (int i = 0; i < 6; i++) {
			Position neighbour = position.adjacent_cell(i);
			if ((isThisPositionHasAnt(neighbour))
					&& (getThisAntAtPosition(neighbour).isColour() == colour)) {
				adjAnts++;
			}
		}
		return adjAnts;
	}

	private void checkForSurroundingAntAndKill(Position position) {
		if (isThisPositionHasAnt(position)) {
			Ant ant = getThisAntAtPosition(position);
			if (checkNumAdjacentAnts(ant.isColour().getFoeColor(), position) >= 5) {
				killAntAtPosition(position);
				int carriedFood = 0;
				if (ant.hasfood()) {
					carriedFood = 1;
				}
				setFoodAtPosition(
						3 + getFoodAtPosition(position) + carriedFood, position);
			}
		}
	}

	private void checkForSurroundedAnts(Position position) {
		checkForSurroundingAntAndKill(position);
		for (int i = 0; i < 6; i++) {
			Position adjAnt = position.adjacent_cell(i);
			checkForSurroundingAntAndKill(adjAnt);
		}
	}

	private Action getInstruction(AntColor colour, int state) {
		AntBrain brain = null;
		if (colour == AntColor.Red) {
			brain = this.brains[0];

		} else {
			brain = this.brains[1];
		}
		return brain.getInstruction(state);
	}

	

	private void logMovement(Ant ant, Position position) {
		this.antMoves.add(new Move(ant, position));

	}

	public int countScore(AntColor antHillColour) {
		int numFood = 0;
		for (Cell[] row : this.cells) {
			for (Cell col : row) {
				if (col.isAntHill(antHillColour)) {
					numFood += col.getFoodCount();
				}
			}
		}
		return numFood;
	}

	public int countAliveAnts(AntColor colour) {
		int numAnts = 0;
		for (int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if ((this.cells[i][j].hasAnt())
						&& (this.cells[i][j].getAnt().isColour() == colour)) {
					numAnts++;
				}
			}
		}
		return numAnts;
	}

	public Colony getTheWinner(){
		int black = countScore(AntColor.Black);
		int red = countScore(AntColor.Red);
		if(black > red){
			return this.blackTeam; //black is the winner
		}else if(red > black){
			return this.redTeam; //red is the winner
		}else{
			return null; //tie
		}
	}
	
	public void run(int numSteps){
		int steps = numSteps;
		while(steps > 0){
			round();
		}
	}
	
	
	private void round() {
		// TODO Auto-generated method stub
		this.rounds += 1;
		if(this.rounds%10 == 0){
			setStatistics();
		}
		for(int i=0; i<this.ants.size(); i++){
			step(i);
		}
	}

	public String toString() {
		String print = "";
		for (Cell[] row : this.cells) {
			for (Cell col : row) {
				print += col + "\n";
			}
		}
		return print;
	}
	
	private void run(int numSteps, String file){
		BufferedWriter writeOut = null;
		try{
			int r = 0;
			writeOut = new BufferedWriter(new FileWriter(file));
			while(r<numSteps){
				round();
				writeOut.write("Round number: " + r + "\n" + toString());
				r++;
			}
			writeOut.close();
		}catch(Exception e){
			System.err.println("Cannot write to " + file + " : " + e.getMessage());
			try{
				if(writeOut != null){
					writeOut.close();
				}
			}catch(Exception ie){
				System.err.println("File cannot open nor close. " + ie.getMessage());
			}
		}finally{
			try{
				if(writeOut != null){
					writeOut.close();
				}
			}catch(Exception oe){
				oe.printStackTrace();
			}
		}
	}
}
