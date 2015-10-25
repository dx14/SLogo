package parser.command.commandlist;

import parser.ParserException;
import parser.SlogoParser;
import parser.command.CommandList;
import parser.command.CommandTreeNode;

// TODO:
// tree builds two branches
// second one duplicates command
// catch exception -> try to build without duplicating
// throw if there's still an exception

public class BinaryRecursiveTreeNode extends CommandTreeNode {

	public BinaryRecursiveTreeNode(CommandList source, SlogoParser parser, CommandTreeNode parent)
			throws ParserException {
		super(source, parser, parent);
		// TODO Auto-generated constructor stub
	}

}
