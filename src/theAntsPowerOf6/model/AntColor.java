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
	
	AntColor getColor(){
		if(value){
			return AntColor.Black;
		}else{
			return AntColor.Red;
		}
	}
}
