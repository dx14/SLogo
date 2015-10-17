package parser.command.commandlist.control;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.CommandTreeNode;
import parser.command.Evaluable;
import parser.command.commandlist.UserCommandInstance;
import parser.command.commandlist.syntax.ListStartCommand;
import parser.command.commandlist.syntax.UserDefinedCommand;
import parser.command.commandlist.syntax.VariableCommand;

public class MakeUserInstructionCommand extends Command{

	private String myName;
	private List<String> myVariables;
	private Evaluable myCommands;
		
	@Override
	public double evaluate() throws ParserException {
		return 1;
	}

	@Override
	public CommandList build() throws ParserException {
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
		
		UserCommandInstance myInstance = new UserCommandInstance(myName, myVariables, myCommands);
		myParser.getCommandContainer().addCommand(myInstance);
		
		return remainder;
	}
	
	private void parseVariables(CommandTreeNode tree) throws ParserException{
		for(int i = 0; i < tree.getNumBranches() - 1; i++){
			if(!(tree.get(i).getCommand() instanceof VariableCommand)){
				throw new ParserException("Error: " + myCommand.getRawText() + " expected variable name but got: " + tree.get(i).getCommandElement().getRawText());
			}
			myVariables.add(tree.get(i).getCommandElement().getRawText());
		}
	}
	
}
