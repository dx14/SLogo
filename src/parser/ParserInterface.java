package parser;

import parser.command.CommandString;

public interface ParserInterface {

	public void runCommand(String command) throws ParserException;
	
	public void runCommand(CommandString command) throws ParserException;
	
	public void setLanguage(String language) throws ParserException;
	
}
