package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class ListEndCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return 0;
	}
	
	@Override
	public CommandList build() throws ParserException {
		if(!(myTree.getParent().getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: unmatched list brace ']'");
		}
		return myTree.getRemainder();
	}

}
