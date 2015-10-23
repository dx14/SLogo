package parser.command.commandlist.multipleturtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class AskWithCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().getRemainder();
	}
	
}
