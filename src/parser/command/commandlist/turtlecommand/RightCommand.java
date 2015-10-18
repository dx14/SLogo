package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class RightCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		double angle = myTree.get(0).evaluate();
		myParser.getCurrentTurtle().turn(angle * -1);
		return angle;
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
