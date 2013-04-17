package theAntsPowerOf6.model;


/**
 * This class is used to hold the name of the colony, its ant brain and colour.
 * @author Tristan
 * @version 0.01
 */
public class Colony {
	
	private String colonyName;
	private AntBrain colonyBrain;
	private AntColor conlonyColor;
	
	
	/**
	 * Constructor for taking name of colony, the AntBrain and colour.
	 * @param name
	 * @param brain
	 * @param color
	 */
	public Colony(String name, AntBrain brain, AntColor color){
		this.colonyName = name;
		this.colonyBrain = brain;
		this.conlonyColor = color;
	}

	/**
	 * @return the name of the colony.
	 */
	public String getColonyName() {
		return colonyName;
	}

	/**
	 * Change the name of the colony.
	 * @param colonyName
	 */
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}

	/**
	 * @return AntBrain of the colony.
	 */
	public AntBrain getColonyBrain() {
		return colonyBrain;
	}

	/**
	 * Change the AntBrain. This is useful in every match.
	 * @param colonyBrain
	 */
	public void setColonyBrain(AntBrain colonyBrain) {
		this.colonyBrain = colonyBrain;
	}

	/**
	 * @return the colony colour.
	 */
	public AntColor getConlonyColor() {
		return conlonyColor;
	}

	/**
	 * Set colony colour
	 * @param conlonyColor
	 */
	public void setConlonyColor(AntColor conlonyColor) {
		this.conlonyColor = conlonyColor;
	}
	
	/* 
	 * Print the status of the Colony.
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Colony [colonyName=" + colonyName + ", colonyBrain="
				+ colonyBrain + ", conlonyColor=" + conlonyColor + "]";
	}
}
