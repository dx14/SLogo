package parser.command.commandlist.control;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.CommandTreeNode;
import parser.command.Evaluable;
import parser.command.commandlist.syntax.ListStartCommand;
import parser.command.commandlist.syntax.UserDefinedCommand;
import parser.command.commandlist.syntax.VariableCommand;
import parser.structure.Variable;

public class MakeUserInstructionCommand extends Command{

	private String myName;
	private List<Variable> myVariables;
	private Evaluable myCommands;
	
	private boolean isBuilt = false;
	
	@Override
	public double evaluate() throws ParserException {
		if(isBuilt){
			assignVariables();
			return myCommands.evaluate();
		}
		else{
			myParser.getCommandContainer().addCommand(myName, this);
			isBuilt = true;
			return 1;
		}
	}
	
	private void assignVariables() throws ParserException {
		for(int i = 0; i < myVariables.size(); i++){
			myVariables.get(i).setValue(myTree.get(i).evaluate());
		}
	}

	@Override
	public CommandList build() throws ParserException {
		return (isBuilt) ? loadVariables() : initialize();
	}
	
	private CommandList initialize() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().buildNext().getRemainder();
		myVariables = new ArrayList<>();
		
		if(!(myTree.get(0).getCommand() instanceof UserDefinedCommand)){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected user defined command name but got: " + myTree.get(0).getCommandElement().getRawText());
		}
		
		if(!(myTree.get(1).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected list of variables but got: " + myTree.get(1).getCommandElement().getRawText());
		}
		
		if(!(myTree.get(2).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected list of commands but got: " + myTree.get(2).getCommandElement().getRawText());
		}
		
		myName = myTree.get(0).getCommandElement().getRawText();
		parseVariables(myTree.get(1));
		myCommands = myTree.get(2);
		
		return remainder;
	}
	
	private void parseVariables(CommandTreeNode tree) throws ParserException{
		for(int i = 0; i < tree.getNumBranches() - 1; i++){
			if(!(tree.get(i).getCommand() instanceof VariableCommand)){
				throw new ParserException("Error: " + myCommand.getRawText() + " expected variable name but got: " + tree.get(i).getCommandElement().getRawText());
			}
			System.out.println(myParser.getVariableContainer().getVariable( tree.get(i)));
			myVariables.add(myParser.getVariableContainer().getVariable( tree.get(i) ));
		}
	}

	private CommandList loadVariables() throws ParserException {
		CommandList remainder = myTree.getRemainder();
		for(int i = 0; i<myVariables.size(); i++){
			remainder = myTree.buildNext().getRemainder();
		}
		return remainder;
	}
	
}
