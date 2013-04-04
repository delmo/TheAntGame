package theAntsPowerOf6.model;

public enum Marker
{
	One(1 << 0), Two(1 << 1), Three(1 << 2), Four(1 << 3), Five(1 << 4), Six(1 << 5);

	private int value;



	Marker(int value)
	{
		this.value = value;
	}

	
	
	public int getValue()
	{
		return value;
	}


	
	public static Marker get(int index)
	{
		switch (index)
		{
		case 0:
			return One;
		case 1:
			return Two;
		case 2:
			return Three;
		case 3:
			return Four;
		case 4:
			return Five;
		case 5:
			return Six;
		}
		return null;
	}
}

