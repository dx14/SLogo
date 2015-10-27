package parser.command.commandlist.syntax;

import parser.ParserException;
import parser.command.CommandList;
import parser.command.tree.BinaryRecursiveTreeNode;

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
