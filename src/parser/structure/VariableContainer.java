package parser.structure;

import java.util.HashMap;
import java.util.Map;

import parser.command.Evaluable;

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
	
	public Variable getVariable(Evaluable var){
		return getVariable(var.getCommandElement().getRawText());
	}
	
	public void debug(){
		System.out.println("User Variable List:");
		myVariables.keySet().stream().forEach(s -> System.out.printf("%13s = %f %n", s, myVariables.get(s).getValue()));
	}

}
