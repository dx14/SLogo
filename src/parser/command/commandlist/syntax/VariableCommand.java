package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.Command;

public class VariableCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getVariableContainer().getVariableValue(myCommand.getRawText());
	}

}
