package parser.command;

import java.util.List;

import parser.ParserException;

public interface Evaluable {
	
	public double evaluate() throws ParserException;
	
	public List<String> build() throws ParserException;

}
