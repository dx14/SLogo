package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;

public class HomeCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().goHome();
	}
}
