package parser;

import java.util.Observer;

import parser.command.CommandList;

public interface ParserInterface {

	public void runCommand(String command) throws ParserException;
	
	public void runCommand(CommandList command) throws ParserException;
	
	public void setLanguage(String language) throws ParserException;
	
	public void outputCommandContainer(String filename) throws ParserException;
	
	public void addVariableObserver(Observer o);
	
	public void addCommandObserver(Observer o);
	
	public void addTurtleObserver(Observer o);
	
	public void debug();
	
}
