package parser.command.commandlist.display;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class ClearStampsCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		 return myParser.clearStamps();
	}

}