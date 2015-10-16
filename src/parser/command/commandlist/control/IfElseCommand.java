package parser.command.commandlist.control;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class IfElseCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return (myTree.get(0).evaluate()!=0)? myTree.get(1).evaluate() : myTree.get(2).evaluate();
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().buildNext().getRemainder();
		if(!(myTree.get(1).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: ifelse expected list argument, got: " + myTree.get(1).getCommandElement().getRawText());
		}
		if(!(myTree.get(2).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: ifelse expected list argument, got: " + myTree.get(2).getCommandElement().getRawText());
		}
		return remainder;
	}
}
