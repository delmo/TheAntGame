package theAntsPowerOf6.model;

import java.io.BufferedReader;
import java.io.FileReader;

public class Map {

	private char[][] map;
	private String name;
	private BufferedReader reader;
	private int width;
	private int height;

	public Map(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
		
		map = new char[width][height];
		//fill top row with '#'
		for (int x = 0; x < width; x++) {
			map[0][x] = '#'; 
		}	
			for (int y = 1; y < height -1; y++) {
				map[y][0] = '#';
				for(int x = 1; x < width; x++){
					map[x][y] = '.';
			}
			map[y][width - 1] = '#';
		}
		//fill bottom row
		for (int x = 0; x < width; x++) {
			map[height - 1][x] = '#';
		}
	}

	public Map(String file, String name) {
		this.name = name;
		try {
			reader = new BufferedReader(new FileReader(file));
			width = new Integer(reader.readLine()); // first line
			height = new Integer(reader.readLine()); // second line
			map = new char[width][height];
			String toRead = null; // third and rest of lines
			for (int row = 0; row < height; row++) {
				toRead = reader.readLine();
//				if (row % 2 == 1) {
//					// move on column forward
//					toRead = toRead.substring(1, toRead.length());
//				}
				for (int col = 0; col < width; col++) {
					char rowcol = toRead.charAt(col*2); 
					//System.out.print(rowcol);
					if (!((rowcol == '#') || (rowcol == '.')
							|| (rowcol == '+') || (rowcol == '-'))) {
						try{
							Integer.parseInt(""+rowcol);
						}catch(NumberFormatException nfe){
							throw new Exception("Number format not recognized: " + rowcol);
						}
					}
					if((col>0) && (toRead.charAt(col*2-1) != ' ')){ 
						throw new Exception("Character not recognized: " + toRead.charAt(col-1));
					}
					map[row][col] = rowcol;
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
	
	public int getWidth() {
		return width;
	}
	

	public int getHeight() {
		return height;
	}
	

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
}
