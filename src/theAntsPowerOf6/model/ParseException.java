package theAntsPowerOf6.model;

public class ParseException extends Exception{
	String title;
	String error;
	
	public ParseException(String title, String error){
		super();
		this.title = title;
		this.error = error;
	}

	@Override
	public String toString() {
		return "ParseException [title=" + title + ", error=" + error + "]";
	}
	
	
}
