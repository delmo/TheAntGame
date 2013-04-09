package theAntsPowerOf6.model;
/**
 * 
 * @author Tristan
 * @version 0.01
 *
 */
public enum AntColor {
	Red(true), Black(false);
	private boolean value;
	
	
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

	public AntColor getFoeColor() {
		if(value){
			return Black;
		}else{
			return Red;
		}
	}
}
