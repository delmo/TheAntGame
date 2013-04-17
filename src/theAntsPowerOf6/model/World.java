package theAntsPowerOf6.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to simulate the two competing colonies.
 * 
 * @author Tristan
 * @version
 * 
 */
public class World {

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
	private Randomizer randomizer;

	/**
	 * Constructor that sets up two colonies and the map they will battle.
	 * 
	 * @param black
	 * @param red
	 * @param map
	 */
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
		this.randomizer = new Randomizer();
		getMap(map);
	}

	int counter = 0;

	private void step(int antId) {
		if (isAntAlive(antId)) {
			Position position = lookForAntID(antId);
			// System.out.println(position.toString());
			Ant ant = getThisAntAtPosition(position);
			// System.out.println("ID:"
			// +ant.getId()+";Resting:"+ant.getResting());
			if (ant.getResting() > 0) {
				ant.setResting();
			} else {
				Action action = getInstruction(ant.isColour(), ant.getState());
				// System.out.println(action.getInstruction());
				if (action == null) {
					return;
				}
				// Sense sensedir st1 st2 cond
				// System.out.println("Step: "+ counter++);
				// System.out.println(action.getInstruction() ==
				// Instruction.Flip);
				if (action.getInstruction() == Instruction.Sense) {
					Position oppPosition = position.sensed_cell(
							ant.getDirection(), action.getDir());
					if (senseTheCell(action.getCon(), ant.isColour(),
							oppPosition, action.getMark())) {
						ant.setState(action.getSt1());
					} else {
						ant.setState(action.getSt2());
					}
					// Mark i st
				} else if (action.getInstruction() == Instruction.Mark) {
					setMarkerAtPosition(ant.isColour(), position,
							action.getMark());
					ant.setState(action.getSt1());
					// Unmark i st
				} else if (action.getInstruction() == Instruction.Unmark) {
					removeMarkerAtPosition(ant.isColour(), position,
							action.getMark());
					ant.setState(action.getSt1());
					// PickUp st1 st2
				} else if (action.getInstruction() == Instruction.PickUp) {
					if ((ant.hasfood()) || (getFoodAtPosition(position) == 0)) {
						ant.setState(action.getSt2());
					} else {
						setFoodAtPosition(getFoodAtPosition(position) - 1,
								position);
						ant.setHasfood(true);
						ant.setState(action.getSt1());
					}
					// Drop st
				} else if (action.getInstruction() == Instruction.Drop) {
					if (ant.hasfood()) {
						ant.setHasfood(false);
						setFoodAtPosition(getFoodAtPosition(position) + 1,
								position);
					}
					ant.setState(action.getSt1());
					// Turn lr st
				} else if (action.getInstruction() == Instruction.Turn) {
					ant.setDirection(Position.toTurn(action.getTurn(),
							ant.getDirection()));
					ant.setState(action.getSt1());
					logMovement(ant, position);
					// Move st1 st2
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
					// Flip p st1 st2
				} else if (action.getInstruction() == Instruction.Flip) {
					if ((this.randomizer.nextInt(action.getNumber())) == 0) {
						ant.setState(action.getSt1());
					} else {
						ant.setState(action.getSt2());
					}
				}
			}
		}
	}

	/**
	 * Get Black colony
	 * 
	 * @return
	 */
	public Colony getBlackTeam() {
		return blackTeam;
	}

	/**
	 * Set Black Colony
	 * 
	 * @param blackTeam
	 */
	public void setBlackTeam(Colony blackTeam) {
		this.blackTeam = blackTeam;
	}

	/**
	 * Get Red colony
	 * 
	 * @return
	 */
	public Colony getRedTeam() {
		return redTeam;
	}

	/**
	 * Set Red Colony
	 * 
	 * @param redTeam
	 */
	public void setRedTeam(Colony redTeam) {
		this.redTeam = redTeam;
	}

	/**
	 * Get World width
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set width of the world.
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get height of the world.
	 * 
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set height of the world.
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the current rounds
	 */
	public int getTurnCounter() {
		return rounds;
	}

	/**
	 * Set the counter, by default is 300,000
	 * 
	 * @param turnCounter
	 */
	public void setTurnCounter(int turnCounter) {
		this.rounds = turnCounter;
	}

	/**
	 * @return list of all ants ordered by thier ID
	 */
	public ArrayList<Ant> getAnts() {
		return ants;
	}

	/**
	 * Set list of ants.
	 * 
	 * @param ants
	 */
	public void setAnts(ArrayList<Ant> ants) {
		this.ants = ants;
	}

	/**
	 * @return all moves.
	 */
	public ArrayList<Move> getAntMoves() {

		return antMoves;
	}

	public void setAntMoves(ArrayList<Move> antMoves) {
		this.antMoves = antMoves;
	}

	/**
	 * @return statistics of the game.
	 */
	public ArrayList<int[]> getStatistics() {
		return this.statistics;
	}

	/**
	 * Count number of alive ants and food count for each colony.
	 */
	public void setStatistics() {
		int[] scores = new int[4];
		scores[0] = countAliveAnts(AntColor.Black);
		scores[1] = countScore(AntColor.Black);
		scores[2] = countAliveAnts(AntColor.Red);
		scores[3] = countScore(AntColor.Red);
		this.statistics.add(scores);
	}

	/**
	 * Get cell in specified position
	 * 
	 * @param position
	 * @return
	 */
	public Cell getCells(Position position) {
		int x = position.getX();
		int y = position.getY();
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return null;
		}
		return cells[x][y];
	}

	/**
	 * Setup cells in the world.
	 * 
	 * @param cells
	 */
	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	/**
	 * @return cells[][]
	 */
	public Cell[][] getCells() {
		return this.cells;
	}

	/**
	 * @return the two ant brains
	 */
	public AntBrain[] getBrains() {
		return brains;
	}

	/**
	 * Store two ant brains. Only two ant brains can be store.
	 * 
	 * @param brains
	 */
	public void setBrains(AntBrain[] brains) {
		this.brains = brains;
	}

	/**
	 * Load the map in the world, setup the cells and ant ID for each ants in
	 * the world.
	 * 
	 * @param map
	 */
	public void getMap(Map map) {
		int id = 0;
		for (int i = 0; i < map.getHeight(); i++) {
			for (int j = 0; j < map.getWidth(); j++) {
				Position position = new Position(j, i);
				Cell cell = new Cell(map.getWhoIsInMap(j, i), position);
				this.cells[i][j] = cell;
				if (cell.isAntHill(AntColor.Red)) {
					Ant ant = new Ant(id++, AntColor.Red, position);
					ants.add(ant);
					cell.setAnt(ant);
				} else if (cell.isAntHill(AntColor.Black)) {
					Ant ant = new Ant(id++, AntColor.Black, position);
					ants.add(ant);
					cell.setAnt(ant);
				}
			}
		}
	}

	/**
	 * This method is used to sense the specific cell in the world.
	 * 
	 * @param condition
	 * @param colour
	 * @param position
	 * @param markerNum
	 * @return true if ant the condition in the specified position is true,
	 *         otherwise, false.
	 */
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

	/**
	 * Mark if the position had changed.
	 * 
	 * @param position
	 */
	private void markPositionChanged(Position position) {
		getCells(position).setChanged(true);
	}

	/**
	 * Check all cells if they marked or not. This is useful in GUI to represent
	 * which cell has mark and what colour.
	 * 
	 * @return
	 */
	public ArrayList<Cell> getAllMarkedCells() {
		ArrayList<Cell> allMarkedCells = new ArrayList<Cell>();
		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				if (cells[i][j].checkMarker()) {
					allMarkedCells.add(cells[i][j]);
				}
			}
		}
		return allMarkedCells;
	}

	/**
	 * @return all cells that changed.
	 */
	public ArrayList<Cell> getAllChangedCells() {
		ArrayList<Cell> allChangedCells = new ArrayList<Cell>();
		for (int i = 1; i < height; i++) {
			for (int j = 1; j < width; j++) {
				if (cells[i][j].isChanged()) {
					allChangedCells.add(cells[i][j]);
					cells[i][j].setChanged(false);
				}
			}
		}
		return allChangedCells;
	}

	/**
	 * Checks if an ant is alive based on the unique ID
	 * @param antID
	 * @return true if an ant is alive, otherwise, false.
	 */
	public boolean isAntAlive(int antID) {
		if ((antID > ants.size()) || (antID < 0)) {
			return false;
		}
		return ants.get(antID) != null;
	}

	/**
	 * Check if there is an ant.
	 * @param position
	 * @return true if there is an ant in specified position, otherwise, false.
	 */
	private boolean isThisPositionHasAnt(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.hasAnt();
		}
		return false;
	}

	/**
	 * Get an ant in the specific position.
	 * @param position
	 * @return an ant, otherwise, null object
	 */
	private Ant getThisAntAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.getAnt();
		}
		return null;
	}

	/**
	 * Get the position of an ant based on their ID.
	 * @param antID
	 * @return Position of an ant.
	 */
	private Position lookForAntID(int antID) {
		if (ants.get(antID) != null) {
			return ants.get(antID).getPosition();
		}
		return null;
	}

	/**
	 * Remove an ant in specified position.
	 * @param position
	 */
	private void removeAntAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.removeAnt();
		}
	}

	/**
	 * Turn an ant to food and recount the number of food in the cell.
	 * @param position
	 */
	private void killAntAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			if (cell.hasAnt()) {
				Ant ant = cell.getAnt();
				antMoveTo(ant, new Position(-1, -1));
				ants.set(ant.getId(), null);
				cell.removeAnt();
			}
		}
	}

	/**
	 * Move ant to the next posible position.
	 * @param ant
	 * @param position
	 */
	private void antMoveTo(Ant ant, Position position) {
		// TODO Auto-generated method stub
		Cell cell = getCells(position);
		if (cell != null) {
			cell.setAnt(ant);
		}
	}

	/**
	 * @param position
	 * @return int of food in specific cell
	 */
	public int getFoodAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.getFoodCount();
		}
		return -1;
	}

	/**
	 * Set number of food in the cell.
	 * @param foodNumber
	 * @param position
	 */
	private void setFoodAtPosition(int foodNumber, Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.setFoodCount(foodNumber);
			markPositionChanged(position);

		}
	}

	/**
	 * @param position
	 * @return true, if there is a rock in the cell.
	 */
	private boolean isRockyAtPosition(Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.isRocky();
		}
		return false;
	}

	/**
	 * @param colour
	 * @param position
	 * @return true if the ant hill is home, false if foe.
	 */
	private boolean isAntHillAtPosition(AntColor colour, Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.isAntHill(colour);
		}
		return false;
	}

	/**
	 * Set marker for specific cell.
	 * @param colour
	 * @param position
	 * @param markerNum
	 */
	private void setMarkerAtPosition(AntColor colour, Position position,
			int markerNum) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.setMarker(colour, markerNum);
			markPositionChanged(position);
		}
	}

	/**
	 * Remove marker at specific position if ant has the same colour as the marker.
	 * @param colour
	 * @param position
	 * @param markerNum
	 */
	private void removeMarkerAtPosition(AntColor colour, Position position,
			int markerNum) {
		Cell cell = getCells(position);
		if (cell != null) {
			cell.clearMarker(colour, markerNum);
			markPositionChanged(position);
		}
	}

	/**
	 * @param colour
	 * @param position
	 * @param markerNum
	 * @return true, if the colour of the marker is from friend, false if foe marker.
	 */
	private boolean checkMarkerAtPosition(AntColor colour, Position position,
			int markerNum) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.checkMarker(colour, markerNum);
		}
		return false;
	}

	/**
	 * @param colour
	 * @param position
	 * @return true, if the colour of the marker is from friend, false if foe marker.
	 */
	private boolean checkMarkerAtPosition(AntColor colour, Position position) {
		Cell cell = getCells(position);
		if (cell != null) {
			return cell.checkMarker(colour);
		}
		return false;
	}

	/**
	 * @param colour
	 * @param position
	 * @return integer of adjacent cell
	 */
	private int checkNumAdjacentAnts(AntColor colour, Position position) {
		int adjAnts = 0;
		// 6 direction
		for (int i = 0; i < 6; i++) {
			Position neighbour = position.adjacent_cell(i);
			if ((isThisPositionHasAnt(neighbour))
					&& (getThisAntAtPosition(neighbour).isColour() == colour)) {
				adjAnts++;// adjAnts +=1
			}
		}
		return adjAnts;
	}

	/**
	 * Set to kill the ant.
	 * @param position
	 */
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

	/**
	 * Check if an ant is surrounded by 5-6 foes
	 * @param position
	 */
	private void checkForSurroundedAnts(Position position) {
		checkForSurroundingAntAndKill(position);
		for (int i = 0; i < 6; i++) {// direction
			Position adjAnt = position.adjacent_cell(i);
			checkForSurroundingAntAndKill(adjAnt);
		}
	}

	/**
	 * @param colour
	 * @param state
	 * @return next Action available in the ant brain. 
	 */
	private Action getInstruction(AntColor colour, int state) {
		AntBrain brain = null;
		if (colour == AntColor.Red) {
			brain = this.brains[0];

		} else {
			brain = this.brains[1];
		}
		return brain.getInstruction(state);
	}

	/**
	 * Record the movement of an ant and the position.
	 * @param ant
	 * @param position
	 */
	private void logMovement(Ant ant, Position position) {
		this.antMoves.add(new Move(ant, position));

	}

	/**
	 * @param antHillColour
	 * @return the number of food in the ant hill.
	 */
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

	/**
	 * @param colour
	 * @return the number of alive ants.
	 */
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

	/**
	 * @return the winner colony based on the number of food they have collected.
	 */
	public Colony getTheWinner() {
		int black = countScore(AntColor.Black);
		int red = countScore(AntColor.Red);
		if (black > red) {
			return this.blackTeam; // black is the winner
		} else if (red > black) {
			return this.redTeam; // red is the winner
		} else {
			return null; // tie
		}
	}

	/**
	 * Run the step() method. By default 300,000.
	 * @param numSteps
	 */
	public void run(int numSteps) {
		int steps = numSteps;
		while (steps > 0) {
			round();
		}
	}

	/**
	 * Record statistics every 10 rounds.
	 */
	private void round() {
		// TODO Auto-generated method stub
		this.rounds += 1;
		if (this.rounds % 10 == 0) {
			setStatistics();
		}
		for (int i = 0; i < this.ants.size(); i++) {
			step(i);
		}
	}

	/* 
	 * Print the cells[][]
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String print = "";
		for (Cell[] row : this.cells) {
			for (Cell col : row) {
				print += col + "\n";
			}
		}
		return print;
	}

	/**
	 * Writing what was happen for each round. Record it in a file.
	 * @param numSteps
	 * @param file
	 */
	private void run(int numSteps, String file) {
		BufferedWriter writeOut = null;
		try {
			int r = 0;
			writeOut = new BufferedWriter(new FileWriter(file));
			while (r < numSteps) {
				round();
				writeOut.write("Round number: " + r + "\n" + toString());
				r++;
			}
			writeOut.close();
		} catch (Exception e) {
			System.err.println("Cannot write to " + file + " : "
					+ e.getMessage());
			try {
				if (writeOut != null) {
					writeOut.close();
				}
			} catch (Exception ie) {
				System.err.println("File cannot open nor close. "
						+ ie.getMessage());
			}
		} finally {
			try {
				if (writeOut != null) {
					writeOut.close();
				}
			} catch (Exception oe) {
				oe.printStackTrace();
			}
		}
	}
}
