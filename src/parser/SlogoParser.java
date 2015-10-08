package parser;

import parser.command.CommandInterpreter;
import parser.command.CommandString;
import util.Variable;

public class SlogoParser implements ParserInterface{

	public Variable getVariable(){
		return null;
	}
	
	public SlogoParser(){
		
	}

	@Override
	public void runCommand(String command) throws ParserException {
		
		new CommandInterpreter(command);
		
	}

	@Override
	public void runCommand(CommandString command) throws ParserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLanguage(String language) throws ParserException {
		// TODO Auto-generated method stub
		
	}
	
}
