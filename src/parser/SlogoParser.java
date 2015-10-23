package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observer;

import parser.command.CommandInterpreter;
import parser.command.CommandList;
import parser.resource.ResourceParser;
import parser.structure.CommandContainer;
import parser.structure.FullTurtle;
import parser.structure.SimpleTurtle;
import parser.structure.TurtleContainer;
import parser.structure.VariableContainer;

public class SlogoParser implements ParserInterface{

	private TurtleContainer myTurtleContainer;
	private VariableContainer myVariableContainer;
	private CommandContainer myCommandContainer;
	
	private ResourceParser myResourceParser;
	private CommandInterpreter myCommandInterpreter;
	
	public static void main(String args[]) throws ParserException{
		
		SlogoParser p = new SlogoParser();
		
		//p.runCommand("to walk [ :turns ] [ repeat :turns [ forward 50 2 ] ] walk walk 3");
		//p.loadCommand("examples/procedures_with_parameters/random_range.logo");
		//p.loadCommand("examples/simple/forward_complex.logo");
		p.runCommand("tell [ 1 2 3 10 ] fd 10 setheading 50 askwith [ equal? ycor 0 ] [ fd 50 ]");
		p.getVariableContainer().debug();
		p.getCommandContainer().debug();
		p.getTurtleContainer().debug();
	}
	
	public SlogoParser(){
		myTurtleContainer = new TurtleContainer();
		myVariableContainer = new VariableContainer();
		myCommandContainer = new CommandContainer();
		myResourceParser = new ResourceParser();
		myCommandInterpreter = new CommandInterpreter(this, myResourceParser);
	}
	
	public FullTurtle getCurrentTurtle(){
		return myTurtleContainer.getCurrentTurtle();
	}
	
	public VariableContainer getVariableContainer(){
		return myVariableContainer;
	}
	
	public CommandContainer getCommandContainer(){
		return myCommandContainer;
	}
	
	public TurtleContainer getTurtleContainer() {
		return myTurtleContainer;
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
		myCommandInterpreter.interpret(command);
	}

	@Override
	public void runCommand(CommandList command) throws ParserException {
		myCommandInterpreter.interpret(command);
	}

	@Override
	public void setLanguage(String language) throws ParserException {
		myResourceParser.setLanguage(language);
	}
	
	@Override
	public void addVariableObserver(Observer o){
	//	System.out.println(myVariableContainer == null);
		myVariableContainer.addObserver(o);
	}
	
	@Override
	public void addCommandObserver(Observer o){
		myCommandContainer.addObserver(o);
	}
	
	@Override
	public void addTurtleObserver(Observer o){
		myTurtleContainer.addObserver(o);
		myTurtleContainer.update();
	}

	@Override
	public void debug() {
		myVariableContainer.debug();
		myCommandContainer.debug();
		myTurtleContainer.debug();
	}
}
