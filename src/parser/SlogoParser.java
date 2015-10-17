package parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observer;

import parser.command.CommandInterpreter;
import parser.command.CommandList;
import parser.structure.CommandContainer;
import parser.structure.Turtle;
import parser.structure.TurtleContainer;
import parser.structure.VariableContainer;

public class SlogoParser implements ParserInterface{

	private TurtleContainer myTurtleContainer;
	private VariableContainer myVariableContainer;
	private CommandContainer myCommandContainer;
	
//	public static void main(String args[]) throws ParserException{
//		
//		SlogoParser p = new SlogoParser();
//		
//		//p.runCommand("to walk [ :turns ] [ repeat :turns [ forward 50 2 ] ] walk walk 3");
//		p.loadCommand("examples/procedures_with_parameters/random_range.logo");
//		//p.loadCommand("examples/simple/forward_complex.logo");
//		p.getVariableContainer().debug();
//		p.getCommandContainer().debug();
//	}
	
	public SlogoParser(){
		myTurtleContainer = new TurtleContainer();
		myVariableContainer = new VariableContainer();
		myCommandContainer = new CommandContainer();
	}
	
	public Turtle getCurrentTurtle(){
		return myTurtleContainer.getCurrentTurtle();
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
	
	@Override
	public void addVariableObserver(Observer o){
		myVariableContainer.addObserver(o);
	}

	@Override
	public void debug() {
		myVariableContainer.debug();
		myCommandContainer.debug();
		myTurtleContainer.debug();
	}
}
