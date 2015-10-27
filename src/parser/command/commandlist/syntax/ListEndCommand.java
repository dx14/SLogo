package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;

public class ListEndCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return 0;
	}
	
	@Override
	public CommandList build() throws ParserException {
		Validator.assertParent(myTree, myCommand, ListStartCommand.class);
		return myTree.getRemainder();
	}

}
