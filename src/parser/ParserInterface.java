package parser;

import parser.command.CommandList;

public interface ParserInterface {

	public void runCommand(String command) throws ParserException;
	
	public void runCommand(CommandList command) throws ParserException;
	
	public void setLanguage(String language) throws ParserException;
	
}