package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class SetPositionCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().setPosition(myTree.get(0), myTree.get(1));
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().getRemainder();
	}

}
