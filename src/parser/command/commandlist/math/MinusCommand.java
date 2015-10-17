package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class MinusCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myTree.get(0).evaluate()*-1;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
