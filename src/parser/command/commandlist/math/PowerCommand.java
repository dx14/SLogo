package parser.command.commandlist.math;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class PowerCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return Math.pow(myTree.get(0).evaluate(), myTree.get(1).evaluate());
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().getRemainder();
	}

}
