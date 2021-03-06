package theAntsPowerOf6.model;

/**
 * This class holds the status of an ant.
 * @author Tristan
 * @version 0.02
 *
 */
public class Ant {
	int id;
	AntColor colour;	
	int resting;
	int direction;
	boolean hasFood;
	boolean ant_is_alive;
	int state;	
	Position position;
	//World world;
	
	/*
	 * Ant Constructor which initialize the unique ID of an ant, colony colour and position.
	 * Set individual id
	 * Set the clan 
	 * Set the position
	 */
	public Ant(int id, AntColor antcolour, Position position){
		this.id = id;
		this.colour = antcolour;
		this.position = position;
		this.resting = 0;
		this.state = 0;
		this.direction = 0;
		this.hasFood = false;
		this.ant_is_alive = true;
	}
	
	/*
	 * get state of an ant.
	 */
	public int getState() {
		return state;
	}

	/*
	 * set state
	 */
	public void setState(int state) {
		this.state = state;
	}	

	/*
	 * get unique ID of an ant.
	 */
	public int getId() {
		return id;
	}
	

	/* 
	 * Check colour of an ant.
	 */
	public AntColor isColour() {
		return this.colour;
	}

		
	/*
	 * get the resting counter
	 */
	public int getResting() {
		return resting;
	}

	//rest for 14 rounds after each move instruction
	public void setResting() {
		this.resting = 14;
	}
	
	/*
	 * decrement resting counter for each ant
	 */
	public void decResting(){
		this.resting -= 1;
	}

	/*
	 * get current direction facing
	 */
	public int getDirection() {
		return direction;
	}

	//I think we can use Position class rather than doing in this class
	public void setDirection(int direction) {
		this.direction = direction;
	}
	

	/*
	 * Check if ant has food
	 */
	public boolean hasfood() {
		return hasFood;
	}

	/*
	 * Can be called when drop food and collect food
	 */
	public void setHasfood(boolean has_food) {
		this.hasFood = has_food;
	}
	
	
	//throw it away if not useful...
	public void step(int id) {

		for (int j = 0; j < id; j++) {
			if (ant_is_alive == false) {
				j++;
			} else {
				//sense();
			}
		}
	}

	/*
	 * Move ant to different position
	 */
	public void move(Position newPosition) {
		this.position = newPosition;		
	}
	
	/*
	 * Get position
	 */
	public Position getPosition(){
		return position;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ant [id=" + id + ", colour=" + colour + ", resting=" + resting
				+ ", direction=" + direction + ", hasFood=" + hasFood
				+ ", state=" + state + "]";
	}

	
}
