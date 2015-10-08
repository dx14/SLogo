package parser.command;

import java.util.List;

import parser.ParserException;
import parser.SlogoParser;

public interface Evaluable {
	
	public double evaluate() throws ParserException;
	
	public List<String> build() throws ParserException;

	public void setParameters(CommandTree tree, SlogoParser parser);
	
}
