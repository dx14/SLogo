package parser;

import parser.command.CommandInterpreter;
import parser.command.CommandList;
import util.Coordinate;
import util.SlogoPath;
import util.StraightPath;
import util.Variable;

public class SlogoParser implements ParserInterface{

	public static void main(String args[]) throws ParserException{
		
		SlogoParser p = new SlogoParser();
		p.runCommand("fd 50");
		
	}
	
	public Variable getVariable(){
		return null;
	}
	
	public SlogoParser(){
		
	}
	
	public void moveCurrentTurtle(double distance){
		moveCurrentTurtle(new StraightPath(new Coordinate(0,0), new Coordinate(0, distance)));
	}

	public void moveCurrentTurtle(SlogoPath path){
		System.out.println("MOVING TURTLE along " + path);
	}
		
	@Override
	public void runCommand(String command) throws ParserException {
		
		new CommandInterpreter(command, this);
		
	}

	@Override
	public void runCommand(CommandList command) throws ParserException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLanguage(String language) throws ParserException {
		// TODO Auto-generated method stub
		
	}
	
}
