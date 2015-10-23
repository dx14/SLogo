package parser.command.commandlist.turtles;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class TellCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		List<Integer> turtleIDs = new ArrayList<>();
		for(int i =0; i<myTree.get(0).getNumBranches()-1; i++){
			turtleIDs.add(Math.abs(((Double)myTree.get(0).get(i).evaluate()).intValue()));
		}
		myParser.getTurtleContainer().setCurrent(turtleIDs);
		return turtleIDs.get(turtleIDs.size()-1);
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().getRemainder();
		if(!(myTree.get(0).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: expected list for " + myCommand.getRawText());
		}
		if(myTree.get(0).getNumBranches()<2){
			throw new ParserException("Error: expected non-empty list for " + myCommand.getRawText());
		}
		return remainder;
	}
	
}
