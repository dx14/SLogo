package parser.command;

import parser.SlogoParser;

public abstract class Command implements Evaluable{
	
	protected CommandTreeNode myTree;
	protected SlogoParser myParser;
	
	public Command(){
		this(null, null);
	}
	
	public Command(CommandTreeNode tree, SlogoParser parser){
		setParameters(tree, parser);
	}
	
	@Override
	public void setParameters(CommandTreeNode tree, SlogoParser parser){
		myTree = tree;
		myParser = parser;
	}

}
