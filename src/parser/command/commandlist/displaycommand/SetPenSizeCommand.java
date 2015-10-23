package parser.command.commandlist.displaycommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class SetPenSizeCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		 return myParser.getCurrentTurtle().setPenSize(myTree.get(0));
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
