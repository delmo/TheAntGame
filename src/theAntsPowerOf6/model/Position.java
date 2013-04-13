package theAntsPowerOf6.model;


/**
 * @author Tristan
 * @version 0.02
 */
public class Position {

	int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return 27*x+13*y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	// where to turn left or right
	//pseudo code is in the course website 
	public static int toTurn(Turn pointedWhere, int dir) {
		int value;
		if (pointedWhere == Turn.Left) { //turn to left
			value = (dir + 5) % 6;
			return value;
		} else {// turn to right
			value = (dir + 1) % 6;
			return value;
		}
	}

	/*
	 * Get x position
	 */
	public int getX() {
		return x;
	}

	/*
	 * Set x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * Get y position
	 */
	public int getY() {
		return y;
	}

	/*
	 * Set y position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getX() + "," + getY();
	}

	/*
	 * Please review this method. pseudo code is in the course website 
	 */
	public Position sensed_cell(int dir, Direction direction) {
		switch (direction) {
		case Here:
			return this;
		case Ahead:
			return adjacent_cell(dir);
		case LeftAhead:
			return adjacent_cell(toTurn(Turn.Left, dir));
		case RightAhead:
			return adjacent_cell(toTurn(Turn.Right, dir));
		default:
			return this;
		}
	}

	/* pseudo code is in the course website 
	 * function adjacent_cell(p:pos, d:dir):
	 * pos = let (x,y) = p in 
	 * switch d of
	 * case 0: (x+1, y) 
	 * case 1: if even then (x, y+1) else (x+1, y+1) 
	 * case 2: if even then (x-1, y+1) else (x, y+1) 
	 * case 3: (x-1, y) case 4: if even then(x-1, y-1) else (x, y-1) 
	 * case 5: if even then (x, y-1) else (x+1, y-1)
	 */
	public Position adjacent_cell(int dir) {
		if (dir == 0) {
			return new Position(x + 1, y);
		}
		if (dir == 1) {
			return new Position(x + ((y % 2 == 0) ? 0 : 1), y + 1);
		}
		if (dir == 2) {
			return new Position(x + ((y % 2 == 0) ? -1 : 0), y + 1);
		}
		if (dir == 3) {
			return new Position(x - 1, y);
		}
		if (dir == 4) {
			return new Position(x + ((y % 2 == 0) ? -1 : 0), y - 1);
		}
		if (dir == 5) {
			return new Position(x + ((y % 2 == 0) ? 0 : 1), y - 1);
		} else {
			return this;
		}
	}
}
