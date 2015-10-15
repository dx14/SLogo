package parser.command.commandlist.turtlequery;

import parser.ParserException;
import parser.command.Command;

public class IsShowingCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().isShowing() ? 1 : 0;
	}

}
