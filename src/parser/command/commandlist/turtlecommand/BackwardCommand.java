package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class BackwardCommand extends Command {
	@Override
	public double evaluate() throws ParserException {
		double distance = myTree.get(0).evaluate();
		myParser.getCurrentTurtle().move(distance * -1);
		return distance;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
