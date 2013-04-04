package theAntsPowerOf6.model;

import java.util.ArrayList;

public class World {
	
	//world is empty? what happen
	private Team blackTeam;
	private Team redTeam;
	private int width;
	private int height;
	private int turnCounter;
	private ArrayList<Ant> ants;
	private ArrayList<Move> antMoves;
	private ArrayList<int[]> statistics;
	private Cell[][] cells;
	private AntBrain[] brains;
	
}
