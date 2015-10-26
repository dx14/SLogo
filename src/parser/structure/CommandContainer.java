package parser.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
		//System.out.println("Loading command: " + name);
		return myCommands.get(name).clone();
	}
	
	public void addCommand(UserCommandInstance c){
		myCommands.put(c.getName(), c);
	}
	
	public void debug(){
		System.out.println("User Command List:");
		myCommands.keySet().stream()
			.forEach(s -> System.out.printf("%13s -> %n\t%s%n%n", s, myCommands.get(s)));
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}

	@Override
	public List<GUICommand> getCommands() {
		final ArrayList<GUICommand> userCommandList = new ArrayList<>();
		myCommands.values().stream()
			.forEach(c -> userCommandList.add(c));
		return Collections.unmodifiableList(userCommandList);
	}
}
