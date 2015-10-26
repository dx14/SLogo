package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.CommandList;

public class GroupEndCommand extends parser.command.Command{
	
	@Override
	public double evaluate() throws ParserException {
		return 0;
	}
	
	@Override
	public CommandList build() throws ParserException {
		if(!(myTree.getParent().getCommand() instanceof GroupStartCommand)){
			throw new ParserException("Error: unmatched group brace ')'");
		}
		return myTree.getRemainder();
	}

}
