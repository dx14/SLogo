package parser.command.commandlist.turtlequery;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class YCoordinateCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().getCoordinate().getY();
	}

}
