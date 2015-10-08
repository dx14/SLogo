package parser.command;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;
import parser.SlogoParser;

public class CommandTree implements Evaluable {

	private CommandList mySource;
	private CommandList myRemainder;
	
	private Evaluable myCommand;	// two way link
	
	private CommandTree myParent;
	private List<CommandTree> myBranches;
	
	private SlogoParser myParser;
	
	
	/*
	 * CREATE ROOT COMMAND
	 */
	
	public CommandTree(CommandList source, SlogoParser parser){
		this(source, parser, null);
	}
	
	public CommandTree(CommandList source, SlogoParser parser, CommandTree parent){
		mySource = source;
		myRemainder = mySource.copy();
		
		myParser = parser;
		
		myParent = parent;
		myCommand = null;
		myBranches = new ArrayList<>();
	}
	
	public CommandList build () throws ParserException{
		myCommand = CommandInterpreter.reflect(mySource.getNativeCommand(0), this, myParser);
		myRemainder.remove(0);
		
		myCommand.build();	// will build this as well
		
		return myRemainder;
	}
	
	public Evaluable buildNext() throws ParserException{
		CommandTree newBranch = new CommandTree(myRemainder, myParser, this);
		myBranches.add(newBranch);
		myRemainder = newBranch.build();
		return newBranch.getCommand();
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
	
	@Override
	public void setParameters(CommandTree tree, SlogoParser parser) {
		myParser = parser;
		myParent = tree;
	}
}
