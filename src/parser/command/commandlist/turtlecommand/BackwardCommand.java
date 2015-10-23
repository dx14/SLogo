package parser.command.commandlist.turtlecommand;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class BackwardCommand extends Command {
	@Override
	public double evaluate() throws ParserException {
		return myParser.getCurrentTurtle().move(myTree.get(0), (d) -> d*-1);
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().getRemainder();
	}

}
