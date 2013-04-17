package theAntsPowerOf6.model;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * This class is use to generate an map and read from a file.
 * @author Tristan
 *
 */
public class Map {

	private char[][] map;
	private String name;
	//private BufferedReader reader = null;
	private int width;
	private int height;

	/**
	 * Constructor which takes name of the map, height and width.
	 * @param name
	 * @param width
	 * @param height
	 */
	public Map(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		
		map = new char[width][height];
		//fill top row with '#'
		for (int x = 0; x < width; x++) {
			map[0][x] = '#'; 
		}	
			for (int y = 1; y < height - 1; y++) {
				map[y][0] = '#';
				for(int x = 1; x < width - 1; x++){
					map[y][x] = '.';
			}
			map[y][width - 1] = '#';
		}
		//fill bottom row
		for (int x = 0; x < width; x++) {
			map[height - 1][x] = '#';
		}
	}

	/**
	 * Another constructor which take the name of the map and String filename.
	 * @param file
	 * @param name
	 */
	public Map(String file, String name) {
		this.name = name;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			width = new Integer(reader.readLine()); // first line
			height = new Integer(reader.readLine()); // second line
			map = new char[height][width];
			String toRead = null; // third and rest of lines
			for (int y = 0; y < height; y++) {
				toRead = reader.readLine();
				if (y % 2 == 1) {
					// move on column forward
					toRead = toRead.substring(1, toRead.length());
				}
				for (int x = 0; x < width; x++) {
					char rowcol = toRead.charAt(x*2); 
					//System.out.print(rowcol);
					if (!((rowcol == '#') || (rowcol == '.')
							|| (rowcol == '+') || (rowcol == '-'))) {
						try{
							Integer.parseInt(""+rowcol);
						}catch(NumberFormatException nfe){
							throw new ParseException("Number format not recognized: ", "" + rowcol);
						}
					}
					if((x>0) && (toRead.charAt(x*2-1) != ' ')){ 
						throw new ParseException("Character not recognized: ", "" + toRead.charAt(x-1));
					}
					map[y][x] = rowcol;
				}				
			}
		} catch (Exception e) {
			System.out.println("Cannot read the map file: " + e.getMessage());
		}finally{
			try{
				if(reader != null){
					reader.close();
				}
			}catch(Exception e){
				System.out.println("Cannot close the map file: " + e.getMessage());
			}
		}
	}
	
	/**
	 * @return width of the current map.
	 */
	public int getWidth() {
		return width;
	}
	

	/**
	 * @return height of the current map.
	 */
	public int getHeight() {
		return height;
	}
	
	/* 
	 * Print the map on the console.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		String print = "";
		print += width + "\n";
		print += height + "\n";
		for (int y = 0; y < map.length; y++)
		{
			char[] row = map[y];
			//print space with it is odd
			if (y % 2 == 1)
			{
				print += " ";
			}
			for (int x = 0; x < row.length; x++)
			{
				//print row
				print += row[x] + " ";
			}
			print += "\n";
		}
		return print;
	}

	/**
	 * @param x
	 * @param y
	 * @return the character in specific map location.
	 */
	public char getWhoIsInMap(int x, int y) {
		// TODO Auto-generated method stub
		return this.map[y][x];
	}
	
	/**
	 * @return the current map.
	 */
	public Map getMap(){
		Map themap = new Map(this.name, this.width, this.height);
		for(int y=0; y< this.width; y++){
			for(int x=0; x<this.height; x++){
				themap.map[y][x] = this.map[y][x];
			}
		}
		return themap;
	}
	
	/**
	 * Create random map.
	 */
	public void createRandomMap(){
		//create clear map
		map = new char[width][height];
		//fill top row with '#'
		for (int x = 0; x < width; x++) {
			map[0][x] = '#'; 
		}	
			for (int y = 1; y < height - 1; y++) {
				map[y][0] = '#';
				for(int x = 1; x < width - 1; x++){
					map[y][x] = '.';
			}
			map[y][width - 1] = '#';
		}
		//fill bottom row
		for (int x = 0; x < width; x++) {
			map[height - 1][x] = '#';
		}
		
		//create rocks
		
	}//method
}
