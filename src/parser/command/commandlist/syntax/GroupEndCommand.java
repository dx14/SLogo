package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.Validator;
import parser.command.CommandList;

public class GroupEndCommand extends parser.command.Command{
	
	@Override
	public double evaluate() throws ParserException {
		return 0;
	}
	
	@Override
	public CommandList build() throws ParserException {
		Validator.assertParent(myTree, myCommand, GroupStartCommand.class);
		return myTree.getRemainder();
	}

}
