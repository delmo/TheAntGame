package theAntsPowerOf6.model;

public class Position {

	int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// where to turn left or right
	//pseudo code is in the course website 
	public int toTurn(Turn pointedWhere, int dir) {
		int value;
		if (pointedWhere == Turn.Left) { //turn to left
			value = (dir + 5) % 6;
			return value;
		} else {// turn to right
			value = (dir + 1) % 6;
			return value;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

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
			return new Position(this.x + 1, this.y);
		}
		if (dir == 1) {
			return new Position(this.x + (this.y % 2 == 0 ? 0 : 1), this.y + 1);
		}
		if (dir == 2) {
			return new Position(this.x + (this.y % 2 == 0 ? -1 : 0), this.y + 1);
		}
		if (dir == 3) {
			return new Position(this.x - 1, this.y);
		}
		if (dir == 4) {
			return new Position(this.x + (this.y % 2 == 0 ? -1 : 0), this.y - 1);
		}
		if (dir == 5) {
			return new Position(this.x + (this.y % 2 == 0 ? 0 : 1), this.y - 1);
		} else {
			return this;
		}
	}
}
