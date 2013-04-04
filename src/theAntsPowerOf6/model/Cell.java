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


	
	


	
	public void setMarker(AntColor color, int marker)
	{
		if (color == AntColor.Black)
		{
			markBlack |= Marker.get(marker).getValue();
		} else
		{
			markRed |= Marker.get(marker).getValue();
		}
	}


	
	public void clearMarker(AntColor color, int marker)
	{
		if (color == AntColor.Black)
		{
			markBlack = markBlack & ~Marker.get(marker).getValue();
		} else
		{
			markRed = markRed & ~Marker.get(marker).getValue();
		}
	}


	
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
	
	
	public boolean isRocky()
	{
		return isRocky;
	}
		
	
	public boolean checkMarker()
	{
		return (markBlack > 0) || (markRed > 0);
	}


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


	
	public int getFoodCount()
	{
		return foodCount;
	}


	
	public void setFoodCount(int foodCount)
	{
		this.foodCount = foodCount;
	}
	

	
	public Position getPosition()
	{
		return position;
	}


	
	public boolean hasAnt()
	{
		return ant != null;
	}


	
	public Ant getAnt()
	{
		return ant;
	}


	
	public void setAnt(Ant ant)
	{
		this.ant = ant;
	}


	
	public void removeAnt()
	{
		this.ant = null;
	}

	public void setChanged(boolean isChanged)
	{
		hasChanged = isChanged;
	}


	
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


