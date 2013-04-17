package theAntsPowerOf6.model;

import java.util.ArrayList;

/**
 * This class is used to serve as the data manager for all matches.
 * @author Tristan
 * @version 0.01
 *
 */
public class Tournament {
	private ArrayList<Match> matches;
	private ArrayList<Map> maps;
	private ArrayList<Colony> colonies;
	private ArrayList<Integer> scoreManager;
	private Match match;
	
	
	/**
	 * Constructor to setup all maps needed in the game and all competing teams/colonies.
	 * @param maps
	 * @param colonies
	 */
	public Tournament(ArrayList<Map> maps, ArrayList<Colony> colonies){
		this.maps = maps;
		this.colonies = colonies;
		scoreManager = new ArrayList<Integer>();
		for(int i=0; i<colonies.size(); i++){
			scoreManager.add(0);
		}
		
		match = null;
		matches = new ArrayList<Match>();
		for(int i=0; i<colonies.size(); i++){
			for(int j=i+1; i<colonies.size(); j++){
				for(Map map : maps){
					matches.add(new Match(map, colonies.get(i), colonies.get(j)));
				}
			}
		}
	}
}
