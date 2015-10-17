package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;

import parser.command.CommandInterpreter;
import parser.command.CommandList;
import parser.command.Evaluable;
import parser.command.commandlist.turtlecommand.ForwardCommand;
import util.Coordinate;
import util.SlogoPath;
import util.StraightPath;
import parser.structure.CommandContainer;
import parser.structure.Turtle;
import parser.structure.Variable;
import parser.structure.VariableContainer;

public class SlogoParser implements ParserInterface{

	private Turtle myCurrentTurtle;
	private List<Turtle> myTurtles;
	private VariableContainer myVariableContainer;
	private CommandContainer myCommandContainer;
	
	public static void main(String args[]) throws ParserException{
		
		SlogoParser p = new SlogoParser();
		
		//p.runCommand("to walk [ :turns ] [ repeat :turns [ forward 50 2 ] ] walk walk 3");
		p.loadCommand("examples/procedures_with_parameters/random_range.logo");
		//p.loadCommand("examples/simple/forward_complex.logo");
		p.getVariableContainer().debug();
		p.getCommandContainer().debug();
	}
	
	public SlogoParser(){
		myCurrentTurtle = new Turtle();
		myTurtles = new ArrayList<>(Arrays.asList(myCurrentTurtle));
		myVariableContainer = new VariableContainer();
		myCommandContainer = new CommandContainer();
	}
	
	public Turtle getCurrentTurtle(){
		return myCurrentTurtle;
	}
	
	public VariableContainer getVariableContainer(){
		return myVariableContainer;
	}
	
	public CommandContainer getCommandContainer(){
		return myCommandContainer;
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
	
	public void addVariableObserver(Observer o){
		myVariableContainer.addObserver(o);
	}
}
