package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;

public class ClearScreenCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().clear();
		return myParser.getCurrentTurtle().goHome();
	}
}
