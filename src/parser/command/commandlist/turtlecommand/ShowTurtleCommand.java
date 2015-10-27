package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;

public class ShowTurtleCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getCurrentTurtle().visible(true);
		return 1;
	}

}
