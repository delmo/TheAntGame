package theAntsPowerOf6.model;

public class Move {
	private Ant ant;
	private boolean isAntDead;
	private Position currentPosition;
	private Position toPosition;
	private int turnWhere;
	
	public Move(Ant ant, Position currentPosition){
		this.ant = ant;
		this.currentPosition = currentPosition;
		this.toPosition = ant.getPosition();
		this.isAntDead = ((currentPosition.getX() == -1) && (currentPosition.getY() == -1));
		this.turnWhere = ant.getDirection();
	}

	public Ant getAnt() {
		return ant;
	}

	public boolean isAntDead() {
		return isAntDead;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public Position getToPosition() {
		return toPosition;
	}

	public int getTurnWhere() {
		return turnWhere;
	}	
}
