package parser.command.commandlist.display;

import parser.ParserException;
import parser.command.Command;
import parser.command.CommandList;

public class SetPaletteCommand extends Command {

	@Override
	public double evaluate() throws ParserException {
		 return myParser.setPaletteColor(myTree.get(0), myTree.get(1), myTree.get(2), myTree.get(3));
	}

	@Override
	public CommandList build() throws ParserException {
		return myTree.buildNext().buildNext().buildNext().buildNext().getRemainder();
	}

}
