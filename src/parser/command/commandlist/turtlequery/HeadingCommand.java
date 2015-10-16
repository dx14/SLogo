package parser.command.commandlist.turtlequery;

import parser.ParserException;
import parser.command.Command;

public class HeadingCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().getHeading();
	}

}
