package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;

public class HideTurtleCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().visible(false);
		return 0;
	}
}
