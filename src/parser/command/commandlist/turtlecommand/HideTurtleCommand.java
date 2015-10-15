package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class HideTurtleCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().hide();
		return 0;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.getRemainder();
	}

}
