package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class SineCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		double degrees = myTree.get(0).evaluate();
		return Math.toDegrees(Math.sin(Math.toRadians(degrees)));
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
