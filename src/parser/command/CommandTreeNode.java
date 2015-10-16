package parser.command;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.SlogoParser;

public class CommandTreeNode implements Evaluable {

	private CommandList mySource; 
	private CommandList myRemainder; 
	
	private Evaluable myCommand;	// two way link
	
	private CommandTreeNode myParent;
	private List<CommandTreeNode> myBranches;
	
	private SlogoParser myParser;
	
	
	/*
	 * CREATE ROOT COMMAND
	 */
	
	public CommandTreeNode(CommandList source, SlogoParser parser) throws ParserException{
		this(source, parser, null);
	}
	
	public CommandTreeNode(CommandList source, SlogoParser parser, CommandTreeNode parent) throws ParserException{
		if(source.isEmpty()){
			throw new ParserException("Error: expected another argument.");
		}
		mySource = source;
		myRemainder = mySource.copy();
		
		myParser = parser;
		
		myParent = parent;
		myCommand = null;
		myBranches = new ArrayList<>();
	}
	
	public CommandList build () throws ParserException{
		myCommand = CommandInterpreter.reflect(mySource.get(0), this, myParser);
		myRemainder.remove(0);
		
		myCommand.build();	// will build this as well
		
		return myRemainder;
	}
	
	public CommandTreeNode buildNext() throws ParserException{
		CommandTreeNode newBranch = new CommandTreeNode(myRemainder, myParser, this);
		myBranches.add(newBranch);
		myRemainder = newBranch.build();
		return this;
	}
	
	public boolean empty(){
		return myBranches.size() == 0;
	}
	
	public double evaluate() throws ParserException{
		return myCommand.evaluate();
	}

	public CommandList getRemainder(){
		return myRemainder;
	}
	
	public Evaluable getCommand(){
		return myCommand;
	}
	
	public CommandList getSource(){
		return mySource.copy();
	}
	
	public CommandTreeNode getParent(){
		return myParent;
	}
	
	public CommandTreeNode get(int index){
		return myBranches.get(index);
	}
	
	@Override
	public void setParameters(CommandTreeNode tree, SlogoParser parser, CommandElement command) {
		myParser = parser;
		myParent = tree;
	}
	
	@Override
	public String toString(){
		StringBuilder output = new StringBuilder("(" + myCommand.toString());
		for(CommandTreeNode t : myBranches){
			output.append(" ");
			output.append(t.toString());
		}
		return output.append(")").toString();
	}
	
	@Override
	public CommandElement getCommandElement(){
		return myCommand.getCommandElement();
	}
}
