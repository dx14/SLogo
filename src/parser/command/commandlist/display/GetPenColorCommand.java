package parser.command.commandlist.display;

import parser.ParserException;
import parser.command.Command;

public class GetPenColorCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		 return myParser.getCurrentTurtle().getPen().getColor();
	}

}
