package parser.structure;

import java.util.HashMap;
import java.util.Map;

import parser.ParserException;
import parser.command.commandlist.UserCommandInstance;

public class CommandContainer {
	
	private Map<String, UserCommandInstance> myCommands;
	
	public CommandContainer() {
		myCommands = new HashMap<>();
	}
	
	/**
	 * Returns copy of this command instance so that a new tree and such can be assigned each time.
	 * @param name
	 * @return
	 * @throws ParserException
	 */
	public UserCommandInstance getCommandInstance(String name) throws ParserException{
		if(!myCommands.containsKey(name)){
			throw new ParserException("Error: don't know how to " + name);
		}
		System.out.println("Loading command: " + name);
		return myCommands.get(name).clone();
	}
	
	public void addCommand(UserCommandInstance c){
		myCommands.put(c.getName(), c);
		System.out.println("put " + c.getName());
	}
}
