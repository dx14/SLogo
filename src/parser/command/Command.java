package parser.command;

import parser.SlogoParser;

public abstract class Command implements Evaluable{
	
	protected CommandTree myTree;
	protected SlogoParser myParser;
	
	public Command(){
		this(null, null);
	}
	
	public Command(CommandTree tree, SlogoParser parser){
		setParameters(tree, parser);
	}
	
	@Override
	public void setParameters(CommandTree tree, SlogoParser parser){
		myTree = tree;
		myParser = parser;
	}

}
