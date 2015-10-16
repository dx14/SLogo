package parser.structure;

import java.util.HashMap;
import java.util.Map;

public class VariableContainer {
	
	private Map<String, Variable> myVariables;
	
	public VariableContainer(){
		myVariables = new HashMap<>();
	}
	
	public void setVariable(String name, double value){
		if(!myVariables.containsKey(name)){
			myVariables.put(name, new Variable(name, value));
		} 
		myVariables.get(name).setValue(value);
		// debug
		myVariables.keySet().stream().forEach(s -> System.out.println(s));
		myVariables.values().stream().forEach(s -> System.out.println(s.getValue()));
	}
	
	public double getVariableValue(String name){
		if(!myVariables.containsKey(name)){
			setVariable(name, 0.0);
		}
		return myVariables.get(name).getValue();
	}
	
	public Variable getVariable(String name){
		if(!myVariables.containsKey(name)){
			setVariable(name, 0.0);
		}
		return myVariables.get(name);
	}

}
