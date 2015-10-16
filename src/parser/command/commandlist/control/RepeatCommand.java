package parser.command.commandlist.control;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;
import parser.structure.Variable;

public class RepeatCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		Variable repcount = myParser.getVariableContainer().getVariable(":repcount");
		repcount.setValue(1);
		double iterations = myTree.get(0).evaluate();
		double lastValue = 0;
		while(repcount.getValue() <= iterations){
			lastValue = myTree.get(1).evaluate();
			repcount.increment();
		}
		return lastValue;
	}
	
	@Override
	public CommandList build() throws ParserException{
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		if(!(myTree.get(1).getCommand() instanceof ListStartCommand))
			throw new ParserException(myCommand.getRawText() + " expected list argument, got " + myTree.get(0).getCommandElement().getRawText());
		return remainder;
	}

}
