package parser.command.commandlist.booleancommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class EqualCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		double expr1 = myTree.get(0).evaluate();
		double expr2 = myTree.get(1).evaluate();
		if (expr1 == expr2) return 1;
		return 0;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().getRemainder();
	}

}
