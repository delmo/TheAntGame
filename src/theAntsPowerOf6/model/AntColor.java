package theAntsPowerOf6.model;
/**
 * This class is used to compare the colour of an ant and its colony.
 * @author Tristan
 * @version 0.01
 *
 */
public enum AntColor {
	Red(true), Black(false);
	private boolean value;
	
	
	/**
	 * Constructor to set the colour. True for Red and False for Black.
	 * @param val
	 */
	AntColor(boolean val){
		this.value = val;
	}
	
	/*
	 * Check the colour for ant or anthill
	 */
	AntColor getColor(){
		if(value){
			return Red;
		}else{
			return Black;
		}
	}

	/**
	 * Check foe colour.
	 * @return foe colour
	 */
	public AntColor getFoeColor() {
		if(value){
			return Black;
		}else{
			return Red;
		}
	}
}
