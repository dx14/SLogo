package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parser.command.CommandInterpreter;
import parser.command.CommandList;
import parser.command.Evaluable;
import parser.command.commandlist.turtlecommand.ForwardCommand;
import util.Coordinate;
import util.SlogoPath;
import util.StraightPath;
import parser.structure.Turtle;
import parser.structure.Variable;
import parser.structure.VariableContainer;

public class SlogoParser implements ParserInterface{

	private Turtle myCurrentTurtle;
	private List<Turtle> myTurtles;
	private VariableContainer myVariableContainer;
	
	public static void main(String args[]) throws ParserException{
		
		SlogoParser p = new SlogoParser();
		
		p.runCommand("ifelse set :lol 0 [ fd 50 ] [ fd 100 ]");
		//p.loadCommand("examples/procedures_with_parameters/random_range.logo");
		//p.loadCommand("examples/simple/forward_complex.logo");
	}
	
	public SlogoParser(){
		myCurrentTurtle = new Turtle();
		myTurtles = new ArrayList<>(Arrays.asList(myCurrentTurtle));
		myVariableContainer = new VariableContainer();
	}
	
	public Variable getVariable(){
		return null;
	}
	
	public void moveCurrentTurtle(double distance){
		moveCurrentTurtle(new StraightPath(new Coordinate(0,0), new Coordinate(0, distance)));
	}

	public void moveCurrentTurtle(SlogoPath path){
		System.out.println("MOVING TURTLE along " + path);
	}

	public void rotateTurtle(double angle, double direction){
		
	}
	
	public void rotateTurtle(double angle){
		
	}
	
	public Turtle getCurrentTurtle(){
		return myCurrentTurtle;
	}
	
	public VariableContainer getVariableContainer(){
		return myVariableContainer;
	}
	
	public void loadCommand(String filename) throws ParserException {
		try {
			runCommand(Files.lines(Paths.get(filename))
				.reduce((s1, s2) -> s1 + "\n" + s2).orElseGet(() -> ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ParserException("Error: cannot load file " + filename);
		}
	}
	
	@Override
	public void runCommand(String command) throws ParserException {
		
		new CommandInterpreter(command, this);
		
	}

	@Override
	public void runCommand(CommandList command) throws ParserException {
		
	}

	@Override
	public void setLanguage(String language) throws ParserException {
		// TODO Auto-generated method stub
		
	}
	
}
