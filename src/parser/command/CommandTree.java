package parser.command;

import java.util.ArrayList;
import java.util.List;

import parser.ParserException;

public class CommandTree implements Evaluable {

	private List<String> mySource;
	private List<String> myRemainder;
	
	private Evaluable myCommand;	// two way link
	
	private CommandTree myParent;
	private List<CommandTree> myBranches;
	
	
	/*
	 * MYPARENT
	 */
	
	/*
	 * CREATE ROOT COMMAND
	 */
	
	public CommandTree(List<String> source){
		mySource = source;
		
		myRemainder = new ArrayList<>(mySource);
		
		myParent = null;
		myCommand = null;
		myBranches = new ArrayList<>();
	}
	
	public List<String> build () throws ParserException{
		myCommand = CommandInterpreter.reflect(mySource.get(0), this);
		myRemainder.remove(0);
		
		myCommand.build();	// will build this as well
		
		return myRemainder;
	}
	
	public Evaluable buildNext() throws ParserException{
		
		return null;
	}
	
	public boolean empty(){
		return myBranches.size() == 0;
	}
	
	public double evaluate() throws ParserException{
		return myCommand.evaluate();
	}
}
