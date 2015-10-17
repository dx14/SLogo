package parser.command;

import java.util.HashMap;
import java.util.Map;

import parser.ParserException;

public class CommandContainer {
	
	private Map<String, Evaluable> myCommands;
	
	public CommandContainer() {
		myCommands = new HashMap<>();
	}
	
	public Evaluable getCommand(String name) throws ParserException{
		if(!myCommands.containsKey(name)){
			throw new ParserException("Error: don't know how to " + name);
		}
		return myCommands.get(name);
	}
	
	public void addCommand(String name, Evaluable c){
		myCommands.put(name, c);
	}
}
