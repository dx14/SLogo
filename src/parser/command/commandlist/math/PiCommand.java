package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;

public class PiCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return Math.PI;
	}
}
