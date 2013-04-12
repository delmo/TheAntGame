package theAntsPowerOf6.model;


public class Colony {
	
	private String colonyName;
	private AntBrain colonyBrain;
	private AntColor conlonyColor;
	
	public Colony(String name, AntBrain brain, AntColor color){
		this.colonyName = name;
		this.colonyBrain = brain;
		this.conlonyColor = color;
	}

	public String getColonyName() {
		return colonyName;
	}

	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}

	public AntBrain getColonyBrain() {
		return colonyBrain;
	}

	public void setColonyBrain(AntBrain colonyBrain) {
		this.colonyBrain = colonyBrain;
	}

	public AntColor getConlonyColor() {
		return conlonyColor;
	}

	public void setConlonyColor(AntColor conlonyColor) {
		this.conlonyColor = conlonyColor;
	}
	
	@Override
	public String toString() {
		return "Colony [colonyName=" + colonyName + ", colonyBrain="
				+ colonyBrain + ", conlonyColor=" + conlonyColor + "]";
	}
}
