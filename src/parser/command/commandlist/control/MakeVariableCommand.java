package parser.command.commandlist.control;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.VariableCommand;

public class MakeVariableCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		String name = myTree.get(0).getCommandElement().getRawText();
		double value = myTree.get(1).evaluate();
		myParser.getVariableContainer().setVariable(name, value);
		return value;
	}
	
	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		Validator.assertType(myTree.get(0), myCommand, VariableCommand.class);
		return remainder;
	}

}
