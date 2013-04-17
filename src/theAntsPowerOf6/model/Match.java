package theAntsPowerOf6.model;

/**
 * This class is used to hold information about the two competing colonies,
 * their colours and the map they will play.
 * 
 * @author Tristan
 * @version 0.01
 * 
 */
public class Match {
	private Colony one;
	private Colony two;
	private Map map;
	private World world;

	/**
	 * This constructor initialize the two competing colonies.
	 * @param map
	 * @param one
	 * @param two
	 */
	public Match(Map map, Colony one, Colony two) {
		this.map = map;
		this.one = one;
		this.two = two;
		world = null;
	}

	/**
	 * @return the friend colony.
	 */
	public Colony getOne() {
		return one;
	}

	/**
	 * @return the foe colony
	 */
	public Colony getTwo() {
		return two;
	}

	/**
	 * @return the map they are competing.
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @return the world they are competing.
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * This method is used to assign the match in specified world.
	 * @return the world they've assigned.
	 */
	public World setWorldWithColonies() {
		if (world == null) {
			world = new World(one, two, map);
		}
		return world;
	}
}
