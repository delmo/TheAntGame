package theAntsPowerOf6.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


public class AntBrain {
	private String instructionName;
	private Action[] states;
	
	public AntBrain(String name, String file){
		BufferedReader reader = null;
		ArrayList<Action> actions = new ArrayList<Action>();
		this.instructionName = name;
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null){
				line = line.split(";")[0];
				if(line.length() > 0){
					actions.add(new Action(line));
				}else{
					actions.add(null);
				}
			}
		}catch(Exception e){
			System.out.println("File not accessible: " + e.getMessage());
		}finally{
			try{
				if(reader != null){
					reader.close();
				}
			}catch(Exception c){
				System.out.println("Cannot close the file: " + c.getMessage());
			}
		}
		int size = actions.size();
		states = new Action[size];
		for(int i=0; i<size; i++){
			states[i] = actions.get(i);
		}
	}
	
	public int getStateSize(){
		return states.length;
	}
	
	public Action[] getStates(){
		return states;
	}

	public String getInstruction(){
		return instructionName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instructionName == null) ? 0 : instructionName.hashCode());
		result = prime * result + Arrays.hashCode(states);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AntBrain))
			return false;
		AntBrain other = (AntBrain) obj;
		if (instructionName == null) {
			if (other.instructionName != null)
				return false;
		} else if (!instructionName.equals(other.instructionName))
			return false;
		if (!Arrays.equals(states, other.states))
			return false;
		return true;
	}

	
}
