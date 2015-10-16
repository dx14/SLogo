package parser.command.commandlist.control;

import parser.ParserException;
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
		if(!(myTree.get(0).getCommand() instanceof VariableCommand))
			throw new ParserException("MAKE expected variable argument, got " + myTree.get(0).getCommandElement().getRawText());
		return remainder;
	}

}
