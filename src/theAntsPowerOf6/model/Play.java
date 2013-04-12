package theAntsPowerOf6.model;

import java.util.ArrayList;

public class Play {

	public void startPlaying(ArrayList<Map> maps, ArrayList<Colony> colonies, boolean playable){
		Tournament tournament;
		if(playable){
			tournament = new Tournament(maps, colonies);
			//if(there is map left){
			//	play
			//}
		}
	}
}
