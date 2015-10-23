package parser.command.commandlist.turtles;

import parser.ParserException;
import parser.command.Command;

public class TurtlesCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getTurtleContainer().getNumTurtles();
	}
	
}
