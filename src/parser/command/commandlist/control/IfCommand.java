package parser.command.commandlist.control;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class IfCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return (myTree.get(0).evaluate()!=0)? myTree.get(1).evaluate() : 0;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		if(!(myTree.get(1).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: if expected list argument, got: " + myTree.get(1).getCommandElement().getRawText());
		}
		return remainder;
	}
}
