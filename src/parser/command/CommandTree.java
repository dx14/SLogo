package parser.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import parser.ParserException;
import parser.SlogoParser;

public class CommandTree implements Evaluable {

	private List<String> mySource;
	private List<String> myRemainder;
	
	private Evaluable myCommand;	// two way link
	
	private CommandTree myParent;
	private List<CommandTree> myBranches;
	
	private SlogoParser myParser;
	
	
	/*
	 * CREATE ROOT COMMAND
	 */
	
	public CommandTree(List<String> source, SlogoParser parser){
		this(source, parser, null);
	}
	
	public CommandTree(List<String> source, SlogoParser parser, CommandTree parent){
		mySource = source;
		myRemainder = new ArrayList<>(mySource);
		
		myParent = parent;
		myCommand = null;
		myBranches = new ArrayList<>();
	}
	
	public List<String> build () throws ParserException{
		myCommand = CommandInterpreter.reflect(mySource.get(0), this, myParser);
		myRemainder.remove(0);
		
		myCommand.build();	// will build this as well
		
		return myRemainder;
	}
	
	public Evaluable buildNext() throws ParserException{
		CommandTree newBranch = new CommandTree(myRemainder, myParser);
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

	public List<String> getRemainder(){
		return myRemainder;
	}
	
	public Evaluable getCommand(){
		return myCommand;
	}
	
	public List<String> getSource(){
		return Collections.unmodifiableList(mySource);
	}
	
	@Override
	public void setParameters(CommandTree tree, SlogoParser parser) {
		myParser = parser;
		myParent = tree;
	}
}
