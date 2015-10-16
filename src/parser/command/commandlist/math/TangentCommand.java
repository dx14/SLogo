package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class TangentCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		double degrees = myTree.get(0).evaluate();
		if (degrees == 0) return 0;
		return Math.toDegrees(Math.tan(Math.toRadians(degrees)));
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
