package parser.command.commandlist;

import parser.ParserException;

import java.util.ArrayList;
import java.util.List;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.Evaluable;
import parser.structure.GUICommand;
import parser.structure.LocalVariableContainer;

public class UserCommandInstance extends Command implements Evaluable, GUICommand {

	private List<String> myVariables;
	Evaluable myCommands;
	private String myName;
	
	private LocalVariableContainer myVariableContainer;
	
	private List<UserCommandInstance> myChildren;
	
	public UserCommandInstance(String name, List<String> variables, Evaluable commands){
		myName = name;
		myVariables = variables;
		myCommands = commands;
		myChildren = new ArrayList<>();
	}
	
	public UserCommandInstance clone(){
		UserCommandInstance copy = new UserCommandInstance(myName, myVariables, myCommands);
		myChildren.add(copy);
		return copy;
	}
	
	public String getName(){
		return myName;
	}
	
	@Override
	public double evaluate() throws ParserException {
		System.out.println("EVALUATING " + myName);
		System.out.println(myCommands);
		myVariableContainer = new LocalVariableContainer(myParser.getVariableContainer());
		myParser.setVariableContainer(myVariableContainer);
		for(int i = 0; i<myVariables.size(); i++){
			myParser.getVariableContainer().setVariable(myVariables.get(i), myTree.get(i).evaluate());
		}
		System.out.println("COMMANDS " + myCommands.toString());
		myVariableContainer.debug();
		double result = myCommands.evaluate();
		myParser.setVariableContainer(myVariableContainer.getParent());
		return result;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.getRemainder();
		
		for(int i=0; i<myVariables.size(); i++){
			remainder = myTree.buildNext().getRemainder();
		}
		return remainder;
	}
	
	@Override
	public String toString(){
		return myCommands.toString();
	}
	
	public String toString(boolean useDelimeter){
		return "to " + myName + " [ " + myVariables.stream().reduce("", (s1, s2) -> s1 + " " + s2) + " ] \n" + myCommands.toString(useDelimeter);
	}

	@Override
	public String getInputString() {
		return myName + " " + myVariables.stream().reduce("", (s1, s2) -> s1 + " " + s2);
	}

	@Override
	public String getCommandText() {
		System.out.println(myCommands);
		return myCommands.toString(false);
	}
	
	public void setCommandTree(Evaluable commands){
		myCommands = commands;
		myChildren.stream().forEach((u) -> u.setCommandTree(commands));
	}

}
