package theAntsPowerOf6.model;

public class Match {
	private Colony one;
	private Colony two;
	private Map map;
	private World world;
	
	public Match(Map map, Colony one, Colony two){
		this.map = map;
		this.one = one;
		this.two = two;
		world = null;
	}
	
	public Colony getOne() {
		return one;
	}

	public Colony getTwo() {
		return two;
	}

	public Map getMap() {
		return map;
	}

	
	public World getWorld() {
		return world;
	}

	public World setWorldWithColonies(){
		if(world == null){
			world = new World(one, two, map);
		}
		return world;
	}
}
