package parser.command.commandlist.displaycommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class StampCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		 return myParser.getCurrentTurtle().stamp();
	}

}
