package theAntsPowerOf6.model;

public class Run {
	
	static Colony redcolony;
	static Colony blackcolony;
	static AntBrain redBrain;
	static AntBrain blackBrain;
	static Map map;
	static World world;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//System.out.println(10%6);		
		//System.out.println(Integer.toBinaryString(Marker.Five.getValue()));
		//Map map = new Map("sample.world", "sample");
		//Map map = new Map("sample map", 150, 150);
		//System.out.println(map.toString());
		setup();	

	}

	private static void setup(){
		redBrain = new AntBrain("red brain", "clever1.brain");
		blackBrain = new AntBrain("black brain", "clever2.brain");
		redcolony = new Colony("Red team", redBrain, AntColor.Red);
		blackcolony = new Colony("Black team", blackBrain, AntColor.Black);
		map = new Map("sample.world", "current game map");
		//System.out.println(map.toString());
		world = new World(blackcolony, redcolony, map);
//		for(Ant ant: world.getAnts()){
//			System.out.println(ant.getId() + ":" + ant.isColour());	
//		}
		world.run(1000);
		//world.getTheWinner().getColonyName();
	}
}
