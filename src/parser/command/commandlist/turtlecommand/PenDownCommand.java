package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class PenDownCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().penDown();
		return 1;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.getRemainder();
	}

}
