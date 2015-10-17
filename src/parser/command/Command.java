package parser.command;

import parser.ParserException;
import parser.SlogoParser;

public abstract class Command implements Evaluable{
	
	protected CommandTreeNode myTree;
	protected SlogoParser myParser;
	protected CommandElement myCommand;
	
	public static final boolean DEBUG = true;
	
	public Command(){
		myTree = null;
		myParser = null;
		myCommand = null;
	}
	
	public Command(CommandTreeNode tree, SlogoParser parser, CommandElement command){
		setParameters(tree, parser, command);
	}
	
	@Override
	public void setParameters(CommandTreeNode tree, SlogoParser parser, CommandElement command){
		myTree = tree;
		myParser = parser;
		myCommand = command;
		if(DEBUG){
			System.out.println("Initializing: " + myCommand.getNativeCommand());
		}
	}
	
	@Override
	public CommandList build() throws ParserException{
		return myTree.getRemainder();
	}
	
	@Override
	public CommandElement getCommandElement(){
		return myCommand;
	}
	
	@Override
	public String toString(){
		return myCommand.getRawText();
	}
}
