package theAntsPowerOf6.model;

import java.util.ArrayList;

public class World {
	
	//world is empty? what happen
	private Colony blackTeam;
	private Colony redTeam;
	private int width;
	private int height;
	private int turnCounter;
	private ArrayList<Ant> ants;
	private ArrayList<Move> antMoves;
	private ArrayList<int[]> statistics;
	private Cell[][] cells;
	private AntBrain[] brains;
	
	public World(Colony black, Colony red, Map map){
		this.blackTeam = black;
		this.redTeam = red;
		
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
		return turnCounter;
	}

	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
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
		return statistics;
	}

	public void setStatistics(ArrayList<int[]> statistics) {
		this.statistics = statistics;
	}

	public Cell[][] getCells() {
		return cells;
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
	
	public void setMap(Map map){
		//unimplemented...
	}
}
