package parser.command.commandlist.turtlequery;

import parser.ParserException;
import parser.command.Command;

public class IsPenDownCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().isPenDown() ? 1 : 0;
	}

}
