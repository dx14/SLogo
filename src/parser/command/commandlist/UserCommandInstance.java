package parser.command.commandlist;

import parser.ParserException;
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
	
	public UserCommandInstance(String name, List<String> variables, Evaluable commands){
		myName = name;
		myVariables = variables;
		myCommands = commands;
	}
	
	public UserCommandInstance clone(){
		return new UserCommandInstance(myName, myVariables, myCommands);
	}
	
	public String getName(){
		return myName;
	}
	
	@Override
	public double evaluate() throws ParserException {
		myVariableContainer = new LocalVariableContainer(myParser.getVariableContainer());
		myParser.setVariableContainer(myVariableContainer);
		for(int i = 0; i<myVariables.size(); i++){
			myParser.getVariableContainer().setVariable(myVariables.get(i), myTree.get(i).evaluate());
		}
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

	@Override
	public String getInputString() {
		return myName + " " + myVariables.stream().reduce("", (s1, s2) -> s1 + " " + s2);
	}

	@Override
	public String getCommandText() {
		return myCommands.toString(false);
	}

}
