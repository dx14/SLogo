package parser.command.commandlist.booleancommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class NotCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		double test = myTree.get(0).evaluate();
		if (test == 0) return 1;
		return 0;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}