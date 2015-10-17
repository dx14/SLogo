package parser.command.commandlist.control;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.CommandTreeNode;
import parser.command.commandlist.syntax.ListStartCommand;
import parser.command.commandlist.syntax.VariableCommand;
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
		
		if(!(myParameters.getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected [ :variable limit ] but got: " + myParameters.getCommandElement().getRawText());
		}
		
		if(!(myCommands.getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected list of commands but got: " + myCommands.getCommandElement().getRawText());
		}
		
		if(!(myParameters.get(0).getCommand() instanceof VariableCommand)){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected variable but got: " + myParameters.get(0).getCommandElement().getRawText());
		}
		
		if(myParameters.getNumBranches()!=3){
			throw new ParserException("Error: " + myCommand.getRawText() + " expected 2 parameters but got: " + (myParameters.getNumBranches()-1));
		}
		
		return remainder;
	}

}
