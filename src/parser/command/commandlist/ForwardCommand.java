package parser.command.commandlist;

import java.util.List;

import parser.ParserException;
import parser.SlogoParser;
import parser.command.Command;
import parser.command.CommandTree;

public class ForwardCommand extends Command {

	public ForwardCommand(){
		System.out.println("Forward Command Initializing");
	}

	@Override
	public double evaluate() throws ParserException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> build() throws ParserException {
		// TODO Auto-generated method stub
		return null;
	}

}
