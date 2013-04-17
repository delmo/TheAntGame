package theAntsPowerOf6.model;

/**
 * This class is use to move the current position of an ant to other available
 * position in the map.
 * 
 * @author Tristan
 * @version 0.01
 * 
 */
public class Move {
	private Ant ant;
	private boolean isAntDead;
	private Position currentPosition;
	private Position toPosition;
	private int turnWhere;

	/**
	 * Constructor to set the current status of an ant.
	 * @param ant
	 * @param currentPosition
	 */
	public Move(Ant ant, Position currentPosition) {
		this.ant = ant;
		this.currentPosition = currentPosition;
		this.toPosition = ant.getPosition();
		this.isAntDead = ((currentPosition.getX() == -1) && (currentPosition
				.getY() == -1));
		this.turnWhere = ant.getDirection();
	}

	/**
	 * @return the ant.
	 */
	public Ant getAnt() {
		return ant;
	}

	/**
	 * @return true if ant is dead. 
	 */
	public boolean isAntDead() {
		return isAntDead;
	}

	/**
	 * @return current position of an ant.
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @return the next position the ant is going.
	 */
	public Position getToPosition() {
		return toPosition;
	}

	/**
	 * @return int on which side the ant will turn.
	 */
	public int getTurnWhere() {
		return turnWhere;
	}
}
