package parser.command;

import parser.ParserException;
import parser.SlogoParser;

public interface Evaluable {
	
	public double evaluate() throws ParserException;
	
	public CommandList build() throws ParserException;
	
	public CommandElement getCommandElement();

	public void setParameters(CommandTreeNode tree, SlogoParser parser, CommandElement command);
	
	public String toString(boolean useDelimiter);
	
}