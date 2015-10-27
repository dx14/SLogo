package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;

public class PenUpCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().penUp();
		return 0;
	}
}
