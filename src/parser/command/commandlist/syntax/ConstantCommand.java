package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class ConstantCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return Double.parseDouble(myCommand.getRawText());
	}
}
