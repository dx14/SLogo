package parser.command.tree;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.SlogoParser;
import parser.Validator;
import parser.command.CommandElement;
import parser.command.CommandInterpreter;
import parser.command.CommandList;
import parser.command.Evaluable;

public class CommandTreeNode implements Evaluable {

	private CommandList mySource; 
	
	private Evaluable myCommand;	// two way link
	
	private CommandTreeNode myParent;
	private List<CommandTreeNode> myBranches;
	
	private SlogoParser myParser;

	public CommandTreeNode(CommandList source, SlogoParser parser) throws ParserException{
		this(source, parser, null);
	}
	
	public CommandTreeNode(CommandList source, SlogoParser parser, CommandTreeNode parent) throws ParserException{
		Validator.assertNonEmpty(source);
		
		mySource = source;
		
		myParser = parser;
		
		myParent = parent;
		myCommand = null;
		myBranches = new ArrayList<>();
	}
	
	public CommandList build () throws ParserException{
		myCommand = CommandInterpreter.reflect(mySource.get(0), this, myParser);
		mySource.remove(0);
		
		myCommand.build();	// will build this as well
		
		return mySource;
	}
	
	public CommandTreeNode buildNext() throws ParserException{
		CommandTreeNode newBranch = new CommandTreeNode(mySource, myParser, this);
		return buildNext(newBranch);
	}
	
	public CommandTreeNode buildNext(CommandTreeNode newBranch) throws ParserException{
		myBranches.add(newBranch);
		mySource = newBranch.build();
		return this;
	}
	
	public double evaluate() throws ParserException{
		return myCommand.evaluate();
	}

	public CommandList getRemainder(){
		return mySource;
	}
	
	public Evaluable getCommand(){
		return myCommand;
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
		return toString(true);
	}
	
	@Override
	public String toString(boolean useDelimiter){
		String startDelimiter = (useDelimiter)?"(":"";
		String endDelimiter = (useDelimiter)?")":"";
		StringBuilder output = new StringBuilder(startDelimiter + myCommand.toString());
		for(CommandTreeNode t : myBranches){
			output.append(" ");
			output.append(t.toString(useDelimiter));
		}
		return output.append(endDelimiter).toString();
	}
	
	@Override
	public CommandElement getCommandElement(){
		return myCommand.getCommandElement();
	}
	
	public int getNumBranches(){
		return myBranches.size();
	}
	
	// USED BY CHILD CLASSES
	
	protected SlogoParser getParser(){
		return myParser;
	}
	
	protected CommandList getSource(){
		return mySource;
	}
	
	protected List<CommandTreeNode> getBranches(){
		return myBranches;
	}
	
	protected void setSource(CommandList source){
		mySource = source;
	}
}
