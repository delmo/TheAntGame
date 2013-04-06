package theAntsPowerOf6.model;

import theAntsPowerOf6.model.Ant;
import theAntsPowerOf6.model.AntColor;
import theAntsPowerOf6.model.Marker;
import theAntsPowerOf6.model.Position;


/**
 * 
 * @author Tristan
 * @version 0.03
 */
public class Cell
{
	private boolean isRocky;
	private boolean isAntHill;
	private boolean hasChanged;
	private int foodCount;
	private int markBlack;
	private int markRed;
	private Ant ant;
	private Position position;	
	private AntColor hillColor;
	
	/*
	 * 1st Constructor
	 * Set if the cell has one of this: anthill, rock, food.
	 * Set the position of this cell.
	 * If it is anthill, then what colour of the anthill.
	 * If it is food, then how many food it has.
	 */
	public Cell(boolean antHill, boolean isRocky, Position position, AntColor antHillColor, int foodCount)
	{
		ant = null;
		markBlack = 0;
		markRed = 0;
		hasChanged = false;
		this.isAntHill = antHill;
		this.isRocky = isRocky;
		this.position = position;
		this.hillColor = antHillColor;
		this.foodCount = foodCount;	
		
	}

	/*
	 * Another Constructor
	 * THis constructor is for labelling the cell.
	 */
	public Cell(char data, Position position)
	{
		this(false, false, position, AntColor.Black, 0);
		switch (data)
		{
		case '#':
			isRocky = true;
			break;
		case '+':
			hillColor = AntColor.Red;
			isAntHill = true;
			break;
		case '-':
			hillColor = AntColor.Black;
			isAntHill = true;
			break;
		case '.':
			break;
		default:
			try
			{
				foodCount = Integer.parseInt("" + data);
			} 
			catch (NumberFormatException e)
			{
				System.out.println("Cannot position cell --> '" + data + "' at " + position);
			}
			break;
		}

	}

	/*
	 * Set marker.
	 * @param color, color of an ant
	 * @param marker, integer marker
	 */
	public void setMarker(AntColor color, int marker)
	{
		if (color == AntColor.Black)
		{
			markBlack |= Marker.get(marker).getValue(); // 2^marker is the returned value
		} else
		{
			markRed |= Marker.get(marker).getValue();
		}
	}


	/*
	 * Remove the marker.
	 * The marker can only be removed by ant with same colour as marker
	 */
	public void clearMarker(AntColor color, int marker)
	{
		if (color == AntColor.Black)
		{
			//use & bitwise operator and ~ complement operator to make it zero
			//if and only if marker value is equal to markBlack.
			//Otherwise, returned same markBlack value.
			markBlack = markBlack & ~Marker.get(marker).getValue(); 
		} else
		{
			markRed = markRed & ~Marker.get(marker).getValue();
		}
	}


	/*
	 * Check marker & colour
	 */
	public boolean checkMarker(AntColor color, int marker)
	{
		if (color == AntColor.Black)
		{
			return (markBlack & Marker.get(marker).getValue()) > 0;
		} else
		{
			return (markRed & Marker.get(marker).getValue()) > 0;
		}
	}
	
	/*
	 * Check there is a rock in the cell.
	 */
	public boolean isRocky()
	{
		return isRocky;
	}
		
	/*
	 * Check is there a marker in the cell.
	 * Black or Red.
	 */
	public boolean checkMarker()
	{
		return (markBlack > 0) || (markRed > 0);
	}

	/*
	 * Check the colour of the marker.
	 * Return the integer representing the marker.
	 */
	public boolean checkMarker(AntColor color)
	{
		if (color == AntColor.Black)
		{
			return markBlack > 0;
		} else
		{
			return markRed > 0;
		}
	}


	/*
	 * Boolean if anthill sits in this cell.
	 */
	public boolean isAntHillSetIfNot(AntColor color)
	{
		if (isAntHill)
		{
			return hillColor == color;
		} else
		{
			return false;
		}
	}


	/*
	 * Return integer number of food exists on this cell.
	 */
	public int getFoodCount()
	{
		return foodCount;
	}


	/*
	 * Set food count on this cell.
	 * This is useful for a dead ant.
	 */
	public void setFoodCount(int foodCount)
	{
		this.foodCount = foodCount;
	}
	

	/*
	 * Return the position of this cell.
	 */
	public Position getPosition()
	{
		return position;
	}


	/*
	 * Boolean if there is an ant or not.
	 */
	public boolean hasAnt()
	{
		return ant != null;
	}


	/*
	 * Return an ant.
	 */
	public Ant getAnt()
	{
		return ant;
	}


	/*
	 * Set an ant to this cell.
	 */
	public void setAnt(Ant ant)
	{
		this.ant = ant;
	}


	/*
	 * Remove an ant from this cell.
	 */
	public void removeAnt()
	{
		this.ant = null;
	}

	/*
	 * Set true or false to represent if this cell
	 * had changed or not.
	 */
	public void setChanged(boolean isChanged)
	{
		hasChanged = isChanged;
	}


	/*
	 * Check if this cell had changed.
	 */
	public boolean isChanged()
	{
		return hasChanged;
	}

	
	@Override
	public String toString()
	{
		String out = "cell(" + position.getX() + ", " + position.getY() + "): ";
		if (isRocky)
		{
			return out + "rock";
		}
		if (foodCount > 0)
		{
			out += foodCount + " food;";
		}
		if (isAntHill)
		{
			out += hillColor + " hill; ";
		}
		if (markRed > 0)
		{
			out += "red marks:";
			for (int i = 0; i < 6; i++)
			{
				if (checkMarker(AntColor.Red, i))
				{
					out += i;
				}
			}
			out += "; ";
		}
		if (markBlack > 0)
		{
			out += "black marks:";
			for (int i = 0; i < 6; i++)
			{
				if (checkMarker(AntColor.Black, i))
				{
					out += i;
				}
			}
			out += "; ";
		}
		if (ant != null)
		{
			out += ant;
		}
		return out;
	}
}


