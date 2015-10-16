package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class DifferenceCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myTree.get(0).evaluate() - myTree.get(1).evaluate();
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().getRemainder();
	}

}
