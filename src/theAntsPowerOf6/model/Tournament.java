package theAntsPowerOf6.model;

import java.util.ArrayList;

public class Tournament {
	private ArrayList<Match> matches;
	private ArrayList<Map> maps;
	private ArrayList<Colony> colonies;
	private ArrayList<Integer> scoreManager;
	private Match match;
	
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
