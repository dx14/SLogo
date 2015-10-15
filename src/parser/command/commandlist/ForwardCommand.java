package parser.command.commandlist;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;
import parser.command.Evaluable;

public class ForwardCommand extends Command {
	
	public ForwardCommand(){
		System.out.println("Forward Command Initializing");
	}

	@Override
	public double evaluate() throws ParserException {
		double distance = myTree.get(0).evaluate();
		System.out.println(distance);
		myParser.moveCurrentTurtle(distance);
		return distance;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
