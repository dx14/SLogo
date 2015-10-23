package parser.command.commandlist.turtles;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.commandlist.syntax.ListStartCommand;

public class AskCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		System.out.println("EVALUATING TELL");
		List<Integer> turtleIDs = new ArrayList<>();
		for(int i =0; i<myTree.get(0).getNumBranches()-1; i++){
			turtleIDs.add(Math.abs(((Double)myTree.get(0).get(i).evaluate()).intValue()));
		}
		myParser.getTurtleContainer().addToExecList(turtleIDs);
		double result = myTree.get(1).evaluate();
		myParser.getTurtleContainer().removeFromExecList();
		return result;
	}

	@Override
	public CommandList build() throws ParserException {
		CommandList remainder = myTree.buildNext().buildNext().getRemainder();
		if(!(myTree.get(0).getCommand() instanceof ListStartCommand)){
			throw new ParserException("Error: expected list for " + myCommand.getRawText());
		}
		if(myTree.get(0).getNumBranches()<2){
			throw new ParserException("Error: expected non-empty list for " + myCommand.getRawText());
		}
		return remainder;
	}
	
}
