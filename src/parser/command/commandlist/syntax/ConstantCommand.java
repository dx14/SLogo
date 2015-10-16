package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.Command;

public class ConstantCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return Double.parseDouble(myCommand.getRawText());
	}
}
