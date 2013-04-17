package theAntsPowerOf6.model;

/**
 * This class extends Exception to parse any error provided by the system.
 * To be more presentable to the user who will play the game, the parse will
 * provide the title of an error and corresponding error messages.
 * @author Tristan
 * @version 0.01
 * 
 *
 */
public class ParseException extends Exception{
	String title;
	String error;
	
	/**
	 * Constructor that sets the title of an error, and corresponding messages.
	 * @param title
	 * @param error
	 */
	public ParseException(String title, String error){
		super();
		this.title = title;
		this.error = error;
	}

	/* 
	 * return String representation of an error.
	 * (non-Javadoc)
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return "ParseException [title=" + title + ", error=" + error + "]";
	}
	
	
}
