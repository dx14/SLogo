package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class NaturalLog extends Command {

	@Override
	public double evaluate() throws ParserException {
		double expr = myTree.get(0).evaluate();
		if (expr <= 0) throw new ParserException("Undefined log value for " + expr);
		return Math.log(myTree.get(0).evaluate());
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
