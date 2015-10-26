package parser.command.tree;

import parser.ParserException;
import parser.SlogoParser;
import parser.command.CommandList;

// TODO:
// tree builds two branches
// second one duplicates command
// catch exception -> try to build without duplicating
// throw if there's still an exception

public class BinaryRecursiveTreeNode extends CommandTreeNode {

	public BinaryRecursiveTreeNode(CommandList source, SlogoParser parser, CommandTreeNode parent)
			throws ParserException {
		super(source, parser, parent);
	}

	public CommandTreeNode buildNext() throws ParserException{
		CommandTreeNode newBranch;
		CommandList remainder;
		if(getNumBranches() < 1){
			newBranch = new CommandTreeNode(getSource(), getParser(), this);
			remainder = newBranch.build();
		}
		else{
			CommandList appendedList = getSource().copy();
			appendedList.insert(0, getCommand().getCommandElement());
			newBranch = new BinaryRecursiveTreeNode(appendedList, getParser(), this);
			try{
				remainder = newBranch.build();
			}
			catch(ParserException e){
				newBranch = new CommandTreeNode(getSource(), getParser(), this);
				remainder = newBranch.build();
			}
		}
		getBranches().add(newBranch);
		setSource(remainder);
		return this;
	}
}
