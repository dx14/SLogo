package parser.command.commandlist.control;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.Evaluable;
import parser.command.commandlist.UserCommandInstance;
import parser.command.commandlist.syntax.ListStartCommand;
import parser.command.commandlist.syntax.UserDefinedCommand;
import parser.command.commandlist.syntax.VariableCommand;
import parser.command.tree.CommandTreeNode;

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
		// only build two branches to start, so we can add it to the tree in case of recursion
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		myVariables = new ArrayList<>();
		
		Validator.assertType(myTree.get(0), myCommand, UserDefinedCommand.class);
		Validator.assertType(myTree.get(1), myCommand, ListStartCommand.class);
		
		myName = myTree.get(0).getCommandElement().getRawText();
		parseVariables(myTree.get(1));
		
		UserCommandInstance myInstance = new UserCommandInstance(myName, myVariables, myCommands);
		myParser.getCommandContainer().addCommand(myInstance);

		// build commands here so that recursive commands will operate
		remainder = myTree.buildNext().getRemainder();
		
		Validator.assertType(myTree.get(2), myCommand, ListStartCommand.class);
		
		myCommands = myTree.get(2);
		myInstance.setCommandTree(myCommands);
		
		myParser.getCommandContainer().update();
		
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
