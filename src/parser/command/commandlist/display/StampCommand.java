package parser.command.commandlist.display;

import parser.ParserException;
import parser.command.Command;

public class StampCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		 return myParser.getTurtleContainer().getCurrentTurtle().stamp();
	}

}
