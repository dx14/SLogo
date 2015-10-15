package parser.command.commandlist.math;

import java.util.Random;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class RandomCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		Random r = new Random();
		double bound = myTree.get(0).evaluate();
		if (bound < 0)  throw new ParserException("Bound is not positive");
		return r.nextDouble() * bound;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
