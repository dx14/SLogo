package parser.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.command.Evaluable;
import util.GUIVariable;

public class LocalVariableContainer extends VariableContainer {

	private VariableContainer myParent;
	private Map<String, Variable> myVariables;
	
	public LocalVariableContainer(VariableContainer parent){
		myVariables = new HashMap<>();
		myParent = parent;
	}
	
	public double getVariableValue(String name){
		if(!myVariables.containsKey(name)){
			if(myParent.contains(name)){
				return myParent.getVariableValue(name);
			}
			setVariable(name, 0.0);
		}
		return myVariables.get(name).getValue();
	}
	
	public Variable getVariable(String name){
		if(!myVariables.containsKey(name)){
			if(myParent.contains(name)){
				return myParent.getVariable(name);
			}
			setVariable(name, 0.0);
		}
		return myVariables.get(name);
	}
	
	public boolean contains(String name){
		return (myVariables.containsKey(name)||myParent.contains(name));
	}
	
	public void debug(){
		System.out.println("Local User Variable List:");
		myVariables.keySet().stream().forEach(s -> System.out.printf("%13s = %f %n", s, myVariables.get(s).getValue()));
		myParent.debug();
	}
}
