package parser.command.commandlist.control;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class IfCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return (myTree.get(0).evaluate()!=0)? myTree.get(1).evaluate() : 0;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		Validator.assertType(myTree.get(1), myCommand, ListStartCommand.class);
		return remainder;
	}
}
