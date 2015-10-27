package parser.command.commandlist.control;

import parser.ParserException;
import parser.Validator;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;
import parser.command.commandlist.syntax.VariableCommand;
import parser.command.tree.CommandTreeNode;
import parser.structure.Variable;

public class DoTimesCommand extends Command {
	
	private CommandTreeNode myParameters;
	private CommandTreeNode myCommands;
	
	@Override
	public double evaluate() throws ParserException {
		Variable variable = myParser.getVariableContainer().getVariable(myParameters.get(0));
		variable.setValue(1);
		double limit = myParameters.get(1).evaluate();
		double lastValue = 0;
		
		while(variable.getValue() <= limit){
			lastValue = myCommands.evaluate();
			variable.increment();
		}
		
		return lastValue;
	}
	
	@Override
	public CommandList build() throws ParserException{
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		
		myParameters = myTree.get(0);
		myCommands = myTree.get(1);
		
		Validator.assertType(myParameters, myCommand, ListStartCommand.class);
		Validator.assertType(myCommands, myCommand, ListStartCommand.class);
		Validator.assertType(myParameters.get(0), myCommand, VariableCommand.class);
		Validator.assertNumArguments(myParameters, myCommand, 3, true);
		
		return remainder;
	}

}
