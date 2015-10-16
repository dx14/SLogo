package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class ListStartCommand extends Command {

	private int myNumCommands;
	
	@Override
	public double evaluate() throws ParserException {
		double lastValue = 0;
		for(int i=0; i<myNumCommands; i++){
			lastValue = myTree.get(i).evaluate();
		}
		return lastValue;
	}
	
	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().getRemainder();
		myNumCommands = 0;
		while(!(myTree.get(myNumCommands).getCommand() instanceof ListEndCommand)){
			remainder = myTree.buildNext().getRemainder();
			myNumCommands++;
		}
		return remainder;
	}

}
