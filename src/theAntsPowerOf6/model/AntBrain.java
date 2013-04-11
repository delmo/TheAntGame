package theAntsPowerOf6.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author Tristan
 * @version 0.02
 */
/**
 * @author Rhayan
 *
 */
public class AntBrain {
	private String name;
	public String getName() {
		return name;
	}

	private Action[] states;
	
	
	/**
	 * 
	 * @param name, instruction
	 * @param file, AntBrain file name
	 */
	public AntBrain(String name, String file){
		BufferedReader reader = null;
		ArrayList<Action> actions = new ArrayList<Action>();
		this.name = name;
		try{
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null){
				line = line.split(";")[0]; // remove the comment after ;
				if(line.length() > 0){
					actions.add(new Action(line));
				}else{
					actions.add(null);
				}
			}
		}catch(Exception e){
			//edit this line, check runtime error for reading file
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
		//put all actions in states[]
		int size = actions.size();
		states = new Action[size];
		for(int i=0; i<size; i++){
			states[i] = actions.get(i);
		}
	}
	
	
	/**
	 * @return size of states[]
	 */
	public int getStateSize(){
		return states.length;
	}
	
	
	/**
	 * @return all remaining states
	 */
	public Action[] getStates(){
		return this.states;
	}

	
	/**
	 * @return current instruction name;
	 */
	public Action getInstruction(int state){
		if((state >= this.states.length) || (state < 0)){
			return null;
		}else{
			return this.states[state];
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(states, other.states))
			return false;
		return true;
	}

	
}
