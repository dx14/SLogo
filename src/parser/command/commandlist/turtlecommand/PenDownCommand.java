package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;

public class PenDownCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().getPen().setDown(true);
		return 1;
	}
}
