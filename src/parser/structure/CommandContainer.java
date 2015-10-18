package parser.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import parser.ParserException;
import parser.command.commandlist.UserCommandInstance;

public class CommandContainer extends Observable implements GUICommandContainer{
	
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
		update();
	}
	
	public void debug(){
		System.out.println("User Command List:");
		myCommands.keySet().stream()
			.forEach(s -> System.out.printf("%13s -> %n\t%s%n%n", s, myCommands.get(s)));
	}
	
	private void update(){
		setChanged();
		notifyObservers();
	}

	@Override
	public Map<String, String> getCommands() {
		// TODO Auto-generated method stub
		return new HashMap<>();
	}
}
