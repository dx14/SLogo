package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class PiCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return Math.PI;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.getRemainder();
	}

}
