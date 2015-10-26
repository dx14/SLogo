package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.CommandList;
import parser.command.Evaluable;
import parser.command.tree.BinaryRecursiveTreeNode;

// TODO: recursively build down, duplicating parent command
// TODO: add clone to Evaluable? Or Command? (Allow us to duplicate easily)
// TODO: add add branch capability -> pass Evaluable object

// TODO: TRANSPOSE COMMAND ON TREE -> allow us to take a branch and swap it?
// TODO: set branch command on tree -> allow us to modify branches?

public class GroupStartCommand extends parser.command.Command{
		
	@Override
	public double evaluate() throws ParserException {
		return myTree.get(0).evaluate();
	}
	
	@Override
	public CommandList build() throws ParserException {
		BinaryRecursiveTreeNode groupTree = new BinaryRecursiveTreeNode(myTree.getRemainder(), myParser, myTree);
		return myTree.buildNext(groupTree).buildNext().getRemainder();
	}
}
