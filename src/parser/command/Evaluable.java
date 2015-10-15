package parser.command;

import parser.ParserException;
import parser.SlogoParser;

public interface Evaluable {
	
	public double evaluate() throws ParserException;
	
	public CommandList build() throws ParserException;

	public void setParameters(CommandTreeNode tree, SlogoParser parser);
	
}