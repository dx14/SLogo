package parser.command.commandlist.turtles;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class AskWithCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		myParser.getTurtleContainer().addToExecList(myTree.get(0));
		double result = myTree.get(1).evaluate();
		myParser.getTurtleContainer().removeFromExecList();
		return result;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		Validator.assertType(myTree.get(0), myCommand, ListStartCommand.class);
		Validator.assertAtLeastNumArguments(myTree.get(0), myCommand, 2, true);
		return remainder;	
	}
	
}
