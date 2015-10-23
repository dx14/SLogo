package parser.command.commandlist.turtles;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class AskWithCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getTurtleContainer().addToExecList(myTree.get(0));
		double result = myTree.get(1).evaluate();
		myParser.getTurtleContainer().removeFromExecList();
		return result;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		if(!(myTree.get(0).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: expected list for " + myCommand.getRawText());
		}
		if(myTree.get(0).getNumBranches()<2){
			throw new ParserException("Error: expected non-empty list for " + myCommand.getRawText());
		}
		return remainder;	
	}
	
}
