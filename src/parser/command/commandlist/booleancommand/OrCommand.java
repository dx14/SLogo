package parser.command.commandlist.booleancommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class OrCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		double test1 = myTree.get(0).evaluate();
		double test2 = myTree.get(1).evaluate();
		if (test1 !=0 || test2 !=0) return 1;
		return 0;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().getRemainder();
	}

}